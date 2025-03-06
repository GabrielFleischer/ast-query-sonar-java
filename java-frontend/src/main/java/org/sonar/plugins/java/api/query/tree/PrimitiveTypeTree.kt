package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.PrimitiveTypeTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out PrimitiveTypeTree>.keyword() = func(PrimitiveTypeTree::keyword)
fun <T> OptionalSelector<T, out PrimitiveTypeTree>.keyword() = func(PrimitiveTypeTree::keyword)
fun <T> ManySelector<T, out PrimitiveTypeTree>.keyword() = func(PrimitiveTypeTree::keyword)