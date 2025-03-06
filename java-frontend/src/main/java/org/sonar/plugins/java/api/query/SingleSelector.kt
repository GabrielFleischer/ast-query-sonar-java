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

package org.sonar.plugins.java.api.query

import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.Root
import org.sonar.plugins.java.api.query.operation.Operation1to1
import org.sonar.plugins.java.api.query.operation.Operation1toOptional
import org.sonar.plugins.java.api.query.operation.tree.treeStoppingAt
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.Tree

class SingleSelector<INIT, CUR>(
  start: Root<INIT>,
  current: IRNode<*, out CUR>
) : Selector<INIT, CUR, CUR>(start, current) {

  fun <TO> apply(op: Operation1to1<in CUR, TO>): SingleSelector<INIT, TO> =
    SingleSelector(start, op.applyTo(current))

  fun <TO> apply(op: Operation1toOptional<in CUR, TO>): OptionalSelector<INIT, TO> =
    OptionalSelector(start, op.applyTo(current))

  override fun toOutput(current: List<CUR>): CUR =
    current.singleOrNull() ?: throw IllegalStateException("The query produced more elements than expected")

  override fun toSingle(): SingleSelector<INIT, CUR> = this
}

fun newQuery(ignoreSubTrees: Set<Tree.Kind> = setOf()): ManySelector<CompilationUnitTree, Tree> {
  val root = Root<CompilationUnitTree>()
  return SingleSelector(root, root).treeStoppingAt(*ignoreSubTrees.toTypedArray())
}
