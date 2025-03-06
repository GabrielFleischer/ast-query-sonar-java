package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.PatternInstanceOfTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out PatternInstanceOfTree>.expression() = func(PatternInstanceOfTree::expression)
fun <T> OptionalSelector<T, out PatternInstanceOfTree>.expression() = func(PatternInstanceOfTree::expression)
fun <T> ManySelector<T, out PatternInstanceOfTree>.expression() = func(PatternInstanceOfTree::expression)

fun <T> SingleSelector<T, out PatternInstanceOfTree>.instanceofKeyword() = func(PatternInstanceOfTree::instanceofKeyword)
fun <T> OptionalSelector<T, out PatternInstanceOfTree>.instanceofKeyword() = func(PatternInstanceOfTree::instanceofKeyword)
fun <T> ManySelector<T, out PatternInstanceOfTree>.instanceofKeyword() = func(PatternInstanceOfTree::instanceofKeyword)

fun <T> SingleSelector<T, out PatternInstanceOfTree>.pattern() = func(PatternInstanceOfTree::pattern)
fun <T> OptionalSelector<T, out PatternInstanceOfTree>.pattern() = func(PatternInstanceOfTree::pattern)
fun <T> ManySelector<T, out PatternInstanceOfTree>.pattern() = func(PatternInstanceOfTree::pattern)