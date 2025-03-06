package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ForStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out ForStatementTree>.closeParenToken() = func(ForStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out ForStatementTree>.closeParenToken() = func(ForStatementTree::closeParenToken)
fun <T> ManySelector<T, out ForStatementTree>.closeParenToken() = func(ForStatementTree::closeParenToken)

fun <T> SingleSelector<T, out ForStatementTree>.condition() = optFunc(ForStatementTree::condition)
fun <T> OptionalSelector<T, out ForStatementTree>.condition() = optFunc(ForStatementTree::condition)
fun <T> ManySelector<T, out ForStatementTree>.condition() = optFunc(ForStatementTree::condition)

fun <T> SingleSelector<T, out ForStatementTree>.firstSemicolonToken() = func(ForStatementTree::firstSemicolonToken)
fun <T> OptionalSelector<T, out ForStatementTree>.firstSemicolonToken() = func(ForStatementTree::firstSemicolonToken)
fun <T> ManySelector<T, out ForStatementTree>.firstSemicolonToken() = func(ForStatementTree::firstSemicolonToken)

fun <T> SingleSelector<T, out ForStatementTree>.forKeyword() = func(ForStatementTree::forKeyword)
fun <T> OptionalSelector<T, out ForStatementTree>.forKeyword() = func(ForStatementTree::forKeyword)
fun <T> ManySelector<T, out ForStatementTree>.forKeyword() = func(ForStatementTree::forKeyword)

fun <T> SingleSelector<T, out ForStatementTree>.listInitializer() = func(ForStatementTree::initializer)
fun <T> OptionalSelector<T, out ForStatementTree>.listInitializer() = func(ForStatementTree::initializer)
fun <T> ManySelector<T, out ForStatementTree>.listInitializer() = func(ForStatementTree::initializer)
fun <T> SingleSelector<T, out ForStatementTree>.initializer() = listFunc(ForStatementTree::initializer)
fun <T> OptionalSelector<T, out ForStatementTree>.initializer() = listFunc(ForStatementTree::initializer)
fun <T> ManySelector<T, out ForStatementTree>.initializer() = listFunc(ForStatementTree::initializer)

fun <T> SingleSelector<T, out ForStatementTree>.listUpdate() = func(ForStatementTree::update)
fun <T> OptionalSelector<T, out ForStatementTree>.listUpdate() = func(ForStatementTree::update)
fun <T> ManySelector<T, out ForStatementTree>.listUpdate() = func(ForStatementTree::update)
fun <T> SingleSelector<T, out ForStatementTree>.update() = listFunc(ForStatementTree::update)
fun <T> OptionalSelector<T, out ForStatementTree>.update() = listFunc(ForStatementTree::update)
fun <T> ManySelector<T, out ForStatementTree>.update() = listFunc(ForStatementTree::update)

fun <T> SingleSelector<T, out ForStatementTree>.openParenToken() = func(ForStatementTree::openParenToken)
fun <T> OptionalSelector<T, out ForStatementTree>.openParenToken() = func(ForStatementTree::openParenToken)
fun <T> ManySelector<T, out ForStatementTree>.openParenToken() = func(ForStatementTree::openParenToken)

fun <T> SingleSelector<T, out ForStatementTree>.secondSemicolonToken() = func(ForStatementTree::secondSemicolonToken)
fun <T> OptionalSelector<T, out ForStatementTree>.secondSemicolonToken() = func(ForStatementTree::secondSemicolonToken)
fun <T> ManySelector<T, out ForStatementTree>.secondSemicolonToken() = func(ForStatementTree::secondSemicolonToken)

fun <T> SingleSelector<T, out ForStatementTree>.statement() = func(ForStatementTree::statement)
fun <T> OptionalSelector<T, out ForStatementTree>.statement() = func(ForStatementTree::statement)
fun <T> ManySelector<T, out ForStatementTree>.statement() = func(ForStatementTree::statement)