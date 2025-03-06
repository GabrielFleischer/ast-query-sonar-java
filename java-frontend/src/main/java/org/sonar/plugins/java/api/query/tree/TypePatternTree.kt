package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypePatternTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out TypePatternTree>.patternVariable() = func(TypePatternTree::patternVariable)
fun <T> OptionalSelector<T, out TypePatternTree>.patternVariable() = func(TypePatternTree::patternVariable)
fun <T> ManySelector<T, out TypePatternTree>.patternVariable() = func(TypePatternTree::patternVariable)