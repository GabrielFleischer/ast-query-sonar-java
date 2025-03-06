package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.WildcardTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out WildcardTree>.bound() = optFunc(WildcardTree::bound)
fun <T> OptionalSelector<T, out WildcardTree>.bound() = optFunc(WildcardTree::bound)
fun <T> ManySelector<T, out WildcardTree>.bound() = optFunc(WildcardTree::bound)

fun <T> SingleSelector<T, out WildcardTree>.extendsOrSuperToken() = optFunc(WildcardTree::extendsOrSuperToken)
fun <T> OptionalSelector<T, out WildcardTree>.extendsOrSuperToken() = optFunc(WildcardTree::extendsOrSuperToken)
fun <T> ManySelector<T, out WildcardTree>.extendsOrSuperToken() = optFunc(WildcardTree::extendsOrSuperToken)

fun <T> SingleSelector<T, out WildcardTree>.listAnnotations() = func(WildcardTree::annotations)
fun <T> OptionalSelector<T, out WildcardTree>.listAnnotations() = func(WildcardTree::annotations)
fun <T> ManySelector<T, out WildcardTree>.listAnnotations() = func(WildcardTree::annotations)
fun <T> SingleSelector<T, out WildcardTree>.annotations() = listFunc(WildcardTree::annotations)
fun <T> OptionalSelector<T, out WildcardTree>.annotations() = listFunc(WildcardTree::annotations)
fun <T> ManySelector<T, out WildcardTree>.annotations() = listFunc(WildcardTree::annotations)

fun <T> SingleSelector<T, out WildcardTree>.queryToken() = func(WildcardTree::queryToken)
fun <T> OptionalSelector<T, out WildcardTree>.queryToken() = func(WildcardTree::queryToken)
fun <T> ManySelector<T, out WildcardTree>.queryToken() = func(WildcardTree::queryToken)