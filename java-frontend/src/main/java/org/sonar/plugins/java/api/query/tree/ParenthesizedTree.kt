package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ParenthesizedTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ParenthesizedTree>.closeParenToken() = func(ParenthesizedTree::closeParenToken)
fun <T> OptionalSelector<T, out ParenthesizedTree>.closeParenToken() = func(ParenthesizedTree::closeParenToken)
fun <T> ManySelector<T, out ParenthesizedTree>.closeParenToken() = func(ParenthesizedTree::closeParenToken)

fun <T> SingleSelector<T, out ParenthesizedTree>.expression() = func(ParenthesizedTree::expression)
fun <T> OptionalSelector<T, out ParenthesizedTree>.expression() = func(ParenthesizedTree::expression)
fun <T> ManySelector<T, out ParenthesizedTree>.expression() = func(ParenthesizedTree::expression)

fun <T> SingleSelector<T, out ParenthesizedTree>.openParenToken() = func(ParenthesizedTree::openParenToken)
fun <T> OptionalSelector<T, out ParenthesizedTree>.openParenToken() = func(ParenthesizedTree::openParenToken)
fun <T> ManySelector<T, out ParenthesizedTree>.openParenToken() = func(ParenthesizedTree::openParenToken)