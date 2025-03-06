package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.CatchTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out CatchTree>.block() = func(CatchTree::block)
fun <T> OptionalSelector<T, out CatchTree>.block() = func(CatchTree::block)
fun <T> ManySelector<T, out CatchTree>.block() = func(CatchTree::block)

fun <T> SingleSelector<T, out CatchTree>.catchKeyword() = func(CatchTree::catchKeyword)
fun <T> OptionalSelector<T, out CatchTree>.catchKeyword() = func(CatchTree::catchKeyword)
fun <T> ManySelector<T, out CatchTree>.catchKeyword() = func(CatchTree::catchKeyword)

fun <T> SingleSelector<T, out CatchTree>.closeParenToken() = func(CatchTree::closeParenToken)
fun <T> OptionalSelector<T, out CatchTree>.closeParenToken() = func(CatchTree::closeParenToken)
fun <T> ManySelector<T, out CatchTree>.closeParenToken() = func(CatchTree::closeParenToken)

fun <T> SingleSelector<T, out CatchTree>.openParenToken() = func(CatchTree::openParenToken)
fun <T> OptionalSelector<T, out CatchTree>.openParenToken() = func(CatchTree::openParenToken)
fun <T> ManySelector<T, out CatchTree>.openParenToken() = func(CatchTree::openParenToken)

fun <T> SingleSelector<T, out CatchTree>.parameter() = func(CatchTree::parameter)
fun <T> OptionalSelector<T, out CatchTree>.parameter() = func(CatchTree::parameter)
fun <T> ManySelector<T, out CatchTree>.parameter() = func(CatchTree::parameter)