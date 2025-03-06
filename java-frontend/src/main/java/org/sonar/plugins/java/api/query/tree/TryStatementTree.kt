package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TryStatementTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out TryStatementTree>.block() = func(TryStatementTree::block)
fun <T> OptionalSelector<T, out TryStatementTree>.block() = func(TryStatementTree::block)
fun <T> ManySelector<T, out TryStatementTree>.block() = func(TryStatementTree::block)

fun <T> SingleSelector<T, out TryStatementTree>.closeParenToken() = optFunc(TryStatementTree::closeParenToken)
fun <T> OptionalSelector<T, out TryStatementTree>.closeParenToken() = optFunc(TryStatementTree::closeParenToken)
fun <T> ManySelector<T, out TryStatementTree>.closeParenToken() = optFunc(TryStatementTree::closeParenToken)

fun <T> SingleSelector<T, out TryStatementTree>.finallyBlock() = optFunc(TryStatementTree::finallyBlock)
fun <T> OptionalSelector<T, out TryStatementTree>.finallyBlock() = optFunc(TryStatementTree::finallyBlock)
fun <T> ManySelector<T, out TryStatementTree>.finallyBlock() = optFunc(TryStatementTree::finallyBlock)

fun <T> SingleSelector<T, out TryStatementTree>.finallyKeyword() = optFunc(TryStatementTree::finallyKeyword)
fun <T> OptionalSelector<T, out TryStatementTree>.finallyKeyword() = optFunc(TryStatementTree::finallyKeyword)
fun <T> ManySelector<T, out TryStatementTree>.finallyKeyword() = optFunc(TryStatementTree::finallyKeyword)

fun <T> SingleSelector<T, out TryStatementTree>.listCatches() = func(TryStatementTree::catches)
fun <T> OptionalSelector<T, out TryStatementTree>.listCatches() = func(TryStatementTree::catches)
fun <T> ManySelector<T, out TryStatementTree>.listCatches() = func(TryStatementTree::catches)
fun <T> SingleSelector<T, out TryStatementTree>.catches() = listFunc(TryStatementTree::catches)
fun <T> OptionalSelector<T, out TryStatementTree>.catches() = listFunc(TryStatementTree::catches)
fun <T> ManySelector<T, out TryStatementTree>.catches() = listFunc(TryStatementTree::catches)

fun <T> SingleSelector<T, out TryStatementTree>.listResourceList() = func(TryStatementTree::resourceList)
fun <T> OptionalSelector<T, out TryStatementTree>.listResourceList() = func(TryStatementTree::resourceList)
fun <T> ManySelector<T, out TryStatementTree>.listResourceList() = func(TryStatementTree::resourceList)
fun <T> SingleSelector<T, out TryStatementTree>.resourceList() = listFunc(TryStatementTree::resourceList)
fun <T> OptionalSelector<T, out TryStatementTree>.resourceList() = listFunc(TryStatementTree::resourceList)
fun <T> ManySelector<T, out TryStatementTree>.resourceList() = listFunc(TryStatementTree::resourceList)

fun <T> SingleSelector<T, out TryStatementTree>.openParenToken() = optFunc(TryStatementTree::openParenToken)
fun <T> OptionalSelector<T, out TryStatementTree>.openParenToken() = optFunc(TryStatementTree::openParenToken)
fun <T> ManySelector<T, out TryStatementTree>.openParenToken() = optFunc(TryStatementTree::openParenToken)

fun <T> SingleSelector<T, out TryStatementTree>.tryKeyword() = func(TryStatementTree::tryKeyword)
fun <T> OptionalSelector<T, out TryStatementTree>.tryKeyword() = func(TryStatementTree::tryKeyword)
fun <T> ManySelector<T, out TryStatementTree>.tryKeyword() = func(TryStatementTree::tryKeyword)