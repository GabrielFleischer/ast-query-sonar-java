package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.GuardedPatternTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out GuardedPatternTree>.expression() = func(GuardedPatternTree::expression)
fun <T> OptionalSelector<T, out GuardedPatternTree>.expression() = func(GuardedPatternTree::expression)
fun <T> ManySelector<T, out GuardedPatternTree>.expression() = func(GuardedPatternTree::expression)

fun <T> SingleSelector<T, out GuardedPatternTree>.pattern() = func(GuardedPatternTree::pattern)
fun <T> OptionalSelector<T, out GuardedPatternTree>.pattern() = func(GuardedPatternTree::pattern)
fun <T> ManySelector<T, out GuardedPatternTree>.pattern() = func(GuardedPatternTree::pattern)

fun <T> SingleSelector<T, out GuardedPatternTree>.whenOperator() = func(GuardedPatternTree::whenOperator)
fun <T> OptionalSelector<T, out GuardedPatternTree>.whenOperator() = func(GuardedPatternTree::whenOperator)
fun <T> ManySelector<T, out GuardedPatternTree>.whenOperator() = func(GuardedPatternTree::whenOperator)