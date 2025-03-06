package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.Tree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out Tree>.firstToken() = optFunc(Tree::firstToken)
fun <T> OptionalSelector<T, out Tree>.firstToken() = optFunc(Tree::firstToken)
fun <T> ManySelector<T, out Tree>.firstToken() = optFunc(Tree::firstToken)

fun <T> SingleSelector<T, out Tree>.kind() = func(Tree::kind)
fun <T> OptionalSelector<T, out Tree>.kind() = func(Tree::kind)
fun <T> ManySelector<T, out Tree>.kind() = func(Tree::kind)

fun <T> SingleSelector<T, out Tree>.lastToken() = optFunc(Tree::lastToken)
fun <T> OptionalSelector<T, out Tree>.lastToken() = optFunc(Tree::lastToken)
fun <T> ManySelector<T, out Tree>.lastToken() = optFunc(Tree::lastToken)

fun <T> SingleSelector<T, out Tree>.parent() = optFunc(Tree::parent)
fun <T> OptionalSelector<T, out Tree>.parent() = optFunc(Tree::parent)
fun <T> ManySelector<T, out Tree>.parent() = optFunc(Tree::parent)