package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.BlockTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out BlockTree>.closeBraceToken() = func(BlockTree::closeBraceToken)
fun <T> OptionalSelector<T, out BlockTree>.closeBraceToken() = func(BlockTree::closeBraceToken)
fun <T> ManySelector<T, out BlockTree>.closeBraceToken() = func(BlockTree::closeBraceToken)

fun <T> SingleSelector<T, out BlockTree>.listBody() = func(BlockTree::body)
fun <T> OptionalSelector<T, out BlockTree>.listBody() = func(BlockTree::body)
fun <T> ManySelector<T, out BlockTree>.listBody() = func(BlockTree::body)
fun <T> SingleSelector<T, out BlockTree>.body() = listFunc(BlockTree::body)
fun <T> OptionalSelector<T, out BlockTree>.body() = listFunc(BlockTree::body)
fun <T> ManySelector<T, out BlockTree>.body() = listFunc(BlockTree::body)

fun <T> SingleSelector<T, out BlockTree>.openBraceToken() = func(BlockTree::openBraceToken)
fun <T> OptionalSelector<T, out BlockTree>.openBraceToken() = func(BlockTree::openBraceToken)
fun <T> ManySelector<T, out BlockTree>.openBraceToken() = func(BlockTree::openBraceToken)