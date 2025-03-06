package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.UnaryExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out UnaryExpressionTree>.expression() = func(UnaryExpressionTree::expression)
fun <T> OptionalSelector<T, out UnaryExpressionTree>.expression() = func(UnaryExpressionTree::expression)
fun <T> ManySelector<T, out UnaryExpressionTree>.expression() = func(UnaryExpressionTree::expression)

fun <T> SingleSelector<T, out UnaryExpressionTree>.operatorToken() = func(UnaryExpressionTree::operatorToken)
fun <T> OptionalSelector<T, out UnaryExpressionTree>.operatorToken() = func(UnaryExpressionTree::operatorToken)
fun <T> ManySelector<T, out UnaryExpressionTree>.operatorToken() = func(UnaryExpressionTree::operatorToken)