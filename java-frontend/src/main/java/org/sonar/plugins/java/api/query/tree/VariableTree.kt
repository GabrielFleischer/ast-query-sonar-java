package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.VariableTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out VariableTree>.endToken() = optFunc(VariableTree::endToken)
fun <T> OptionalSelector<T, out VariableTree>.endToken() = optFunc(VariableTree::endToken)
fun <T> ManySelector<T, out VariableTree>.endToken() = optFunc(VariableTree::endToken)

fun <T> SingleSelector<T, out VariableTree>.equalToken() = optFunc(VariableTree::equalToken)
fun <T> OptionalSelector<T, out VariableTree>.equalToken() = optFunc(VariableTree::equalToken)
fun <T> ManySelector<T, out VariableTree>.equalToken() = optFunc(VariableTree::equalToken)

fun <T> SingleSelector<T, out VariableTree>.initializer() = optFunc(VariableTree::initializer)
fun <T> OptionalSelector<T, out VariableTree>.initializer() = optFunc(VariableTree::initializer)
fun <T> ManySelector<T, out VariableTree>.initializer() = optFunc(VariableTree::initializer)

fun <T> SingleSelector<T, out VariableTree>.listModifiers() = func(VariableTree::modifiers)
fun <T> OptionalSelector<T, out VariableTree>.listModifiers() = func(VariableTree::modifiers)
fun <T> ManySelector<T, out VariableTree>.listModifiers() = func(VariableTree::modifiers)
fun <T> SingleSelector<T, out VariableTree>.modifiers() = listFunc(VariableTree::modifiers)
fun <T> OptionalSelector<T, out VariableTree>.modifiers() = listFunc(VariableTree::modifiers)
fun <T> ManySelector<T, out VariableTree>.modifiers() = listFunc(VariableTree::modifiers)

fun <T> SingleSelector<T, out VariableTree>.simpleName() = func(VariableTree::simpleName)
fun <T> OptionalSelector<T, out VariableTree>.simpleName() = func(VariableTree::simpleName)
fun <T> ManySelector<T, out VariableTree>.simpleName() = func(VariableTree::simpleName)

fun <T> SingleSelector<T, out VariableTree>.symbol() = func(VariableTree::symbol)
fun <T> OptionalSelector<T, out VariableTree>.symbol() = func(VariableTree::symbol)
fun <T> ManySelector<T, out VariableTree>.symbol() = func(VariableTree::symbol)

fun <T> SingleSelector<T, out VariableTree>.type() = func(VariableTree::type)
fun <T> OptionalSelector<T, out VariableTree>.type() = func(VariableTree::type)
fun <T> ManySelector<T, out VariableTree>.type() = func(VariableTree::type)