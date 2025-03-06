package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.DefaultPatternTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out DefaultPatternTree>.defaultToken() = func(DefaultPatternTree::defaultToken)
fun <T> OptionalSelector<T, out DefaultPatternTree>.defaultToken() = func(DefaultPatternTree::defaultToken)
fun <T> ManySelector<T, out DefaultPatternTree>.defaultToken() = func(DefaultPatternTree::defaultToken)