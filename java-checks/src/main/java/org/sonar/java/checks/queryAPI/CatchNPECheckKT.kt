package org.sonar.java.checks.queryAPI

import org.sonar.check.Rule
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.QueryRule
import org.sonar.plugins.java.api.query.operation.composite.eq
import org.sonar.plugins.java.api.query.operation.composite.report
import org.sonar.plugins.java.api.query.operation.core.filter
import org.sonar.plugins.java.api.query.operation.core.map
import org.sonar.plugins.java.api.query.operation.core.union
import org.sonar.plugins.java.api.query.operation.core.where
import org.sonar.plugins.java.api.query.operation.tree.ofKind
import org.sonar.plugins.java.api.query.tree.*
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.Tree

@Rule(key = "S1696")
class CatchNPECheckKT : QueryRule {

  override fun createQuery(entry: ManySelector<CompilationUnitTree, Tree>) {
    val catchType = entry.ofKind(TreeKind.CATCH).parameter().type()

    // For Union Type, flatten the types
    val types = (catchType.ofKind(TreeKind.UNION_TYPE).typeAlternatives() union catchType)

    types.ofKind(TreeKind.IDENTIFIER)
      .where { it.name().eq("NullPointerException") }
      .report { context, tree ->
        context.reportIssue(this, tree, "Avoid catching NullPointerException.");
      }

    types.ofKind(TreeKind.MEMBER_SELECT)
      .filter { it.symbolType().`is`("java.lang.NullPointerException") }
      .identifier()
      .report { context, tree ->
        context.reportIssue(this, tree, "Avoid catching NullPointerException.");
      }
  }
}
