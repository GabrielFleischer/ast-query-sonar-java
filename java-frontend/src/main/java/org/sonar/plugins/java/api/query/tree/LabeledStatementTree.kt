package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.LabeledStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out LabeledStatementTree>.colonToken() = func(LabeledStatementTree::colonToken)
fun <T> OptionalSelector<T, out LabeledStatementTree>.colonToken() = func(LabeledStatementTree::colonToken)
fun <T> ManySelector<T, out LabeledStatementTree>.colonToken() = func(LabeledStatementTree::colonToken)

fun <T> SingleSelector<T, out LabeledStatementTree>.label() = func(LabeledStatementTree::label)
fun <T> OptionalSelector<T, out LabeledStatementTree>.label() = func(LabeledStatementTree::label)
fun <T> ManySelector<T, out LabeledStatementTree>.label() = func(LabeledStatementTree::label)

fun <T> SingleSelector<T, out LabeledStatementTree>.statement() = func(LabeledStatementTree::statement)
fun <T> OptionalSelector<T, out LabeledStatementTree>.statement() = func(LabeledStatementTree::statement)
fun <T> ManySelector<T, out LabeledStatementTree>.statement() = func(LabeledStatementTree::statement)

fun <T> SingleSelector<T, out LabeledStatementTree>.symbol() = func(LabeledStatementTree::symbol)
fun <T> OptionalSelector<T, out LabeledStatementTree>.symbol() = func(LabeledStatementTree::symbol)
fun <T> ManySelector<T, out LabeledStatementTree>.symbol() = func(LabeledStatementTree::symbol)