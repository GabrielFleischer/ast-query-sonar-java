package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ArrayDimensionTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ArrayDimensionTree>.closeBracketToken() = func(ArrayDimensionTree::closeBracketToken)
fun <T> OptionalSelector<T, out ArrayDimensionTree>.closeBracketToken() = func(ArrayDimensionTree::closeBracketToken)
fun <T> ManySelector<T, out ArrayDimensionTree>.closeBracketToken() = func(ArrayDimensionTree::closeBracketToken)

fun <T> SingleSelector<T, out ArrayDimensionTree>.expression() = optFunc(ArrayDimensionTree::expression)
fun <T> OptionalSelector<T, out ArrayDimensionTree>.expression() = optFunc(ArrayDimensionTree::expression)
fun <T> ManySelector<T, out ArrayDimensionTree>.expression() = optFunc(ArrayDimensionTree::expression)

fun <T> SingleSelector<T, out ArrayDimensionTree>.listAnnotations() = func(ArrayDimensionTree::annotations)
fun <T> OptionalSelector<T, out ArrayDimensionTree>.listAnnotations() = func(ArrayDimensionTree::annotations)
fun <T> ManySelector<T, out ArrayDimensionTree>.listAnnotations() = func(ArrayDimensionTree::annotations)
fun <T> SingleSelector<T, out ArrayDimensionTree>.annotations() = listFunc(ArrayDimensionTree::annotations)
fun <T> OptionalSelector<T, out ArrayDimensionTree>.annotations() = listFunc(ArrayDimensionTree::annotations)
fun <T> ManySelector<T, out ArrayDimensionTree>.annotations() = listFunc(ArrayDimensionTree::annotations)

fun <T> SingleSelector<T, out ArrayDimensionTree>.openBracketToken() = func(ArrayDimensionTree::openBracketToken)
fun <T> OptionalSelector<T, out ArrayDimensionTree>.openBracketToken() = func(ArrayDimensionTree::openBracketToken)
fun <T> ManySelector<T, out ArrayDimensionTree>.openBracketToken() = func(ArrayDimensionTree::openBracketToken)