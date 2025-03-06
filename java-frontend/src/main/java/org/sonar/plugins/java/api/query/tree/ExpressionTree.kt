package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ExpressionTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ExpressionTree>.asConstant() = func(ExpressionTree::asConstant)
fun <T> OptionalSelector<T, out ExpressionTree>.asConstant() = func(ExpressionTree::asConstant)
fun <T> ManySelector<T, out ExpressionTree>.asConstant() = func(ExpressionTree::asConstant)

fun <T> SingleSelector<T, out ExpressionTree>.symbolType() = func(ExpressionTree::symbolType)
fun <T> OptionalSelector<T, out ExpressionTree>.symbolType() = func(ExpressionTree::symbolType)
fun <T> ManySelector<T, out ExpressionTree>.symbolType() = func(ExpressionTree::symbolType)