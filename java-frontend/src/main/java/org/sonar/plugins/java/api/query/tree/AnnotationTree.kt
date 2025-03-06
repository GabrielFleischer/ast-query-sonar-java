package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.AnnotationTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out AnnotationTree>.annotationType() = func(AnnotationTree::annotationType)
fun <T> OptionalSelector<T, out AnnotationTree>.annotationType() = func(AnnotationTree::annotationType)
fun <T> ManySelector<T, out AnnotationTree>.annotationType() = func(AnnotationTree::annotationType)

fun <T> SingleSelector<T, out AnnotationTree>.atToken() = func(AnnotationTree::atToken)
fun <T> OptionalSelector<T, out AnnotationTree>.atToken() = func(AnnotationTree::atToken)
fun <T> ManySelector<T, out AnnotationTree>.atToken() = func(AnnotationTree::atToken)

fun <T> SingleSelector<T, out AnnotationTree>.listArguments() = func(AnnotationTree::arguments)
fun <T> OptionalSelector<T, out AnnotationTree>.listArguments() = func(AnnotationTree::arguments)
fun <T> ManySelector<T, out AnnotationTree>.listArguments() = func(AnnotationTree::arguments)
fun <T> SingleSelector<T, out AnnotationTree>.arguments() = listFunc(AnnotationTree::arguments)
fun <T> OptionalSelector<T, out AnnotationTree>.arguments() = listFunc(AnnotationTree::arguments)
fun <T> ManySelector<T, out AnnotationTree>.arguments() = listFunc(AnnotationTree::arguments)