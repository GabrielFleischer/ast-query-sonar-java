package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out MemberSelectExpressionTree>.expression() = func(MemberSelectExpressionTree::expression)
fun <T> OptionalSelector<T, out MemberSelectExpressionTree>.expression() = func(MemberSelectExpressionTree::expression)
fun <T> ManySelector<T, out MemberSelectExpressionTree>.expression() = func(MemberSelectExpressionTree::expression)

fun <T> SingleSelector<T, out MemberSelectExpressionTree>.identifier() = func(MemberSelectExpressionTree::identifier)
fun <T> OptionalSelector<T, out MemberSelectExpressionTree>.identifier() = func(MemberSelectExpressionTree::identifier)
fun <T> ManySelector<T, out MemberSelectExpressionTree>.identifier() = func(MemberSelectExpressionTree::identifier)

fun <T> SingleSelector<T, out MemberSelectExpressionTree>.operatorToken() = func(MemberSelectExpressionTree::operatorToken)
fun <T> OptionalSelector<T, out MemberSelectExpressionTree>.operatorToken() = func(MemberSelectExpressionTree::operatorToken)
fun <T> ManySelector<T, out MemberSelectExpressionTree>.operatorToken() = func(MemberSelectExpressionTree::operatorToken)