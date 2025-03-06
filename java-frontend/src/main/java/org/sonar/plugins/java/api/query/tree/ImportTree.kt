package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ImportTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out ImportTree>.importKeyword() = func(ImportTree::importKeyword)
fun <T> OptionalSelector<T, out ImportTree>.importKeyword() = func(ImportTree::importKeyword)
fun <T> ManySelector<T, out ImportTree>.importKeyword() = func(ImportTree::importKeyword)

fun <T> SingleSelector<T, out ImportTree>.isStatic() = func(ImportTree::isStatic)
fun <T> OptionalSelector<T, out ImportTree>.isStatic() = func(ImportTree::isStatic)
fun <T> ManySelector<T, out ImportTree>.isStatic() = func(ImportTree::isStatic)

fun <T> SingleSelector<T, out ImportTree>.qualifiedIdentifier() = func(ImportTree::qualifiedIdentifier)
fun <T> OptionalSelector<T, out ImportTree>.qualifiedIdentifier() = func(ImportTree::qualifiedIdentifier)
fun <T> ManySelector<T, out ImportTree>.qualifiedIdentifier() = func(ImportTree::qualifiedIdentifier)

fun <T> SingleSelector<T, out ImportTree>.semicolonToken() = func(ImportTree::semicolonToken)
fun <T> OptionalSelector<T, out ImportTree>.semicolonToken() = func(ImportTree::semicolonToken)
fun <T> ManySelector<T, out ImportTree>.semicolonToken() = func(ImportTree::semicolonToken)

fun <T> SingleSelector<T, out ImportTree>.staticKeyword() = optFunc(ImportTree::staticKeyword)
fun <T> OptionalSelector<T, out ImportTree>.staticKeyword() = optFunc(ImportTree::staticKeyword)
fun <T> ManySelector<T, out ImportTree>.staticKeyword() = optFunc(ImportTree::staticKeyword)

fun <T> SingleSelector<T, out ImportTree>.symbol() = optFunc(ImportTree::symbol)
fun <T> OptionalSelector<T, out ImportTree>.symbol() = optFunc(ImportTree::symbol)
fun <T> ManySelector<T, out ImportTree>.symbol() = optFunc(ImportTree::symbol)