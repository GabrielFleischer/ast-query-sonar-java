package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ListTree
import org.sonar.plugins.java.api.tree.Tree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T, A> SingleSelector<T, out ListTree<A>>.listSeparators() where A: Tree = func(ListTree<A>::separators)
fun <T, A> OptionalSelector<T, out ListTree<A>>.listSeparators() where A: Tree = func(ListTree<A>::separators)
fun <T, A> ManySelector<T, out ListTree<A>>.listSeparators() where A: Tree = func(ListTree<A>::separators)
fun <T, A> SingleSelector<T, out ListTree<A>>.separators() where A: Tree = listFunc(ListTree<A>::separators)
fun <T, A> OptionalSelector<T, out ListTree<A>>.separators() where A: Tree = listFunc(ListTree<A>::separators)
fun <T, A> ManySelector<T, out ListTree<A>>.separators() where A: Tree = listFunc(ListTree<A>::separators)