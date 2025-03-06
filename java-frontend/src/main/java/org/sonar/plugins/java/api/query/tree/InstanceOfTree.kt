package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.InstanceOfTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out InstanceOfTree>.expression() = func(InstanceOfTree::expression)
fun <T> OptionalSelector<T, out InstanceOfTree>.expression() = func(InstanceOfTree::expression)
fun <T> ManySelector<T, out InstanceOfTree>.expression() = func(InstanceOfTree::expression)

fun <T> SingleSelector<T, out InstanceOfTree>.instanceofKeyword() = func(InstanceOfTree::instanceofKeyword)
fun <T> OptionalSelector<T, out InstanceOfTree>.instanceofKeyword() = func(InstanceOfTree::instanceofKeyword)
fun <T> ManySelector<T, out InstanceOfTree>.instanceofKeyword() = func(InstanceOfTree::instanceofKeyword)

fun <T> SingleSelector<T, out InstanceOfTree>.type() = func(InstanceOfTree::type)
fun <T> OptionalSelector<T, out InstanceOfTree>.type() = func(InstanceOfTree::type)
fun <T> ManySelector<T, out InstanceOfTree>.type() = func(InstanceOfTree::type)