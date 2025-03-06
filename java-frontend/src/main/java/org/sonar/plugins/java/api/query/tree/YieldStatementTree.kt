package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.YieldStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out YieldStatementTree>.expression() = func(YieldStatementTree::expression)
fun <T> OptionalSelector<T, out YieldStatementTree>.expression() = func(YieldStatementTree::expression)
fun <T> ManySelector<T, out YieldStatementTree>.expression() = func(YieldStatementTree::expression)

fun <T> SingleSelector<T, out YieldStatementTree>.semicolonToken() = func(YieldStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out YieldStatementTree>.semicolonToken() = func(YieldStatementTree::semicolonToken)
fun <T> ManySelector<T, out YieldStatementTree>.semicolonToken() = func(YieldStatementTree::semicolonToken)

fun <T> SingleSelector<T, out YieldStatementTree>.yieldKeyword() = optFunc(YieldStatementTree::yieldKeyword)
fun <T> OptionalSelector<T, out YieldStatementTree>.yieldKeyword() = optFunc(YieldStatementTree::yieldKeyword)
fun <T> ManySelector<T, out YieldStatementTree>.yieldKeyword() = optFunc(YieldStatementTree::yieldKeyword)