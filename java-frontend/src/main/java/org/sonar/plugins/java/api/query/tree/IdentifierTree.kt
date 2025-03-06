package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.IdentifierTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out IdentifierTree>.identifierToken() = func(IdentifierTree::identifierToken)
fun <T> OptionalSelector<T, out IdentifierTree>.identifierToken() = func(IdentifierTree::identifierToken)
fun <T> ManySelector<T, out IdentifierTree>.identifierToken() = func(IdentifierTree::identifierToken)

fun <T> SingleSelector<T, out IdentifierTree>.isUnnamedVariable() = func(IdentifierTree::isUnnamedVariable)
fun <T> OptionalSelector<T, out IdentifierTree>.isUnnamedVariable() = func(IdentifierTree::isUnnamedVariable)
fun <T> ManySelector<T, out IdentifierTree>.isUnnamedVariable() = func(IdentifierTree::isUnnamedVariable)

fun <T> SingleSelector<T, out IdentifierTree>.name() = func(IdentifierTree::name)
fun <T> OptionalSelector<T, out IdentifierTree>.name() = func(IdentifierTree::name)
fun <T> ManySelector<T, out IdentifierTree>.name() = func(IdentifierTree::name)

fun <T> SingleSelector<T, out IdentifierTree>.symbol() = func(IdentifierTree::symbol)
fun <T> OptionalSelector<T, out IdentifierTree>.symbol() = func(IdentifierTree::symbol)
fun <T> ManySelector<T, out IdentifierTree>.symbol() = func(IdentifierTree::symbol)