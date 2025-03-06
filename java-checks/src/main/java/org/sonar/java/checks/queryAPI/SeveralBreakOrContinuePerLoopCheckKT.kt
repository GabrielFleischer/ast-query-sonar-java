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
package org.sonar.java.checks.queryAPI

import org.sonar.check.Rule
import org.sonar.plugins.java.api.JavaFileScannerContext
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.QueryRule
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.operation.composite.report
import org.sonar.plugins.java.api.query.operation.core.filter
import org.sonar.plugins.java.api.query.operation.core.groupWith
import org.sonar.plugins.java.api.query.operation.tree.ofKind
import org.sonar.plugins.java.api.query.operation.tree.subtreeStoppingAt
import org.sonar.plugins.java.api.query.operation.core.union
import org.sonar.plugins.java.api.query.tree.TreeKind
import org.sonar.plugins.java.api.tree.BreakStatementTree
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.ContinueStatementTree
import org.sonar.plugins.java.api.tree.Tree

@Rule(key = "S135")
class SeveralBreakOrContinuePerLoopCheckKT : QueryRule {
  private val loopKinds: Set<Tree.Kind> = setOf(
    Tree.Kind.FOR_STATEMENT,
    Tree.Kind.FOR_EACH_STATEMENT,
    Tree.Kind.WHILE_STATEMENT,
    Tree.Kind.DO_STATEMENT
  )

  private fun SingleSelector<*, out Tree>.findBreaks(): ManySelector<*, BreakStatementTree> =
    subtreeStoppingAt(loopKinds + Tree.Kind.SWITCH_STATEMENT)
      .ofKind(TreeKind.BREAK_STATEMENT)
      // Ignore breaks with labels
      .filter { it.label() == null }

  private fun SingleSelector<*, out Tree>.findContinues(): ManySelector<*, ContinueStatementTree> =
    subtreeStoppingAt(loopKinds)
      .ofKind(TreeKind.CONTINUE_STATEMENT)

  override fun createQuery(entry: ManySelector<CompilationUnitTree, Tree>) {
    entry.ofKind(TreeKind.FOR_STATEMENT, TreeKind.FOR_EACH_STATEMENT, TreeKind.WHILE_STATEMENT, TreeKind.DO_STATEMENT)
      .groupWith  { loop ->
        loop.findBreaks() union loop.findContinues()
      }
      .filter { (_, breakAndContinues) -> breakAndContinues.size > 1 }
      .report { context, (loop, breakAndContinues) ->

        val secondaryLocations = breakAndContinues.map { t ->
          JavaFileScannerContext.Location(
            "\"${if (t.`is`(Tree.Kind.BREAK_STATEMENT)) "break" else "continue"}\" statement.",
            t
          )
        }

        context.reportIssue(
          this,
          loop.firstToken()!!,
          "Reduce the total number of break and continue statements in this loop to use at most one.",
          secondaryLocations,
          breakAndContinues.size - 1
        )
      }
  }
}
