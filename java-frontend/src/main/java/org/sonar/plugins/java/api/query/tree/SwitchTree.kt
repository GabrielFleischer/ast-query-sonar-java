package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.SwitchTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out SwitchTree>.closeBraceToken() = func(SwitchTree::closeBraceToken)
fun <T> OptionalSelector<T, out SwitchTree>.closeBraceToken() = func(SwitchTree::closeBraceToken)
fun <T> ManySelector<T, out SwitchTree>.closeBraceToken() = func(SwitchTree::closeBraceToken)

fun <T> SingleSelector<T, out SwitchTree>.closeParenToken() = func(SwitchTree::closeParenToken)
fun <T> OptionalSelector<T, out SwitchTree>.closeParenToken() = func(SwitchTree::closeParenToken)
fun <T> ManySelector<T, out SwitchTree>.closeParenToken() = func(SwitchTree::closeParenToken)

fun <T> SingleSelector<T, out SwitchTree>.expression() = func(SwitchTree::expression)
fun <T> OptionalSelector<T, out SwitchTree>.expression() = func(SwitchTree::expression)
fun <T> ManySelector<T, out SwitchTree>.expression() = func(SwitchTree::expression)

fun <T> SingleSelector<T, out SwitchTree>.listCases() = func(SwitchTree::cases)
fun <T> OptionalSelector<T, out SwitchTree>.listCases() = func(SwitchTree::cases)
fun <T> ManySelector<T, out SwitchTree>.listCases() = func(SwitchTree::cases)
fun <T> SingleSelector<T, out SwitchTree>.cases() = listFunc(SwitchTree::cases)
fun <T> OptionalSelector<T, out SwitchTree>.cases() = listFunc(SwitchTree::cases)
fun <T> ManySelector<T, out SwitchTree>.cases() = listFunc(SwitchTree::cases)

fun <T> SingleSelector<T, out SwitchTree>.openBraceToken() = func(SwitchTree::openBraceToken)
fun <T> OptionalSelector<T, out SwitchTree>.openBraceToken() = func(SwitchTree::openBraceToken)
fun <T> ManySelector<T, out SwitchTree>.openBraceToken() = func(SwitchTree::openBraceToken)

fun <T> SingleSelector<T, out SwitchTree>.openParenToken() = func(SwitchTree::openParenToken)
fun <T> OptionalSelector<T, out SwitchTree>.openParenToken() = func(SwitchTree::openParenToken)
fun <T> ManySelector<T, out SwitchTree>.openParenToken() = func(SwitchTree::openParenToken)

fun <T> SingleSelector<T, out SwitchTree>.switchKeyword() = func(SwitchTree::switchKeyword)
fun <T> OptionalSelector<T, out SwitchTree>.switchKeyword() = func(SwitchTree::switchKeyword)
fun <T> ManySelector<T, out SwitchTree>.switchKeyword() = func(SwitchTree::switchKeyword)