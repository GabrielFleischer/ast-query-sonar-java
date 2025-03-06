package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.IfStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out IfStatementTree>.closeParenToken() = func(IfStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out IfStatementTree>.closeParenToken() = func(IfStatementTree::closeParenToken)
fun <T> ManySelector<T, out IfStatementTree>.closeParenToken() = func(IfStatementTree::closeParenToken)

fun <T> SingleSelector<T, out IfStatementTree>.condition() = func(IfStatementTree::condition)
fun <T> OptionalSelector<T, out IfStatementTree>.condition() = func(IfStatementTree::condition)
fun <T> ManySelector<T, out IfStatementTree>.condition() = func(IfStatementTree::condition)

fun <T> SingleSelector<T, out IfStatementTree>.elseKeyword() = optFunc(IfStatementTree::elseKeyword)
fun <T> OptionalSelector<T, out IfStatementTree>.elseKeyword() = optFunc(IfStatementTree::elseKeyword)
fun <T> ManySelector<T, out IfStatementTree>.elseKeyword() = optFunc(IfStatementTree::elseKeyword)

fun <T> SingleSelector<T, out IfStatementTree>.elseStatement() = optFunc(IfStatementTree::elseStatement)
fun <T> OptionalSelector<T, out IfStatementTree>.elseStatement() = optFunc(IfStatementTree::elseStatement)
fun <T> ManySelector<T, out IfStatementTree>.elseStatement() = optFunc(IfStatementTree::elseStatement)

fun <T> SingleSelector<T, out IfStatementTree>.ifKeyword() = func(IfStatementTree::ifKeyword)
fun <T> OptionalSelector<T, out IfStatementTree>.ifKeyword() = func(IfStatementTree::ifKeyword)
fun <T> ManySelector<T, out IfStatementTree>.ifKeyword() = func(IfStatementTree::ifKeyword)

fun <T> SingleSelector<T, out IfStatementTree>.openParenToken() = func(IfStatementTree::openParenToken)
fun <T> OptionalSelector<T, out IfStatementTree>.openParenToken() = func(IfStatementTree::openParenToken)
fun <T> ManySelector<T, out IfStatementTree>.openParenToken() = func(IfStatementTree::openParenToken)

fun <T> SingleSelector<T, out IfStatementTree>.thenStatement() = func(IfStatementTree::thenStatement)
fun <T> OptionalSelector<T, out IfStatementTree>.thenStatement() = func(IfStatementTree::thenStatement)
fun <T> ManySelector<T, out IfStatementTree>.thenStatement() = func(IfStatementTree::thenStatement)