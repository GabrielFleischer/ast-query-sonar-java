package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.DoWhileStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out DoWhileStatementTree>.closeParenToken() = func(DoWhileStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.closeParenToken() = func(DoWhileStatementTree::closeParenToken)
fun <T> ManySelector<T, out DoWhileStatementTree>.closeParenToken() = func(DoWhileStatementTree::closeParenToken)

fun <T> SingleSelector<T, out DoWhileStatementTree>.condition() = func(DoWhileStatementTree::condition)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.condition() = func(DoWhileStatementTree::condition)
fun <T> ManySelector<T, out DoWhileStatementTree>.condition() = func(DoWhileStatementTree::condition)

fun <T> SingleSelector<T, out DoWhileStatementTree>.doKeyword() = func(DoWhileStatementTree::doKeyword)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.doKeyword() = func(DoWhileStatementTree::doKeyword)
fun <T> ManySelector<T, out DoWhileStatementTree>.doKeyword() = func(DoWhileStatementTree::doKeyword)

fun <T> SingleSelector<T, out DoWhileStatementTree>.openParenToken() = func(DoWhileStatementTree::openParenToken)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.openParenToken() = func(DoWhileStatementTree::openParenToken)
fun <T> ManySelector<T, out DoWhileStatementTree>.openParenToken() = func(DoWhileStatementTree::openParenToken)

fun <T> SingleSelector<T, out DoWhileStatementTree>.semicolonToken() = func(DoWhileStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.semicolonToken() = func(DoWhileStatementTree::semicolonToken)
fun <T> ManySelector<T, out DoWhileStatementTree>.semicolonToken() = func(DoWhileStatementTree::semicolonToken)

fun <T> SingleSelector<T, out DoWhileStatementTree>.statement() = func(DoWhileStatementTree::statement)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.statement() = func(DoWhileStatementTree::statement)
fun <T> ManySelector<T, out DoWhileStatementTree>.statement() = func(DoWhileStatementTree::statement)

fun <T> SingleSelector<T, out DoWhileStatementTree>.whileKeyword() = func(DoWhileStatementTree::whileKeyword)
fun <T> OptionalSelector<T, out DoWhileStatementTree>.whileKeyword() = func(DoWhileStatementTree::whileKeyword)
fun <T> ManySelector<T, out DoWhileStatementTree>.whileKeyword() = func(DoWhileStatementTree::whileKeyword)