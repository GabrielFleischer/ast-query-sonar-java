package org.sonar.java.checks.queryAPI

import org.sonar.check.Rule
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.QueryRule
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.operation.composite.eq
import org.sonar.plugins.java.api.query.operation.composite.first
import org.sonar.plugins.java.api.query.operation.composite.report
import org.sonar.plugins.java.api.query.operation.core.combine
import org.sonar.plugins.java.api.query.operation.core.filter
import org.sonar.plugins.java.api.query.operation.core.flatMap
import org.sonar.plugins.java.api.query.operation.core.noneExists
import org.sonar.plugins.java.api.query.operation.core.union
import org.sonar.plugins.java.api.query.operation.core.where
import org.sonar.plugins.java.api.query.operation.tree.ofKind
import org.sonar.plugins.java.api.query.operation.tree.parents
import org.sonar.plugins.java.api.query.operation.tree.subtree
import org.sonar.plugins.java.api.query.tree.*
import org.sonar.plugins.java.api.semantic.Symbol
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.Tree
import org.sonar.plugins.java.api.tree.TryStatementTree

@Rule(key = "S2221")
class CatchExceptionCheckKT : QueryRule {

  override fun createQuery(entry: ManySelector<CompilationUnitTree, Tree>) {
    val catchType = entry.ofKind(TreeKind.CATCH)
      .where { catch ->
        val tryStatement = catch
          .parents()
          .ofKind(TreeKind.TRY_STATEMENT).first()

        tryStatement.noResources()
          .combine(tryStatement.noExceptionThrownInBlock()) { a, b -> a && b }
      }
      .parameter()
      .type()

    // For Union Type, flatten the types
    val types = (catchType.ofKind(TreeKind.UNION_TYPE).typeAlternatives() union catchType)

    types.ofKind(TreeKind.IDENTIFIER)
      .where { it.name().eq("Exception") }
      .report { context, tree ->
        context.reportIssue(this, tree, "Catch a list of specific exception subtypes instead.");
      }

    types.ofKind(TreeKind.MEMBER_SELECT)
      .filter { it.symbolType().`is`("java.lang.Exception") }
      .identifier()
      .report { context, tree ->
        context.reportIssue(this, tree, "Catch a list of specific exception subtypes instead.");
      }
  }

  private fun OptionalSelector<*, TryStatementTree>.noResources() =
    resourceList().noneExists()

  private fun OptionalSelector<*, TryStatementTree>.noExceptionThrownInBlock(): SingleSelector<*, Boolean> {
    val symbols = subtree()
        .let {
          it.ofKind(TreeKind.METHOD_INVOCATION).methodSymbol() union
            it.ofKind(TreeKind.NEW_CLASS).methodSymbol()
        }

    // If the method is unknown, we can't check the thrown types we need to assume it does
    val unknownMethods = symbols.filter { it.isUnknown }
    val thowsException = symbols.filter { !it.isUnknown }
      .flatMap(Symbol.MethodSymbol::thrownTypes)
      .filter { type -> type.`is`("java.lang.Exception") }

    return (unknownMethods union thowsException).noneExists()
  }
}
