package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.EnumConstantTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out EnumConstantTree>.initializer() = func(EnumConstantTree::initializer)
fun <T> OptionalSelector<T, out EnumConstantTree>.initializer() = func(EnumConstantTree::initializer)
fun <T> ManySelector<T, out EnumConstantTree>.initializer() = func(EnumConstantTree::initializer)

fun <T> SingleSelector<T, out EnumConstantTree>.listModifiers() = func(EnumConstantTree::modifiers)
fun <T> OptionalSelector<T, out EnumConstantTree>.listModifiers() = func(EnumConstantTree::modifiers)
fun <T> ManySelector<T, out EnumConstantTree>.listModifiers() = func(EnumConstantTree::modifiers)
fun <T> SingleSelector<T, out EnumConstantTree>.modifiers() = listFunc(EnumConstantTree::modifiers)
fun <T> OptionalSelector<T, out EnumConstantTree>.modifiers() = listFunc(EnumConstantTree::modifiers)
fun <T> ManySelector<T, out EnumConstantTree>.modifiers() = listFunc(EnumConstantTree::modifiers)

fun <T> SingleSelector<T, out EnumConstantTree>.separatorToken() = optFunc(EnumConstantTree::separatorToken)
fun <T> OptionalSelector<T, out EnumConstantTree>.separatorToken() = optFunc(EnumConstantTree::separatorToken)
fun <T> ManySelector<T, out EnumConstantTree>.separatorToken() = optFunc(EnumConstantTree::separatorToken)

fun <T> SingleSelector<T, out EnumConstantTree>.simpleName() = func(EnumConstantTree::simpleName)
fun <T> OptionalSelector<T, out EnumConstantTree>.simpleName() = func(EnumConstantTree::simpleName)
fun <T> ManySelector<T, out EnumConstantTree>.simpleName() = func(EnumConstantTree::simpleName)