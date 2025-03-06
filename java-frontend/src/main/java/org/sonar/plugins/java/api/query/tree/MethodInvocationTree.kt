package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.MethodInvocationTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.optListFunc
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out MethodInvocationTree>.listArguments() = func(MethodInvocationTree::arguments)
fun <T> OptionalSelector<T, out MethodInvocationTree>.listArguments() = func(MethodInvocationTree::arguments)
fun <T> ManySelector<T, out MethodInvocationTree>.listArguments() = func(MethodInvocationTree::arguments)
fun <T> SingleSelector<T, out MethodInvocationTree>.arguments() = listFunc(MethodInvocationTree::arguments)
fun <T> OptionalSelector<T, out MethodInvocationTree>.arguments() = listFunc(MethodInvocationTree::arguments)
fun <T> ManySelector<T, out MethodInvocationTree>.arguments() = listFunc(MethodInvocationTree::arguments)

fun <T> SingleSelector<T, out MethodInvocationTree>.listTypeArguments() = optFunc(MethodInvocationTree::typeArguments)
fun <T> OptionalSelector<T, out MethodInvocationTree>.listTypeArguments() = optFunc(MethodInvocationTree::typeArguments)
fun <T> ManySelector<T, out MethodInvocationTree>.listTypeArguments() = optFunc(MethodInvocationTree::typeArguments)
fun <T> SingleSelector<T, out MethodInvocationTree>.typeArguments() = optListFunc(MethodInvocationTree::typeArguments)
fun <T> OptionalSelector<T, out MethodInvocationTree>.typeArguments() = optListFunc(MethodInvocationTree::typeArguments)
fun <T> ManySelector<T, out MethodInvocationTree>.typeArguments() = optListFunc(MethodInvocationTree::typeArguments)

fun <T> SingleSelector<T, out MethodInvocationTree>.methodSelect() = func(MethodInvocationTree::methodSelect)
fun <T> OptionalSelector<T, out MethodInvocationTree>.methodSelect() = func(MethodInvocationTree::methodSelect)
fun <T> ManySelector<T, out MethodInvocationTree>.methodSelect() = func(MethodInvocationTree::methodSelect)

fun <T> SingleSelector<T, out MethodInvocationTree>.methodSymbol() = func(MethodInvocationTree::methodSymbol)
fun <T> OptionalSelector<T, out MethodInvocationTree>.methodSymbol() = func(MethodInvocationTree::methodSymbol)
fun <T> ManySelector<T, out MethodInvocationTree>.methodSymbol() = func(MethodInvocationTree::methodSymbol)