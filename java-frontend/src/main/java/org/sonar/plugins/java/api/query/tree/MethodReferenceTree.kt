package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.MethodReferenceTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.optListFunc

fun <T> SingleSelector<T, out MethodReferenceTree>.doubleColon() = func(MethodReferenceTree::doubleColon)
fun <T> OptionalSelector<T, out MethodReferenceTree>.doubleColon() = func(MethodReferenceTree::doubleColon)
fun <T> ManySelector<T, out MethodReferenceTree>.doubleColon() = func(MethodReferenceTree::doubleColon)

fun <T> SingleSelector<T, out MethodReferenceTree>.expression() = func(MethodReferenceTree::expression)
fun <T> OptionalSelector<T, out MethodReferenceTree>.expression() = func(MethodReferenceTree::expression)
fun <T> ManySelector<T, out MethodReferenceTree>.expression() = func(MethodReferenceTree::expression)

fun <T> SingleSelector<T, out MethodReferenceTree>.listTypeArguments() = optFunc(MethodReferenceTree::typeArguments)
fun <T> OptionalSelector<T, out MethodReferenceTree>.listTypeArguments() = optFunc(MethodReferenceTree::typeArguments)
fun <T> ManySelector<T, out MethodReferenceTree>.listTypeArguments() = optFunc(MethodReferenceTree::typeArguments)
fun <T> SingleSelector<T, out MethodReferenceTree>.typeArguments() = optListFunc(MethodReferenceTree::typeArguments)
fun <T> OptionalSelector<T, out MethodReferenceTree>.typeArguments() = optListFunc(MethodReferenceTree::typeArguments)
fun <T> ManySelector<T, out MethodReferenceTree>.typeArguments() = optListFunc(MethodReferenceTree::typeArguments)

fun <T> SingleSelector<T, out MethodReferenceTree>.method() = func(MethodReferenceTree::method)
fun <T> OptionalSelector<T, out MethodReferenceTree>.method() = func(MethodReferenceTree::method)
fun <T> ManySelector<T, out MethodReferenceTree>.method() = func(MethodReferenceTree::method)