package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ArrayAccessExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ArrayAccessExpressionTree>.dimension() = func(ArrayAccessExpressionTree::dimension)
fun <T> OptionalSelector<T, out ArrayAccessExpressionTree>.dimension() = func(ArrayAccessExpressionTree::dimension)
fun <T> ManySelector<T, out ArrayAccessExpressionTree>.dimension() = func(ArrayAccessExpressionTree::dimension)

fun <T> SingleSelector<T, out ArrayAccessExpressionTree>.expression() = func(ArrayAccessExpressionTree::expression)
fun <T> OptionalSelector<T, out ArrayAccessExpressionTree>.expression() = func(ArrayAccessExpressionTree::expression)
fun <T> ManySelector<T, out ArrayAccessExpressionTree>.expression() = func(ArrayAccessExpressionTree::expression)