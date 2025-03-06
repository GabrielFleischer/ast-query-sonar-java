package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.WhileStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out WhileStatementTree>.closeParenToken() = func(WhileStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out WhileStatementTree>.closeParenToken() = func(WhileStatementTree::closeParenToken)
fun <T> ManySelector<T, out WhileStatementTree>.closeParenToken() = func(WhileStatementTree::closeParenToken)

fun <T> SingleSelector<T, out WhileStatementTree>.condition() = func(WhileStatementTree::condition)
fun <T> OptionalSelector<T, out WhileStatementTree>.condition() = func(WhileStatementTree::condition)
fun <T> ManySelector<T, out WhileStatementTree>.condition() = func(WhileStatementTree::condition)

fun <T> SingleSelector<T, out WhileStatementTree>.openParenToken() = func(WhileStatementTree::openParenToken)
fun <T> OptionalSelector<T, out WhileStatementTree>.openParenToken() = func(WhileStatementTree::openParenToken)
fun <T> ManySelector<T, out WhileStatementTree>.openParenToken() = func(WhileStatementTree::openParenToken)

fun <T> SingleSelector<T, out WhileStatementTree>.statement() = func(WhileStatementTree::statement)
fun <T> OptionalSelector<T, out WhileStatementTree>.statement() = func(WhileStatementTree::statement)
fun <T> ManySelector<T, out WhileStatementTree>.statement() = func(WhileStatementTree::statement)

fun <T> SingleSelector<T, out WhileStatementTree>.whileKeyword() = func(WhileStatementTree::whileKeyword)
fun <T> OptionalSelector<T, out WhileStatementTree>.whileKeyword() = func(WhileStatementTree::whileKeyword)
fun <T> ManySelector<T, out WhileStatementTree>.whileKeyword() = func(WhileStatementTree::whileKeyword)