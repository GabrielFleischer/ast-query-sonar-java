package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.MethodTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out MethodTree>.block() = optFunc(MethodTree::block)
fun <T> OptionalSelector<T, out MethodTree>.block() = optFunc(MethodTree::block)
fun <T> ManySelector<T, out MethodTree>.block() = optFunc(MethodTree::block)

fun <T> SingleSelector<T, out MethodTree>.cfg() = optFunc(MethodTree::cfg)
fun <T> OptionalSelector<T, out MethodTree>.cfg() = optFunc(MethodTree::cfg)
fun <T> ManySelector<T, out MethodTree>.cfg() = optFunc(MethodTree::cfg)

fun <T> SingleSelector<T, out MethodTree>.closeParenToken() = optFunc(MethodTree::closeParenToken)
fun <T> OptionalSelector<T, out MethodTree>.closeParenToken() = optFunc(MethodTree::closeParenToken)
fun <T> ManySelector<T, out MethodTree>.closeParenToken() = optFunc(MethodTree::closeParenToken)

fun <T> SingleSelector<T, out MethodTree>.defaultToken() = optFunc(MethodTree::defaultToken)
fun <T> OptionalSelector<T, out MethodTree>.defaultToken() = optFunc(MethodTree::defaultToken)
fun <T> ManySelector<T, out MethodTree>.defaultToken() = optFunc(MethodTree::defaultToken)

fun <T> SingleSelector<T, out MethodTree>.defaultValue() = optFunc(MethodTree::defaultValue)
fun <T> OptionalSelector<T, out MethodTree>.defaultValue() = optFunc(MethodTree::defaultValue)
fun <T> ManySelector<T, out MethodTree>.defaultValue() = optFunc(MethodTree::defaultValue)

fun <T> SingleSelector<T, out MethodTree>.isOverriding() = optFunc(MethodTree::isOverriding)
fun <T> OptionalSelector<T, out MethodTree>.isOverriding() = optFunc(MethodTree::isOverriding)
fun <T> ManySelector<T, out MethodTree>.isOverriding() = optFunc(MethodTree::isOverriding)

fun <T> SingleSelector<T, out MethodTree>.listModifiers() = func(MethodTree::modifiers)
fun <T> OptionalSelector<T, out MethodTree>.listModifiers() = func(MethodTree::modifiers)
fun <T> ManySelector<T, out MethodTree>.listModifiers() = func(MethodTree::modifiers)
fun <T> SingleSelector<T, out MethodTree>.modifiers() = listFunc(MethodTree::modifiers)
fun <T> OptionalSelector<T, out MethodTree>.modifiers() = listFunc(MethodTree::modifiers)
fun <T> ManySelector<T, out MethodTree>.modifiers() = listFunc(MethodTree::modifiers)

fun <T> SingleSelector<T, out MethodTree>.listParameters() = func(MethodTree::parameters)
fun <T> OptionalSelector<T, out MethodTree>.listParameters() = func(MethodTree::parameters)
fun <T> ManySelector<T, out MethodTree>.listParameters() = func(MethodTree::parameters)
fun <T> SingleSelector<T, out MethodTree>.parameters() = listFunc(MethodTree::parameters)
fun <T> OptionalSelector<T, out MethodTree>.parameters() = listFunc(MethodTree::parameters)
fun <T> ManySelector<T, out MethodTree>.parameters() = listFunc(MethodTree::parameters)

fun <T> SingleSelector<T, out MethodTree>.listThrowsClauses() = func(MethodTree::throwsClauses)
fun <T> OptionalSelector<T, out MethodTree>.listThrowsClauses() = func(MethodTree::throwsClauses)
fun <T> ManySelector<T, out MethodTree>.listThrowsClauses() = func(MethodTree::throwsClauses)
fun <T> SingleSelector<T, out MethodTree>.throwsClauses() = listFunc(MethodTree::throwsClauses)
fun <T> OptionalSelector<T, out MethodTree>.throwsClauses() = listFunc(MethodTree::throwsClauses)
fun <T> ManySelector<T, out MethodTree>.throwsClauses() = listFunc(MethodTree::throwsClauses)

fun <T> SingleSelector<T, out MethodTree>.listTypeParameters() = func(MethodTree::typeParameters)
fun <T> OptionalSelector<T, out MethodTree>.listTypeParameters() = func(MethodTree::typeParameters)
fun <T> ManySelector<T, out MethodTree>.listTypeParameters() = func(MethodTree::typeParameters)
fun <T> SingleSelector<T, out MethodTree>.typeParameters() = listFunc(MethodTree::typeParameters)
fun <T> OptionalSelector<T, out MethodTree>.typeParameters() = listFunc(MethodTree::typeParameters)
fun <T> ManySelector<T, out MethodTree>.typeParameters() = listFunc(MethodTree::typeParameters)

fun <T> SingleSelector<T, out MethodTree>.openParenToken() = optFunc(MethodTree::openParenToken)
fun <T> OptionalSelector<T, out MethodTree>.openParenToken() = optFunc(MethodTree::openParenToken)
fun <T> ManySelector<T, out MethodTree>.openParenToken() = optFunc(MethodTree::openParenToken)

fun <T> SingleSelector<T, out MethodTree>.returnType() = optFunc(MethodTree::returnType)
fun <T> OptionalSelector<T, out MethodTree>.returnType() = optFunc(MethodTree::returnType)
fun <T> ManySelector<T, out MethodTree>.returnType() = optFunc(MethodTree::returnType)

fun <T> SingleSelector<T, out MethodTree>.semicolonToken() = optFunc(MethodTree::semicolonToken)
fun <T> OptionalSelector<T, out MethodTree>.semicolonToken() = optFunc(MethodTree::semicolonToken)
fun <T> ManySelector<T, out MethodTree>.semicolonToken() = optFunc(MethodTree::semicolonToken)

fun <T> SingleSelector<T, out MethodTree>.simpleName() = func(MethodTree::simpleName)
fun <T> OptionalSelector<T, out MethodTree>.simpleName() = func(MethodTree::simpleName)
fun <T> ManySelector<T, out MethodTree>.simpleName() = func(MethodTree::simpleName)

fun <T> SingleSelector<T, out MethodTree>.symbol() = func(MethodTree::symbol)
fun <T> OptionalSelector<T, out MethodTree>.symbol() = func(MethodTree::symbol)
fun <T> ManySelector<T, out MethodTree>.symbol() = func(MethodTree::symbol)

fun <T> SingleSelector<T, out MethodTree>.throwsToken() = func(MethodTree::throwsToken)
fun <T> OptionalSelector<T, out MethodTree>.throwsToken() = func(MethodTree::throwsToken)
fun <T> ManySelector<T, out MethodTree>.throwsToken() = func(MethodTree::throwsToken)