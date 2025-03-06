package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ExpressionStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ExpressionStatementTree>.expression() = func(ExpressionStatementTree::expression)
fun <T> OptionalSelector<T, out ExpressionStatementTree>.expression() = func(ExpressionStatementTree::expression)
fun <T> ManySelector<T, out ExpressionStatementTree>.expression() = func(ExpressionStatementTree::expression)

fun <T> SingleSelector<T, out ExpressionStatementTree>.semicolonToken() = func(ExpressionStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out ExpressionStatementTree>.semicolonToken() = func(ExpressionStatementTree::semicolonToken)
fun <T> ManySelector<T, out ExpressionStatementTree>.semicolonToken() = func(ExpressionStatementTree::semicolonToken)