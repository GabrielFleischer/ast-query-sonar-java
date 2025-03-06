package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.Arguments
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out Arguments>.closeParenToken() = optFunc(Arguments::closeParenToken)
fun <T> OptionalSelector<T, out Arguments>.closeParenToken() = optFunc(Arguments::closeParenToken)
fun <T> ManySelector<T, out Arguments>.closeParenToken() = optFunc(Arguments::closeParenToken)

fun <T> SingleSelector<T, out Arguments>.openParenToken() = optFunc(Arguments::openParenToken)
fun <T> OptionalSelector<T, out Arguments>.openParenToken() = optFunc(Arguments::openParenToken)
fun <T> ManySelector<T, out Arguments>.openParenToken() = optFunc(Arguments::openParenToken)