package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.NewArrayTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out NewArrayTree>.closeBraceToken() = optFunc(NewArrayTree::closeBraceToken)
fun <T> OptionalSelector<T, out NewArrayTree>.closeBraceToken() = optFunc(NewArrayTree::closeBraceToken)
fun <T> ManySelector<T, out NewArrayTree>.closeBraceToken() = optFunc(NewArrayTree::closeBraceToken)

fun <T> SingleSelector<T, out NewArrayTree>.listDimensions() = func(NewArrayTree::dimensions)
fun <T> OptionalSelector<T, out NewArrayTree>.listDimensions() = func(NewArrayTree::dimensions)
fun <T> ManySelector<T, out NewArrayTree>.listDimensions() = func(NewArrayTree::dimensions)
fun <T> SingleSelector<T, out NewArrayTree>.dimensions() = listFunc(NewArrayTree::dimensions)
fun <T> OptionalSelector<T, out NewArrayTree>.dimensions() = listFunc(NewArrayTree::dimensions)
fun <T> ManySelector<T, out NewArrayTree>.dimensions() = listFunc(NewArrayTree::dimensions)

fun <T> SingleSelector<T, out NewArrayTree>.listInitializers() = func(NewArrayTree::initializers)
fun <T> OptionalSelector<T, out NewArrayTree>.listInitializers() = func(NewArrayTree::initializers)
fun <T> ManySelector<T, out NewArrayTree>.listInitializers() = func(NewArrayTree::initializers)
fun <T> SingleSelector<T, out NewArrayTree>.initializers() = listFunc(NewArrayTree::initializers)
fun <T> OptionalSelector<T, out NewArrayTree>.initializers() = listFunc(NewArrayTree::initializers)
fun <T> ManySelector<T, out NewArrayTree>.initializers() = listFunc(NewArrayTree::initializers)

fun <T> SingleSelector<T, out NewArrayTree>.newKeyword() = optFunc(NewArrayTree::newKeyword)
fun <T> OptionalSelector<T, out NewArrayTree>.newKeyword() = optFunc(NewArrayTree::newKeyword)
fun <T> ManySelector<T, out NewArrayTree>.newKeyword() = optFunc(NewArrayTree::newKeyword)

fun <T> SingleSelector<T, out NewArrayTree>.openBraceToken() = optFunc(NewArrayTree::openBraceToken)
fun <T> OptionalSelector<T, out NewArrayTree>.openBraceToken() = optFunc(NewArrayTree::openBraceToken)
fun <T> ManySelector<T, out NewArrayTree>.openBraceToken() = optFunc(NewArrayTree::openBraceToken)

fun <T> SingleSelector<T, out NewArrayTree>.type() = optFunc(NewArrayTree::type)
fun <T> OptionalSelector<T, out NewArrayTree>.type() = optFunc(NewArrayTree::type)
fun <T> ManySelector<T, out NewArrayTree>.type() = optFunc(NewArrayTree::type)