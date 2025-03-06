package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.CaseLabelTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out CaseLabelTree>.caseOrDefaultKeyword() = func(CaseLabelTree::caseOrDefaultKeyword)
fun <T> OptionalSelector<T, out CaseLabelTree>.caseOrDefaultKeyword() = func(CaseLabelTree::caseOrDefaultKeyword)
fun <T> ManySelector<T, out CaseLabelTree>.caseOrDefaultKeyword() = func(CaseLabelTree::caseOrDefaultKeyword)

fun <T> SingleSelector<T, out CaseLabelTree>.colonOrArrowToken() = func(CaseLabelTree::colonOrArrowToken)
fun <T> OptionalSelector<T, out CaseLabelTree>.colonOrArrowToken() = func(CaseLabelTree::colonOrArrowToken)
fun <T> ManySelector<T, out CaseLabelTree>.colonOrArrowToken() = func(CaseLabelTree::colonOrArrowToken)

fun <T> SingleSelector<T, out CaseLabelTree>.isFallThrough() = func(CaseLabelTree::isFallThrough)
fun <T> OptionalSelector<T, out CaseLabelTree>.isFallThrough() = func(CaseLabelTree::isFallThrough)
fun <T> ManySelector<T, out CaseLabelTree>.isFallThrough() = func(CaseLabelTree::isFallThrough)

fun <T> SingleSelector<T, out CaseLabelTree>.listExpressions() = func(CaseLabelTree::expressions)
fun <T> OptionalSelector<T, out CaseLabelTree>.listExpressions() = func(CaseLabelTree::expressions)
fun <T> ManySelector<T, out CaseLabelTree>.listExpressions() = func(CaseLabelTree::expressions)
fun <T> SingleSelector<T, out CaseLabelTree>.expressions() = listFunc(CaseLabelTree::expressions)
fun <T> OptionalSelector<T, out CaseLabelTree>.expressions() = listFunc(CaseLabelTree::expressions)
fun <T> ManySelector<T, out CaseLabelTree>.expressions() = listFunc(CaseLabelTree::expressions)