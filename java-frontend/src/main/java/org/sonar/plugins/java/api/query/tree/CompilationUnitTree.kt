package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out CompilationUnitTree>.eofToken() = func(CompilationUnitTree::eofToken)
fun <T> OptionalSelector<T, out CompilationUnitTree>.eofToken() = func(CompilationUnitTree::eofToken)
fun <T> ManySelector<T, out CompilationUnitTree>.eofToken() = func(CompilationUnitTree::eofToken)

fun <T> SingleSelector<T, out CompilationUnitTree>.listImports() = func(CompilationUnitTree::imports)
fun <T> OptionalSelector<T, out CompilationUnitTree>.listImports() = func(CompilationUnitTree::imports)
fun <T> ManySelector<T, out CompilationUnitTree>.listImports() = func(CompilationUnitTree::imports)
fun <T> SingleSelector<T, out CompilationUnitTree>.imports() = listFunc(CompilationUnitTree::imports)
fun <T> OptionalSelector<T, out CompilationUnitTree>.imports() = listFunc(CompilationUnitTree::imports)
fun <T> ManySelector<T, out CompilationUnitTree>.imports() = listFunc(CompilationUnitTree::imports)

fun <T> SingleSelector<T, out CompilationUnitTree>.listTypes() = func(CompilationUnitTree::types)
fun <T> OptionalSelector<T, out CompilationUnitTree>.listTypes() = func(CompilationUnitTree::types)
fun <T> ManySelector<T, out CompilationUnitTree>.listTypes() = func(CompilationUnitTree::types)
fun <T> SingleSelector<T, out CompilationUnitTree>.types() = listFunc(CompilationUnitTree::types)
fun <T> OptionalSelector<T, out CompilationUnitTree>.types() = listFunc(CompilationUnitTree::types)
fun <T> ManySelector<T, out CompilationUnitTree>.types() = listFunc(CompilationUnitTree::types)

fun <T> SingleSelector<T, out CompilationUnitTree>.moduleDeclaration() = optFunc(CompilationUnitTree::moduleDeclaration)
fun <T> OptionalSelector<T, out CompilationUnitTree>.moduleDeclaration() = optFunc(CompilationUnitTree::moduleDeclaration)
fun <T> ManySelector<T, out CompilationUnitTree>.moduleDeclaration() = optFunc(CompilationUnitTree::moduleDeclaration)

fun <T> SingleSelector<T, out CompilationUnitTree>.packageDeclaration() = optFunc(CompilationUnitTree::packageDeclaration)
fun <T> OptionalSelector<T, out CompilationUnitTree>.packageDeclaration() = optFunc(CompilationUnitTree::packageDeclaration)
fun <T> ManySelector<T, out CompilationUnitTree>.packageDeclaration() = optFunc(CompilationUnitTree::packageDeclaration)