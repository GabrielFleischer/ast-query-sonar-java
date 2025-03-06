package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.AssertStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out AssertStatementTree>.assertKeyword() = func(AssertStatementTree::assertKeyword)
fun <T> OptionalSelector<T, out AssertStatementTree>.assertKeyword() = func(AssertStatementTree::assertKeyword)
fun <T> ManySelector<T, out AssertStatementTree>.assertKeyword() = func(AssertStatementTree::assertKeyword)

fun <T> SingleSelector<T, out AssertStatementTree>.colonToken() = optFunc(AssertStatementTree::colonToken)
fun <T> OptionalSelector<T, out AssertStatementTree>.colonToken() = optFunc(AssertStatementTree::colonToken)
fun <T> ManySelector<T, out AssertStatementTree>.colonToken() = optFunc(AssertStatementTree::colonToken)

fun <T> SingleSelector<T, out AssertStatementTree>.condition() = func(AssertStatementTree::condition)
fun <T> OptionalSelector<T, out AssertStatementTree>.condition() = func(AssertStatementTree::condition)
fun <T> ManySelector<T, out AssertStatementTree>.condition() = func(AssertStatementTree::condition)

fun <T> SingleSelector<T, out AssertStatementTree>.detail() = optFunc(AssertStatementTree::detail)
fun <T> OptionalSelector<T, out AssertStatementTree>.detail() = optFunc(AssertStatementTree::detail)
fun <T> ManySelector<T, out AssertStatementTree>.detail() = optFunc(AssertStatementTree::detail)

fun <T> SingleSelector<T, out AssertStatementTree>.semicolonToken() = func(AssertStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out AssertStatementTree>.semicolonToken() = func(AssertStatementTree::semicolonToken)
fun <T> ManySelector<T, out AssertStatementTree>.semicolonToken() = func(AssertStatementTree::semicolonToken)