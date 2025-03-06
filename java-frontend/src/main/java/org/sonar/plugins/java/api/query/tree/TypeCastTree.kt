package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypeCastTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out TypeCastTree>.andToken() = optFunc(TypeCastTree::andToken)
fun <T> OptionalSelector<T, out TypeCastTree>.andToken() = optFunc(TypeCastTree::andToken)
fun <T> ManySelector<T, out TypeCastTree>.andToken() = optFunc(TypeCastTree::andToken)

fun <T> SingleSelector<T, out TypeCastTree>.closeParenToken() = func(TypeCastTree::closeParenToken)
fun <T> OptionalSelector<T, out TypeCastTree>.closeParenToken() = func(TypeCastTree::closeParenToken)
fun <T> ManySelector<T, out TypeCastTree>.closeParenToken() = func(TypeCastTree::closeParenToken)

fun <T> SingleSelector<T, out TypeCastTree>.expression() = func(TypeCastTree::expression)
fun <T> OptionalSelector<T, out TypeCastTree>.expression() = func(TypeCastTree::expression)
fun <T> ManySelector<T, out TypeCastTree>.expression() = func(TypeCastTree::expression)

fun <T> SingleSelector<T, out TypeCastTree>.listBounds() = func(TypeCastTree::bounds)
fun <T> OptionalSelector<T, out TypeCastTree>.listBounds() = func(TypeCastTree::bounds)
fun <T> ManySelector<T, out TypeCastTree>.listBounds() = func(TypeCastTree::bounds)
fun <T> SingleSelector<T, out TypeCastTree>.bounds() = listFunc(TypeCastTree::bounds)
fun <T> OptionalSelector<T, out TypeCastTree>.bounds() = listFunc(TypeCastTree::bounds)
fun <T> ManySelector<T, out TypeCastTree>.bounds() = listFunc(TypeCastTree::bounds)

fun <T> SingleSelector<T, out TypeCastTree>.openParenToken() = func(TypeCastTree::openParenToken)
fun <T> OptionalSelector<T, out TypeCastTree>.openParenToken() = func(TypeCastTree::openParenToken)
fun <T> ManySelector<T, out TypeCastTree>.openParenToken() = func(TypeCastTree::openParenToken)

fun <T> SingleSelector<T, out TypeCastTree>.type() = func(TypeCastTree::type)
fun <T> OptionalSelector<T, out TypeCastTree>.type() = func(TypeCastTree::type)
fun <T> ManySelector<T, out TypeCastTree>.type() = func(TypeCastTree::type)