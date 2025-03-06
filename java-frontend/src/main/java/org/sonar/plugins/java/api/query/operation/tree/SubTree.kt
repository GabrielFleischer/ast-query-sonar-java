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

package org.sonar.plugins.java.api.query.operation.tree

import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedFunction
import org.sonar.plugins.java.api.query.graph.ir.SubtreeFunction
import org.sonar.plugins.java.api.query.graph.ir.TreeIsOfKindFunction
import org.sonar.plugins.java.api.query.graph.ir.constant
import org.sonar.plugins.java.api.query.operation.core.flatMapSeq
import org.sonar.plugins.java.api.query.tree.TreeKind
import org.sonar.plugins.java.api.tree.Tree
import org.sonar.plugins.java.api.tree.Tree.Kind

class TreeParameters {
  var stopAt: IdentifiedFunction<(Tree) -> Boolean> = constant(false, "Nothing")

  fun stopAt(vararg kinds: Kind) = stopAt(kinds.toSet())

  fun stopAt(kinds: Set<Kind>) {
    this.stopAt = TreeIsOfKindFunction(kinds)
  }
}

fun <INIT, T : Tree> SingleSelector<INIT, T>.subtree() = flatMapSeq(SubtreeFunction(false, emptySet()))
fun <INIT, T : Tree> OptionalSelector<INIT, T>.subtree() = flatMapSeq(SubtreeFunction(false, emptySet()))
fun <INIT, T : Tree> ManySelector<INIT, T>.subtree() = flatMapSeq(SubtreeFunction(false, emptySet()))

fun <INIT, T : Tree> SingleSelector<INIT, T>.tree() = flatMapSeq(SubtreeFunction(true, emptySet()))
fun <INIT, T : Tree> OptionalSelector<INIT, T>.tree() = flatMapSeq(SubtreeFunction(true, emptySet()))
fun <INIT, T : Tree> ManySelector<INIT, T>.tree() = flatMapSeq(SubtreeFunction(true, emptySet()))

fun <INIT, T : Tree> SingleSelector<INIT, T>.subtreeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(false, kinds.toSet()))

fun <INIT, T : Tree> OptionalSelector<INIT, T>.subtreeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(false, kinds.toSet()))

fun <INIT, T : Tree> ManySelector<INIT, T>.subtreeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(false, kinds.toSet()))

fun <INIT, T : Tree> SingleSelector<INIT, T>.treeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(true, kinds.toSet()))

fun <INIT, T : Tree> OptionalSelector<INIT, T>.treeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(true, kinds.toSet()))

fun <INIT, T : Tree> ManySelector<INIT, T>.treeStoppingAt(vararg kinds: Kind) =
  flatMapSeq(SubtreeFunction(true, kinds.toSet()))

fun <INIT, T : Tree> SingleSelector<INIT, T>.subtreeStoppingAt(kinds: Set<Kind>) =
  flatMapSeq(SubtreeFunction(false, kinds))

fun <INIT, T : Tree> OptionalSelector<INIT, T>.subtreeStoppingAt(kinds: Set<Kind>) =
  flatMapSeq(SubtreeFunction(false, kinds))

fun <INIT, T : Tree> ManySelector<INIT, T>.subtreeStoppingAt(kinds: Set<Kind>) =
  flatMapSeq(SubtreeFunction(false, kinds))

@JvmName("subTree-TK")
fun <INIT, T : Tree> SingleSelector<INIT, T>.subtreeStoppingAt(vararg kinds: TreeKind<*>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))

@JvmName("subTree-TK")
fun <INIT, T : Tree> OptionalSelector<INIT, T>.subtreeStoppingAt(vararg kinds: TreeKind<*>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))

@JvmName("subTree-TK")
fun <INIT, T : Tree> ManySelector<INIT, T>.subtreeStoppingAt(vararg kinds: TreeKind<*>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))

@JvmName("subTree-TK")
fun <INIT, T : Tree> SingleSelector<INIT, T>.subtreeStoppingAt(kinds: Set<TreeKind<*>>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))

@JvmName("subTree-TK")
fun <INIT, T : Tree> OptionalSelector<INIT, T>.subtreeStoppingAt(kinds: Set<TreeKind<*>>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))

@JvmName("subTree-TK")
fun <INIT, T : Tree> ManySelector<INIT, T>.subtreeStoppingAt(kinds: Set<TreeKind<*>>) =
  flatMapSeq(SubtreeFunction(false, kinds.map { it.kind }.toSet()))
