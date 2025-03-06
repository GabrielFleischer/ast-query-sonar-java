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

package org.sonar.plugins.java.api.query.graph

import org.sonar.plugins.java.api.query.Query
import org.sonar.plugins.java.api.query.graph.exec.ExecutionContext
import org.sonar.plugins.java.api.query.graph.exec.ExecutionGraph
import org.sonar.plugins.java.api.query.graph.exec.Store
import org.sonar.plugins.java.api.query.graph.ir.TranslationTable
import org.sonar.plugins.java.api.query.graph.ir.nodes.*

abstract class Builder<N : Node<N>> {

  abstract fun <IN> build(root: Root<IN>): ExecutionGraph<N, IN>

  protected fun addScopesToCombines(root: Root<*>) {
    val applied = mutableSetOf<NodeId>()

    while (true) {
      val combine = GraphUtils.topologicalSort(root)
        .filter { it is Combine<*, *, *> || it is CombineDrop<*, *, *> }
        .reversed()
        .firstOrNull { applied.contains(it.id).not() }
        ?: return

      val dominator = combine.immediateDominator

      scope(combine, dominator)
      GraphUtils.removeDeadBranches(root)

      applied += combine.id
    }
  }

  private fun <OUT> scope(scoped: IRNode<*, *>, from: IRNode<*, OUT>) {
    val scope = Scope(from)
    GraphUtils.copySubTree(from, scope, scoped)

    val children = scoped.children
    val unscope = UnScope(scoped, scope)
    val table = TranslationTable()
    table.addParent(scoped, unscope)

    children.forEach { it.applyTranslation(table) }
  }

  private data class TangledScope(val mainScope: Scope<*>, val mainUnscope: UnScope<*>, val tangledScope: Scope<*>)

  protected fun untangleScopes(root: Root<*>) {
    while (true) {
      val nextTangledScope = findTangledScope(root) ?: return

      GraphUtils.removeNodeAndStitch(nextTangledScope.mainUnscope)
      nextTangledScope.tangledScope.unscopes.forEach {
        it.addScopeStart(nextTangledScope.mainScope)
      }
    }
  }

  private fun findTangledScope(root: Root<*>): TangledScope? {
    val scopeCandidates = GraphUtils.topologicalSort(root)
      .filterIsInstance<Scope<*>>()

    for (candidate in scopeCandidates) {
      for (unscope in candidate.unscopes) {
        val scopedNodes = candidate.strictDescendants
          .intersect(unscope.strictAncestors)

        // A tangled scope is any scoped scope that has an unscoped node that is outside the current scope
        val tangledScope = scopedNodes
          .filterIsInstance<Scope<*>>()
          .firstOrNull { s -> s.unscopes.any { it !in scopedNodes } }

        if (tangledScope != null) {
          return TangledScope(candidate, unscope, tangledScope)
        }
      }
    }

    return null
  }

  protected fun <T> applyMergeOptimization(node: IRNode<*, T>) {
    fun <OUT> tryToMerge(index: Int, child: IRNode<*, OUT>): Boolean {
      val candidates = node.children
        .filterIndexed { i, other -> i > index && other.canMergeWith(child) }
        .toList()

      candidates.forEach { candidate ->
        @Suppress("UNCHECKED_CAST")
        candidate.children.forEach { c ->
          child.addChild(c as ChildNode<OUT>)
        }

        candidate.delete()
      }

      // Return true if any candidates were merged
      return candidates.isNotEmpty()
    }

    fun <T> mergeEquivalentChildren(node: IRNode<*, T>) {
      while (true) {
        var merged = false
        for (i in node.children.indices) {
          if (tryToMerge(i, node.children[i])) {
            merged = true
            break
          }
        }

        if (!merged) {
          return
        }
      }
    }

    mergeEquivalentChildren(node)
    node.children.forEach { applyMergeOptimization(it) }
  }

  fun <IN, END, OUT> buildQuery(root: Root<IN>, end: IRNode<*, END>, transform: (List<END>) -> OUT): Query<IN, OUT> {

    val table = GraphUtils.copyTree(root)
    val root = table.get(root) as Root<IN>
    val end = table.get(end)

    val collector = Store { mutableListOf<END>() }
    Consumer(end) { ctx, value ->
      collector.get(ctx).add(value)
    }

    GraphUtils.removeDeadBranches(root)

    val graph = build(root)

    return Query { ctx, input ->
      val execCtx = ExecutionContext(ctx)
      graph.execute(execCtx, input)

      collector.get(execCtx).let(transform)
    }
  }
}
