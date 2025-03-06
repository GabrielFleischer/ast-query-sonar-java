package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ForEachStatement
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ForEachStatement>.closeParenToken() = func(ForEachStatement::closeParenToken)
fun <T> OptionalSelector<T, out ForEachStatement>.closeParenToken() = func(ForEachStatement::closeParenToken)
fun <T> ManySelector<T, out ForEachStatement>.closeParenToken() = func(ForEachStatement::closeParenToken)

fun <T> SingleSelector<T, out ForEachStatement>.colonToken() = func(ForEachStatement::colonToken)
fun <T> OptionalSelector<T, out ForEachStatement>.colonToken() = func(ForEachStatement::colonToken)
fun <T> ManySelector<T, out ForEachStatement>.colonToken() = func(ForEachStatement::colonToken)

fun <T> SingleSelector<T, out ForEachStatement>.expression() = func(ForEachStatement::expression)
fun <T> OptionalSelector<T, out ForEachStatement>.expression() = func(ForEachStatement::expression)
fun <T> ManySelector<T, out ForEachStatement>.expression() = func(ForEachStatement::expression)

fun <T> SingleSelector<T, out ForEachStatement>.forKeyword() = func(ForEachStatement::forKeyword)
fun <T> OptionalSelector<T, out ForEachStatement>.forKeyword() = func(ForEachStatement::forKeyword)
fun <T> ManySelector<T, out ForEachStatement>.forKeyword() = func(ForEachStatement::forKeyword)

fun <T> SingleSelector<T, out ForEachStatement>.openParenToken() = func(ForEachStatement::openParenToken)
fun <T> OptionalSelector<T, out ForEachStatement>.openParenToken() = func(ForEachStatement::openParenToken)
fun <T> ManySelector<T, out ForEachStatement>.openParenToken() = func(ForEachStatement::openParenToken)

fun <T> SingleSelector<T, out ForEachStatement>.statement() = func(ForEachStatement::statement)
fun <T> OptionalSelector<T, out ForEachStatement>.statement() = func(ForEachStatement::statement)
fun <T> ManySelector<T, out ForEachStatement>.statement() = func(ForEachStatement::statement)

fun <T> SingleSelector<T, out ForEachStatement>.variable() = func(ForEachStatement::variable)
fun <T> OptionalSelector<T, out ForEachStatement>.variable() = func(ForEachStatement::variable)
fun <T> ManySelector<T, out ForEachStatement>.variable() = func(ForEachStatement::variable)