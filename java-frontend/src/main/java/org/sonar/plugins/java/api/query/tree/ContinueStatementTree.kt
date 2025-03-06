package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ContinueStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out ContinueStatementTree>.continueKeyword() = func(ContinueStatementTree::continueKeyword)
fun <T> OptionalSelector<T, out ContinueStatementTree>.continueKeyword() = func(ContinueStatementTree::continueKeyword)
fun <T> ManySelector<T, out ContinueStatementTree>.continueKeyword() = func(ContinueStatementTree::continueKeyword)

fun <T> SingleSelector<T, out ContinueStatementTree>.label() = optFunc(ContinueStatementTree::label)
fun <T> OptionalSelector<T, out ContinueStatementTree>.label() = optFunc(ContinueStatementTree::label)
fun <T> ManySelector<T, out ContinueStatementTree>.label() = optFunc(ContinueStatementTree::label)

fun <T> SingleSelector<T, out ContinueStatementTree>.semicolonToken() = func(ContinueStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out ContinueStatementTree>.semicolonToken() = func(ContinueStatementTree::semicolonToken)
fun <T> ManySelector<T, out ContinueStatementTree>.semicolonToken() = func(ContinueStatementTree::semicolonToken)