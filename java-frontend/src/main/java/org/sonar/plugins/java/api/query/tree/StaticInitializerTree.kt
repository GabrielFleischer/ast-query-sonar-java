package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.StaticInitializerTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out StaticInitializerTree>.staticKeyword() = func(StaticInitializerTree::staticKeyword)
fun <T> OptionalSelector<T, out StaticInitializerTree>.staticKeyword() = func(StaticInitializerTree::staticKeyword)
fun <T> ManySelector<T, out StaticInitializerTree>.staticKeyword() = func(StaticInitializerTree::staticKeyword)