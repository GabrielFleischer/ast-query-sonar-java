package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.EmptyStatementTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out EmptyStatementTree>.semicolonToken() = func(EmptyStatementTree::semicolonToken)
fun <T> OptionalSelector<T, out EmptyStatementTree>.semicolonToken() = func(EmptyStatementTree::semicolonToken)
fun <T> ManySelector<T, out EmptyStatementTree>.semicolonToken() = func(EmptyStatementTree::semicolonToken)