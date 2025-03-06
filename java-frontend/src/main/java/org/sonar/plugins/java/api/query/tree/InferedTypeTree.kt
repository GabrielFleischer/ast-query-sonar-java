package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.InferedTypeTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out InferedTypeTree>.firstToken() = optFunc(InferedTypeTree::firstToken)
fun <T> OptionalSelector<T, out InferedTypeTree>.firstToken() = optFunc(InferedTypeTree::firstToken)
fun <T> ManySelector<T, out InferedTypeTree>.firstToken() = optFunc(InferedTypeTree::firstToken)

fun <T> SingleSelector<T, out InferedTypeTree>.isLeaf() = func(InferedTypeTree::isLeaf)
fun <T> OptionalSelector<T, out InferedTypeTree>.isLeaf() = func(InferedTypeTree::isLeaf)
fun <T> ManySelector<T, out InferedTypeTree>.isLeaf() = func(InferedTypeTree::isLeaf)

fun <T> SingleSelector<T, out InferedTypeTree>.kind() = func(InferedTypeTree::kind)
fun <T> OptionalSelector<T, out InferedTypeTree>.kind() = func(InferedTypeTree::kind)
fun <T> ManySelector<T, out InferedTypeTree>.kind() = func(InferedTypeTree::kind)

fun <T> SingleSelector<T, out InferedTypeTree>.lastToken() = optFunc(InferedTypeTree::lastToken)
fun <T> OptionalSelector<T, out InferedTypeTree>.lastToken() = optFunc(InferedTypeTree::lastToken)
fun <T> ManySelector<T, out InferedTypeTree>.lastToken() = optFunc(InferedTypeTree::lastToken)

fun <T> SingleSelector<T, out InferedTypeTree>.listAnnotations() = func(InferedTypeTree::annotations)
fun <T> OptionalSelector<T, out InferedTypeTree>.listAnnotations() = func(InferedTypeTree::annotations)
fun <T> ManySelector<T, out InferedTypeTree>.listAnnotations() = func(InferedTypeTree::annotations)
fun <T> SingleSelector<T, out InferedTypeTree>.annotations() = listFunc(InferedTypeTree::annotations)
fun <T> OptionalSelector<T, out InferedTypeTree>.annotations() = listFunc(InferedTypeTree::annotations)
fun <T> ManySelector<T, out InferedTypeTree>.annotations() = listFunc(InferedTypeTree::annotations)

fun <T> SingleSelector<T, out InferedTypeTree>.listChildren() = func(InferedTypeTree::children)
fun <T> OptionalSelector<T, out InferedTypeTree>.listChildren() = func(InferedTypeTree::children)
fun <T> ManySelector<T, out InferedTypeTree>.listChildren() = func(InferedTypeTree::children)
fun <T> SingleSelector<T, out InferedTypeTree>.children() = listFunc(InferedTypeTree::children)
fun <T> OptionalSelector<T, out InferedTypeTree>.children() = listFunc(InferedTypeTree::children)
fun <T> ManySelector<T, out InferedTypeTree>.children() = listFunc(InferedTypeTree::children)