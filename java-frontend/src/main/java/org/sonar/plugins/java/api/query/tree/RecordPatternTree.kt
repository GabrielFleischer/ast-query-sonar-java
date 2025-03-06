package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.RecordPatternTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out RecordPatternTree>.closeParenToken() = func(RecordPatternTree::closeParenToken)
fun <T> OptionalSelector<T, out RecordPatternTree>.closeParenToken() = func(RecordPatternTree::closeParenToken)
fun <T> ManySelector<T, out RecordPatternTree>.closeParenToken() = func(RecordPatternTree::closeParenToken)

fun <T> SingleSelector<T, out RecordPatternTree>.listPatterns() = func(RecordPatternTree::patterns)
fun <T> OptionalSelector<T, out RecordPatternTree>.listPatterns() = func(RecordPatternTree::patterns)
fun <T> ManySelector<T, out RecordPatternTree>.listPatterns() = func(RecordPatternTree::patterns)
fun <T> SingleSelector<T, out RecordPatternTree>.patterns() = listFunc(RecordPatternTree::patterns)
fun <T> OptionalSelector<T, out RecordPatternTree>.patterns() = listFunc(RecordPatternTree::patterns)
fun <T> ManySelector<T, out RecordPatternTree>.patterns() = listFunc(RecordPatternTree::patterns)

fun <T> SingleSelector<T, out RecordPatternTree>.openParenToken() = func(RecordPatternTree::openParenToken)
fun <T> OptionalSelector<T, out RecordPatternTree>.openParenToken() = func(RecordPatternTree::openParenToken)
fun <T> ManySelector<T, out RecordPatternTree>.openParenToken() = func(RecordPatternTree::openParenToken)

fun <T> SingleSelector<T, out RecordPatternTree>.type() = func(RecordPatternTree::type)
fun <T> OptionalSelector<T, out RecordPatternTree>.type() = func(RecordPatternTree::type)
fun <T> ManySelector<T, out RecordPatternTree>.type() = func(RecordPatternTree::type)