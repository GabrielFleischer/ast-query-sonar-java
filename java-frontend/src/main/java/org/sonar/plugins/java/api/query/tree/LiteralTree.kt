package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.LiteralTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out LiteralTree>.token() = func(LiteralTree::token)
fun <T> OptionalSelector<T, out LiteralTree>.token() = func(LiteralTree::token)
fun <T> ManySelector<T, out LiteralTree>.token() = func(LiteralTree::token)

fun <T> SingleSelector<T, out LiteralTree>.value() = func(LiteralTree::value)
fun <T> OptionalSelector<T, out LiteralTree>.value() = func(LiteralTree::value)
fun <T> ManySelector<T, out LiteralTree>.value() = func(LiteralTree::value)