package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypeParameterTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out TypeParameterTree>.extendToken() = optFunc(TypeParameterTree::extendToken)
fun <T> OptionalSelector<T, out TypeParameterTree>.extendToken() = optFunc(TypeParameterTree::extendToken)
fun <T> ManySelector<T, out TypeParameterTree>.extendToken() = optFunc(TypeParameterTree::extendToken)

fun <T> SingleSelector<T, out TypeParameterTree>.identifier() = func(TypeParameterTree::identifier)
fun <T> OptionalSelector<T, out TypeParameterTree>.identifier() = func(TypeParameterTree::identifier)
fun <T> ManySelector<T, out TypeParameterTree>.identifier() = func(TypeParameterTree::identifier)

fun <T> SingleSelector<T, out TypeParameterTree>.listBounds() = func(TypeParameterTree::bounds)
fun <T> OptionalSelector<T, out TypeParameterTree>.listBounds() = func(TypeParameterTree::bounds)
fun <T> ManySelector<T, out TypeParameterTree>.listBounds() = func(TypeParameterTree::bounds)
fun <T> SingleSelector<T, out TypeParameterTree>.bounds() = listFunc(TypeParameterTree::bounds)
fun <T> OptionalSelector<T, out TypeParameterTree>.bounds() = listFunc(TypeParameterTree::bounds)
fun <T> ManySelector<T, out TypeParameterTree>.bounds() = listFunc(TypeParameterTree::bounds)

fun <T> SingleSelector<T, out TypeParameterTree>.symbol() = func(TypeParameterTree::symbol)
fun <T> OptionalSelector<T, out TypeParameterTree>.symbol() = func(TypeParameterTree::symbol)
fun <T> ManySelector<T, out TypeParameterTree>.symbol() = func(TypeParameterTree::symbol)