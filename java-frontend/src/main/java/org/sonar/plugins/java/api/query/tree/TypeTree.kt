package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypeTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out TypeTree>.listAnnotations() = func(TypeTree::annotations)
fun <T> OptionalSelector<T, out TypeTree>.listAnnotations() = func(TypeTree::annotations)
fun <T> ManySelector<T, out TypeTree>.listAnnotations() = func(TypeTree::annotations)
fun <T> SingleSelector<T, out TypeTree>.annotations() = listFunc(TypeTree::annotations)
fun <T> OptionalSelector<T, out TypeTree>.annotations() = listFunc(TypeTree::annotations)
fun <T> ManySelector<T, out TypeTree>.annotations() = listFunc(TypeTree::annotations)

fun <T> SingleSelector<T, out TypeTree>.symbolType() = func(TypeTree::symbolType)
fun <T> OptionalSelector<T, out TypeTree>.symbolType() = func(TypeTree::symbolType)
fun <T> ManySelector<T, out TypeTree>.symbolType() = func(TypeTree::symbolType)