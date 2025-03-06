package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.LambdaExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out LambdaExpressionTree>.arrowToken() = func(LambdaExpressionTree::arrowToken)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.arrowToken() = func(LambdaExpressionTree::arrowToken)
fun <T> ManySelector<T, out LambdaExpressionTree>.arrowToken() = func(LambdaExpressionTree::arrowToken)

fun <T> SingleSelector<T, out LambdaExpressionTree>.body() = func(LambdaExpressionTree::body)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.body() = func(LambdaExpressionTree::body)
fun <T> ManySelector<T, out LambdaExpressionTree>.body() = func(LambdaExpressionTree::body)

fun <T> SingleSelector<T, out LambdaExpressionTree>.cfg() = func(LambdaExpressionTree::cfg)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.cfg() = func(LambdaExpressionTree::cfg)
fun <T> ManySelector<T, out LambdaExpressionTree>.cfg() = func(LambdaExpressionTree::cfg)

fun <T> SingleSelector<T, out LambdaExpressionTree>.closeParenToken() = optFunc(LambdaExpressionTree::closeParenToken)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.closeParenToken() = optFunc(LambdaExpressionTree::closeParenToken)
fun <T> ManySelector<T, out LambdaExpressionTree>.closeParenToken() = optFunc(LambdaExpressionTree::closeParenToken)

fun <T> SingleSelector<T, out LambdaExpressionTree>.listParameters() = func(LambdaExpressionTree::parameters)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.listParameters() = func(LambdaExpressionTree::parameters)
fun <T> ManySelector<T, out LambdaExpressionTree>.listParameters() = func(LambdaExpressionTree::parameters)
fun <T> SingleSelector<T, out LambdaExpressionTree>.parameters() = listFunc(LambdaExpressionTree::parameters)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.parameters() = listFunc(LambdaExpressionTree::parameters)
fun <T> ManySelector<T, out LambdaExpressionTree>.parameters() = listFunc(LambdaExpressionTree::parameters)

fun <T> SingleSelector<T, out LambdaExpressionTree>.openParenToken() = optFunc(LambdaExpressionTree::openParenToken)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.openParenToken() = optFunc(LambdaExpressionTree::openParenToken)
fun <T> ManySelector<T, out LambdaExpressionTree>.openParenToken() = optFunc(LambdaExpressionTree::openParenToken)

fun <T> SingleSelector<T, out LambdaExpressionTree>.symbol() = func(LambdaExpressionTree::symbol)
fun <T> OptionalSelector<T, out LambdaExpressionTree>.symbol() = func(LambdaExpressionTree::symbol)
fun <T> ManySelector<T, out LambdaExpressionTree>.symbol() = func(LambdaExpressionTree::symbol)