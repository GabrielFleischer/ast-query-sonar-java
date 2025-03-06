package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.BinaryExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out BinaryExpressionTree>.leftOperand() = func(BinaryExpressionTree::leftOperand)
fun <T> OptionalSelector<T, out BinaryExpressionTree>.leftOperand() = func(BinaryExpressionTree::leftOperand)
fun <T> ManySelector<T, out BinaryExpressionTree>.leftOperand() = func(BinaryExpressionTree::leftOperand)

fun <T> SingleSelector<T, out BinaryExpressionTree>.operatorToken() = func(BinaryExpressionTree::operatorToken)
fun <T> OptionalSelector<T, out BinaryExpressionTree>.operatorToken() = func(BinaryExpressionTree::operatorToken)
fun <T> ManySelector<T, out BinaryExpressionTree>.operatorToken() = func(BinaryExpressionTree::operatorToken)

fun <T> SingleSelector<T, out BinaryExpressionTree>.rightOperand() = func(BinaryExpressionTree::rightOperand)
fun <T> OptionalSelector<T, out BinaryExpressionTree>.rightOperand() = func(BinaryExpressionTree::rightOperand)
fun <T> ManySelector<T, out BinaryExpressionTree>.rightOperand() = func(BinaryExpressionTree::rightOperand)