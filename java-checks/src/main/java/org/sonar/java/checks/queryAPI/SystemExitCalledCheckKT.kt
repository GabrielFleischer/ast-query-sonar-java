package org.sonar.java.checks.queryAPI

import org.sonar.check.Rule
import org.sonar.java.checks.helpers.MethodTreeUtils
import org.sonar.java.model.ExpressionUtils
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.QueryRule
import org.sonar.plugins.java.api.query.operation.composite.first
import org.sonar.plugins.java.api.query.operation.composite.report
import org.sonar.plugins.java.api.query.operation.core.filter
import org.sonar.plugins.java.api.query.operation.core.notPresent
import org.sonar.plugins.java.api.query.operation.core.where
import org.sonar.plugins.java.api.query.operation.tree.ofKind
import org.sonar.plugins.java.api.query.operation.tree.parents
import org.sonar.plugins.java.api.query.tree.TreeKind
import org.sonar.plugins.java.api.semantic.MethodMatchers
import org.sonar.plugins.java.api.tree.CompilationUnitTree
import org.sonar.plugins.java.api.tree.Tree

@Rule(key = "S1147")
class SystemExitCalledCheckKT : QueryRule {

  private val exitMethods = MethodMatchers.create()
    .ofTypes("java.lang.System", "java.lang.Runtime")
    .names("exit", "halt")
    .addParametersMatcher("int")
    .build()

  override fun createQuery(entry: ManySelector<CompilationUnitTree, Tree>) {
    entry.ofKind(TreeKind.METHOD_INVOCATION)
      .filter(exitMethods::matches)
      .isNotInMainMethod()
      .report { context, invocation ->
        val methodName = ExpressionUtils.methodName(invocation).name()
        context.reportIssue(this, invocation.methodSelect(), "Remove this call to \"$methodName\" or ensure it is really required.")
      }
  }

  private fun <CUR : Tree> ManySelector<CompilationUnitTree, CUR>.isNotInMainMethod() =
    where { tree ->
      tree.parents()
        .ofKind(TreeKind.METHOD,
          TreeKind.CLASS,
          TreeKind.INTERFACE,
          TreeKind.ENUM,
          TreeKind.ANNOTATION_TYPE,
          TreeKind.LAMBDA_EXPRESSION,
          TreeKind.CONSTRUCTOR,
          TreeKind.INITIALIZER,
          TreeKind.STATIC_INITIALIZER,
          TreeKind.RECORD)
        .first()
        .ofKind(TreeKind.METHOD)
        .filter { MethodTreeUtils.isMainMethod(it) }
        .notPresent()
    }
}
