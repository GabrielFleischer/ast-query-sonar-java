package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.BreakStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out BreakStatementTree>.breakKeyword() = func(BreakStatementTree::breakKeyword)
fun <T> OptionalSelector<T, out BreakStatementTree>.breakKeyword() = func(BreakStatementTree::breakKeyword)
fun <T> ManySelector<T, out BreakStatementTree>.breakKeyword() = func(BreakStatementTree::breakKeyword)

fun <T> SingleSelector<T, out BreakStatementTree>.label() = optFunc(BreakStatementTree::label)
fun <T> OptionalSelector<T, out BreakStatementTree>.label() = optFunc(BreakStatementTree::label)
fun <T> ManySelector<T, out BreakStatementTree>.label() = optFunc(BreakStatementTree::label)

fun <T> SingleSelector<T, out BreakStatementTree>.semicolonToken() = func(BreakStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out BreakStatementTree>.semicolonToken() = func(BreakStatementTree::semicolonToken)
fun <T> ManySelector<T, out BreakStatementTree>.semicolonToken() = func(BreakStatementTree::semicolonToken)