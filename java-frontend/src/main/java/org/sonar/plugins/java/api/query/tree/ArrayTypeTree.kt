package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ArrayTypeTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ArrayTypeTree>.closeBracketToken() = optFunc(ArrayTypeTree::closeBracketToken)
fun <T> OptionalSelector<T, out ArrayTypeTree>.closeBracketToken() = optFunc(ArrayTypeTree::closeBracketToken)
fun <T> ManySelector<T, out ArrayTypeTree>.closeBracketToken() = optFunc(ArrayTypeTree::closeBracketToken)

fun <T> SingleSelector<T, out ArrayTypeTree>.ellipsisToken() = optFunc(ArrayTypeTree::ellipsisToken)
fun <T> OptionalSelector<T, out ArrayTypeTree>.ellipsisToken() = optFunc(ArrayTypeTree::ellipsisToken)
fun <T> ManySelector<T, out ArrayTypeTree>.ellipsisToken() = optFunc(ArrayTypeTree::ellipsisToken)

fun <T> SingleSelector<T, out ArrayTypeTree>.openBracketToken() = optFunc(ArrayTypeTree::openBracketToken)
fun <T> OptionalSelector<T, out ArrayTypeTree>.openBracketToken() = optFunc(ArrayTypeTree::openBracketToken)
fun <T> ManySelector<T, out ArrayTypeTree>.openBracketToken() = optFunc(ArrayTypeTree::openBracketToken)

fun <T> SingleSelector<T, out ArrayTypeTree>.type() = func(ArrayTypeTree::type)
fun <T> OptionalSelector<T, out ArrayTypeTree>.type() = func(ArrayTypeTree::type)
fun <T> ManySelector<T, out ArrayTypeTree>.type() = func(ArrayTypeTree::type)