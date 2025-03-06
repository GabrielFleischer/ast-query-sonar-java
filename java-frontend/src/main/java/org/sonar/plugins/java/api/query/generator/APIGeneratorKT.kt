/*
 * SonarQube Java
 * Copyright (C) 2012-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.plugins.java.api.query.generator

import org.sonar.plugins.java.api.tree.Tree
import java.io.File
import java.lang.reflect.Method
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
  println("APIGenerator")

  val paths = getPaths(args)

  generateTreeSelectors(paths)
}

data class Paths(
  val srcPackage: String,
  val dstPackage: String,
  val srcDir: Path,
  val dstDir: Path,
)

private fun getPaths(args: Array<String>): Paths {
  val projectBaseDir = Path.of(args.getOrNull(0) ?: ".")
  println("Project Base Dir: $projectBaseDir")

  val srcPkg = "org.sonar.plugins.java.api.tree"
  val srcDir = projectBaseDir.resolve(
    "src.main.java.$srcPkg".replace('.', File.separatorChar)
  )
  println("Src Package Directory: $srcDir")
  val dstPkg = "org.sonar.plugins.java.api.query.tree"
  val dstDir = projectBaseDir.resolve(
    "src.main.java.$dstPkg".replace('.', File.separatorChar)
  )
  println("Dst Package Directory: $dstDir")
  Files.createDirectories(dstDir)

  return Paths(srcPkg, dstPkg, srcDir, dstDir)
}

private fun generateTreeSelectors(paths: Paths) {
  val selectors = genSelectors(paths)

  for ((type, imports, typeSelectors) in selectors) {
    if (typeSelectors.isEmpty()) {
      continue
    }

    val content = """
      package ${paths.dstPackage}
      
      import org.sonar.plugins.java.api.query.SingleSelector
      import org.sonar.plugins.java.api.query.OptionalSelector
      import org.sonar.plugins.java.api.query.ManySelector
      import ${paths.srcPackage}.$type
    """.trimIndent()
      .plus(imports.joinToString("\n", "\n", "\n\n"))
      .plus(typeSelectors.joinToString("\n\n"))

    val outputPath = paths.dstDir.resolve("$type.kt")

    Files.writeString(outputPath, content)
  }
}

private data class GetterDef(
  val getter: Method,
  val type: String,
  val typeParamsDef: List<TypeVariable<out Class<*>?>>,
) {
  val typeParams = ('A'..'Z').take(typeParamsDef.size)

  val name = getter.name

  val isNullable = getter.annotations.any { it.annotationClass.simpleName == "Nullable" }
  val isList = List::class.java.isAssignableFrom(getter.returnType)

  // If the getter returns a List, the opName is listName
  // Name should have its fist letter capitalized
  private val capitalizedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
  val opName = if (isList) "list$capitalizedName" else name

  val methodTypeParams = if (typeParams.isEmpty()) "" else typeParams.joinToString("") { ", $it" }
  val classTypeParams = if (typeParams.isEmpty()) "" else typeParams.joinToString(", ", "<", ">")
  val typeParamsBounds =
    if (typeParams.isEmpty()) ""
    else {
      " where " + typeParams
        .zip(typeParamsDef.map { it.bounds })
        .flatMap { (tp, bounds) -> bounds.map { "$tp: ${boundName(it)}" } }
        .joinToString(", ")
    }

  val necessaryImports = typeParamsDef
    .flatMap { it.bounds.toList() }
    .filterIsInstance<Class<*>>()
    .map { "import ${it.name}" }
    .let { imports ->
      val finalImports = imports.toMutableList()
      when {
        isNullable && isList -> {
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.optFunc"
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.optListFunc"
        }
        isList -> {
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.func"
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.listFunc"
        }
        isNullable -> {
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.optFunc"
        }
        else -> {
          finalImports += "import org.sonar.plugins.java.api.query.operation.composite.func"
        }
      }

      finalImports.toList()
    }

  private fun boundName(type: Type): String =
    if (type is Class<*>) type.simpleName else type.toString()

  fun generate(): String {
    fun generateCalls(funName: String, selectorCall: String) = listOf("SingleSelector", "OptionalSelector", "ManySelector").map { selector ->
      "fun <T$methodTypeParams> $selector<T, out $type$classTypeParams>.$funName()$typeParamsBounds = $selectorCall($type$classTypeParams::$name)"
    }

    val calls = generateCalls(opName, if (isNullable) "optFunc" else "func").toMutableList()
    if (isList) {
      calls += generateCalls(name, if (isNullable) "optListFunc" else "listFunc")
    }

    return calls.joinToString("\n")
  }
}

private data class TypeSelectors(
  val type: String,
  val imports: Set<String>,
  val selectors: List<String>,
)

private fun genSelectors(paths: Paths): List<TypeSelectors> =
  Files.list(paths.srcDir)
    .use { it.toList() }
    .map { it.fileName.toString() }
    .filter { it.endsWith(".java") && it != "package-info.java" }
    .map { filename ->
      val type = filename.substring(0, filename.length - ".java".length)
      val clazz = Class.forName("${paths.srcPackage}.$type")

      type to clazz
    }
    .filter { Tree::class.java.isAssignableFrom(it.second) }
    .map { (type, clazz) ->

      val getters = clazz.methods
        .asSequence()
        .filter { it.parameterCount == 0 }
        .filter { it.declaringClass == clazz }
        .filter { it.returnType != Void::class.java }
        .filter { it.annotations.none { a -> a.annotationClass.simpleName == "Deprecated" } }
        .toList()

      val selectors = getters.map { GetterDef(it, type, clazz.typeParameters.toList().filterNotNull()) }
      val imports = selectors.flatMap { it.necessaryImports }.toSet()

      TypeSelectors(type, imports, selectors.map { it.generate() }.sorted())
    }
