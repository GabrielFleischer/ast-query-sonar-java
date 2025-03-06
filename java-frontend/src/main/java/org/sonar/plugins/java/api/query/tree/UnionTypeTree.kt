package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.UnionTypeTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out UnionTypeTree>.listTypeAlternatives() = func(UnionTypeTree::typeAlternatives)
fun <T> OptionalSelector<T, out UnionTypeTree>.listTypeAlternatives() = func(UnionTypeTree::typeAlternatives)
fun <T> ManySelector<T, out UnionTypeTree>.listTypeAlternatives() = func(UnionTypeTree::typeAlternatives)
fun <T> SingleSelector<T, out UnionTypeTree>.typeAlternatives() = listFunc(UnionTypeTree::typeAlternatives)
fun <T> OptionalSelector<T, out UnionTypeTree>.typeAlternatives() = listFunc(UnionTypeTree::typeAlternatives)
fun <T> ManySelector<T, out UnionTypeTree>.typeAlternatives() = listFunc(UnionTypeTree::typeAlternatives)