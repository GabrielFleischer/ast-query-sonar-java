package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ConditionalExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ConditionalExpressionTree>.colonToken() = func(ConditionalExpressionTree::colonToken)
fun <T> OptionalSelector<T, out ConditionalExpressionTree>.colonToken() = func(ConditionalExpressionTree::colonToken)
fun <T> ManySelector<T, out ConditionalExpressionTree>.colonToken() = func(ConditionalExpressionTree::colonToken)

fun <T> SingleSelector<T, out ConditionalExpressionTree>.condition() = func(ConditionalExpressionTree::condition)
fun <T> OptionalSelector<T, out ConditionalExpressionTree>.condition() = func(ConditionalExpressionTree::condition)
fun <T> ManySelector<T, out ConditionalExpressionTree>.condition() = func(ConditionalExpressionTree::condition)

fun <T> SingleSelector<T, out ConditionalExpressionTree>.falseExpression() = func(ConditionalExpressionTree::falseExpression)
fun <T> OptionalSelector<T, out ConditionalExpressionTree>.falseExpression() = func(ConditionalExpressionTree::falseExpression)
fun <T> ManySelector<T, out ConditionalExpressionTree>.falseExpression() = func(ConditionalExpressionTree::falseExpression)

fun <T> SingleSelector<T, out ConditionalExpressionTree>.questionToken() = func(ConditionalExpressionTree::questionToken)
fun <T> OptionalSelector<T, out ConditionalExpressionTree>.questionToken() = func(ConditionalExpressionTree::questionToken)
fun <T> ManySelector<T, out ConditionalExpressionTree>.questionToken() = func(ConditionalExpressionTree::questionToken)

fun <T> SingleSelector<T, out ConditionalExpressionTree>.trueExpression() = func(ConditionalExpressionTree::trueExpression)
fun <T> OptionalSelector<T, out ConditionalExpressionTree>.trueExpression() = func(ConditionalExpressionTree::trueExpression)
fun <T> ManySelector<T, out ConditionalExpressionTree>.trueExpression() = func(ConditionalExpressionTree::trueExpression)