package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.NewClassTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.optListFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out NewClassTree>.classBody() = optFunc(NewClassTree::classBody)
fun <T> OptionalSelector<T, out NewClassTree>.classBody() = optFunc(NewClassTree::classBody)
fun <T> ManySelector<T, out NewClassTree>.classBody() = optFunc(NewClassTree::classBody)

fun <T> SingleSelector<T, out NewClassTree>.dotToken() = optFunc(NewClassTree::dotToken)
fun <T> OptionalSelector<T, out NewClassTree>.dotToken() = optFunc(NewClassTree::dotToken)
fun <T> ManySelector<T, out NewClassTree>.dotToken() = optFunc(NewClassTree::dotToken)

fun <T> SingleSelector<T, out NewClassTree>.enclosingExpression() = optFunc(NewClassTree::enclosingExpression)
fun <T> OptionalSelector<T, out NewClassTree>.enclosingExpression() = optFunc(NewClassTree::enclosingExpression)
fun <T> ManySelector<T, out NewClassTree>.enclosingExpression() = optFunc(NewClassTree::enclosingExpression)

fun <T> SingleSelector<T, out NewClassTree>.identifier() = func(NewClassTree::identifier)
fun <T> OptionalSelector<T, out NewClassTree>.identifier() = func(NewClassTree::identifier)
fun <T> ManySelector<T, out NewClassTree>.identifier() = func(NewClassTree::identifier)

fun <T> SingleSelector<T, out NewClassTree>.listArguments() = func(NewClassTree::arguments)
fun <T> OptionalSelector<T, out NewClassTree>.listArguments() = func(NewClassTree::arguments)
fun <T> ManySelector<T, out NewClassTree>.listArguments() = func(NewClassTree::arguments)
fun <T> SingleSelector<T, out NewClassTree>.arguments() = listFunc(NewClassTree::arguments)
fun <T> OptionalSelector<T, out NewClassTree>.arguments() = listFunc(NewClassTree::arguments)
fun <T> ManySelector<T, out NewClassTree>.arguments() = listFunc(NewClassTree::arguments)

fun <T> SingleSelector<T, out NewClassTree>.listTypeArguments() = optFunc(NewClassTree::typeArguments)
fun <T> OptionalSelector<T, out NewClassTree>.listTypeArguments() = optFunc(NewClassTree::typeArguments)
fun <T> ManySelector<T, out NewClassTree>.listTypeArguments() = optFunc(NewClassTree::typeArguments)
fun <T> SingleSelector<T, out NewClassTree>.typeArguments() = optListFunc(NewClassTree::typeArguments)
fun <T> OptionalSelector<T, out NewClassTree>.typeArguments() = optListFunc(NewClassTree::typeArguments)
fun <T> ManySelector<T, out NewClassTree>.typeArguments() = optListFunc(NewClassTree::typeArguments)

fun <T> SingleSelector<T, out NewClassTree>.methodSymbol() = func(NewClassTree::methodSymbol)
fun <T> OptionalSelector<T, out NewClassTree>.methodSymbol() = func(NewClassTree::methodSymbol)
fun <T> ManySelector<T, out NewClassTree>.methodSymbol() = func(NewClassTree::methodSymbol)

fun <T> SingleSelector<T, out NewClassTree>.newKeyword() = optFunc(NewClassTree::newKeyword)
fun <T> OptionalSelector<T, out NewClassTree>.newKeyword() = optFunc(NewClassTree::newKeyword)
fun <T> ManySelector<T, out NewClassTree>.newKeyword() = optFunc(NewClassTree::newKeyword)