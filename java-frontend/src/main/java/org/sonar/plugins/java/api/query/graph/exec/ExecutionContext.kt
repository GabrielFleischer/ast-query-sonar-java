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

package org.sonar.plugins.java.api.query.graph.exec

import org.sonar.plugins.java.api.JavaFileScannerContext
import org.sonar.plugins.java.api.tree.BaseTreeVisitor
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.Tree

data class ExecutionContext(
  val scannerContext: JavaFileScannerContext
) {
  val flattenedTree = FlattenedTree.create(scannerContext.getTree())
}

data class FlattenedTree(
  val nodesInOrder: List<Tree>,
  val subTreeMap: Map<Tree, Pair<Int, Int>>
) {

  companion object {
    fun create(tree: CompilationUnitTree): FlattenedTree {
      val nodesInOrder = arrayListOf<Tree>()
      val subTreeMap = mutableMapOf<Tree, Pair<Int, Int>>()
      var counter = 0

     val visitor = object : BaseTreeVisitor() {
       public override fun scan(tree: Tree?) {
         if (tree != null) {
           nodesInOrder.add(tree)
           val start = counter++
           tree.accept(this)

           subTreeMap[tree] = (start + 1) to counter
         }
       }
     }

      visitor.scan(tree)

      return FlattenedTree(nodesInOrder, subTreeMap.toMap())
     }
    }

  fun getTree(
    tree: Tree,
    shouldTraverse: (Tree) -> Boolean = { true },
    includeStart: Boolean = true, // If true, yield the tree itself as the first parameter
    includeNonTraversed: Boolean = false // If true, yield the nodes that will not be traversed
  ): Sequence<Tree> {
    return sequence {
      if (includeStart) {
        yield(tree)
      }

      var (cur, end) = subTreeMap.getValue(tree)
      while (cur < end) {
        val node = nodesInOrder[cur]
        if (shouldTraverse(node)) {
          yield(node)
          cur++
        } else {
          if (includeNonTraversed) {
            yield(node)
          }

          cur = subTreeMap.getValue(node).second
        }
      }
    }
  }
}


