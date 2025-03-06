package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ThrowStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ThrowStatementTree>.expression() = func(ThrowStatementTree::expression)
fun <T> OptionalSelector<T, out ThrowStatementTree>.expression() = func(ThrowStatementTree::expression)
fun <T> ManySelector<T, out ThrowStatementTree>.expression() = func(ThrowStatementTree::expression)

fun <T> SingleSelector<T, out ThrowStatementTree>.semicolonToken() = func(ThrowStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out ThrowStatementTree>.semicolonToken() = func(ThrowStatementTree::semicolonToken)
fun <T> ManySelector<T, out ThrowStatementTree>.semicolonToken() = func(ThrowStatementTree::semicolonToken)

fun <T> SingleSelector<T, out ThrowStatementTree>.throwKeyword() = func(ThrowStatementTree::throwKeyword)
fun <T> OptionalSelector<T, out ThrowStatementTree>.throwKeyword() = func(ThrowStatementTree::throwKeyword)
fun <T> ManySelector<T, out ThrowStatementTree>.throwKeyword() = func(ThrowStatementTree::throwKeyword)