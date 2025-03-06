package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.UsesDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out UsesDirectiveTree>.typeName() = func(UsesDirectiveTree::typeName)
fun <T> OptionalSelector<T, out UsesDirectiveTree>.typeName() = func(UsesDirectiveTree::typeName)
fun <T> ManySelector<T, out UsesDirectiveTree>.typeName() = func(UsesDirectiveTree::typeName)