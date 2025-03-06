package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ReturnStatementTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ReturnStatementTree>.expression() = optFunc(ReturnStatementTree::expression)
fun <T> OptionalSelector<T, out ReturnStatementTree>.expression() = optFunc(ReturnStatementTree::expression)
fun <T> ManySelector<T, out ReturnStatementTree>.expression() = optFunc(ReturnStatementTree::expression)

fun <T> SingleSelector<T, out ReturnStatementTree>.returnKeyword() = func(ReturnStatementTree::returnKeyword)
fun <T> OptionalSelector<T, out ReturnStatementTree>.returnKeyword() = func(ReturnStatementTree::returnKeyword)
fun <T> ManySelector<T, out ReturnStatementTree>.returnKeyword() = func(ReturnStatementTree::returnKeyword)

fun <T> SingleSelector<T, out ReturnStatementTree>.semicolonToken() = func(ReturnStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out ReturnStatementTree>.semicolonToken() = func(ReturnStatementTree::semicolonToken)
fun <T> ManySelector<T, out ReturnStatementTree>.semicolonToken() = func(ReturnStatementTree::semicolonToken)