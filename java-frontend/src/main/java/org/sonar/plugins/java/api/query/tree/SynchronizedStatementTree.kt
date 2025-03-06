package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.SynchronizedStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out SynchronizedStatementTree>.block() = func(SynchronizedStatementTree::block)
fun <T> OptionalSelector<T, out SynchronizedStatementTree>.block() = func(SynchronizedStatementTree::block)
fun <T> ManySelector<T, out SynchronizedStatementTree>.block() = func(SynchronizedStatementTree::block)

fun <T> SingleSelector<T, out SynchronizedStatementTree>.closeParenToken() = func(SynchronizedStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out SynchronizedStatementTree>.closeParenToken() = func(SynchronizedStatementTree::closeParenToken)
fun <T> ManySelector<T, out SynchronizedStatementTree>.closeParenToken() = func(SynchronizedStatementTree::closeParenToken)

fun <T> SingleSelector<T, out SynchronizedStatementTree>.expression() = func(SynchronizedStatementTree::expression)
fun <T> OptionalSelector<T, out SynchronizedStatementTree>.expression() = func(SynchronizedStatementTree::expression)
fun <T> ManySelector<T, out SynchronizedStatementTree>.expression() = func(SynchronizedStatementTree::expression)

fun <T> SingleSelector<T, out SynchronizedStatementTree>.openParenToken() = func(SynchronizedStatementTree::openParenToken)
fun <T> OptionalSelector<T, out SynchronizedStatementTree>.openParenToken() = func(SynchronizedStatementTree::openParenToken)
fun <T> ManySelector<T, out SynchronizedStatementTree>.openParenToken() = func(SynchronizedStatementTree::openParenToken)

fun <T> SingleSelector<T, out SynchronizedStatementTree>.synchronizedKeyword() = func(SynchronizedStatementTree::synchronizedKeyword)
fun <T> OptionalSelector<T, out SynchronizedStatementTree>.synchronizedKeyword() = func(SynchronizedStatementTree::synchronizedKeyword)
fun <T> ManySelector<T, out SynchronizedStatementTree>.synchronizedKeyword() = func(SynchronizedStatementTree::synchronizedKeyword)