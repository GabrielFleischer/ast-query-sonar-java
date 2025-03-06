package org.sonar.java.checks.queryAPI

import org.sonar.check.Rule
import org.sonar.java.model.ExpressionUtils
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.QueryRule
import org.sonar.plugins.java.api.query.operation.composite.report
import org.sonar.plugins.java.api.query.operation.core.filter
import org.sonar.plugins.java.api.query.operation.core.isPresent
import org.sonar.plugins.java.api.query.operation.core.where
import org.sonar.plugins.java.api.query.operation.tree.ofKind
import org.sonar.plugins.java.api.query.tree.TreeKind
import org.sonar.plugins.java.api.query.tree.expression
import org.sonar.plugins.java.api.query.tree.methodSelect
import org.sonar.plugins.java.api.semantic.MethodMatchers
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree
import org.sonar.plugins.java.api.tree.MethodInvocationTree
import org.sonar.plugins.java.api.tree.Tree

@Rule(key = "S4635")
class StringOffsetMethodsCheckKT : QueryRule {

  private val javaLangString: String = "java.lang.String"
  private val int: String = "int"
  private val substring: MethodMatchers = MethodMatchers.create()
    .ofTypes(javaLangString)
    .names("substring")
    .addParametersMatcher(int)
    .build()
  private val indexOf: MethodMatchers = MethodMatchers.or(
    MethodMatchers.create()
      .ofTypes(javaLangString)
      .names("indexOf", "lastIndexOf")
      .addParametersMatcher(int)
      .build(),
    MethodMatchers.create()
      .ofTypes(javaLangString)
      .names("indexOf", "lastIndexOf", "startsWith")
      .addParametersMatcher(javaLangString)
      .build()
  )

  override fun createQuery(entry: ManySelector<CompilationUnitTree, Tree>) {
    entry
      .ofKind(TreeKind.METHOD_INVOCATION)
      .filter { invoke -> indexOf.matches(invoke.methodSymbol()) }
      .where { invoke ->
        invoke.methodSelect()
          .ofKind(TreeKind.MEMBER_SELECT)
          .expression()
          .ofKind(TreeKind.METHOD_INVOCATION)
          .filter { substring.matches(it) }
          .isPresent()
      }
      .report { context, issue ->
        context.reportIssue(
          this,
          ExpressionUtils.methodName((issue.methodSelect() as MemberSelectExpressionTree).expression() as MethodInvocationTree),
          issue,
          String.format("Replace \"%s\" with the overload that accepts an offset parameter.", issue.methodSymbol().name())
        )
      }
  }
}
