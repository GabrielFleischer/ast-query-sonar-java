package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out AssignmentExpressionTree>.expression() = func(AssignmentExpressionTree::expression)
fun <T> OptionalSelector<T, out AssignmentExpressionTree>.expression() = func(AssignmentExpressionTree::expression)
fun <T> ManySelector<T, out AssignmentExpressionTree>.expression() = func(AssignmentExpressionTree::expression)

fun <T> SingleSelector<T, out AssignmentExpressionTree>.operatorToken() = func(AssignmentExpressionTree::operatorToken)
fun <T> OptionalSelector<T, out AssignmentExpressionTree>.operatorToken() = func(AssignmentExpressionTree::operatorToken)
fun <T> ManySelector<T, out AssignmentExpressionTree>.operatorToken() = func(AssignmentExpressionTree::operatorToken)

fun <T> SingleSelector<T, out AssignmentExpressionTree>.variable() = func(AssignmentExpressionTree::variable)
fun <T> OptionalSelector<T, out AssignmentExpressionTree>.variable() = func(AssignmentExpressionTree::variable)
fun <T> ManySelector<T, out AssignmentExpressionTree>.variable() = func(AssignmentExpressionTree::variable)