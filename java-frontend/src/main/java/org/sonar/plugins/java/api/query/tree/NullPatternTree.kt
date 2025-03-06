package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.NullPatternTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out NullPatternTree>.nullLiteral() = func(NullPatternTree::nullLiteral)
fun <T> OptionalSelector<T, out NullPatternTree>.nullLiteral() = func(NullPatternTree::nullLiteral)
fun <T> ManySelector<T, out NullPatternTree>.nullLiteral() = func(NullPatternTree::nullLiteral)