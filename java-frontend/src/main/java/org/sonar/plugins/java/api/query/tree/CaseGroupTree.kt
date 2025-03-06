package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.CaseGroupTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out CaseGroupTree>.listBody() = func(CaseGroupTree::body)
fun <T> OptionalSelector<T, out CaseGroupTree>.listBody() = func(CaseGroupTree::body)
fun <T> ManySelector<T, out CaseGroupTree>.listBody() = func(CaseGroupTree::body)
fun <T> SingleSelector<T, out CaseGroupTree>.body() = listFunc(CaseGroupTree::body)
fun <T> OptionalSelector<T, out CaseGroupTree>.body() = listFunc(CaseGroupTree::body)
fun <T> ManySelector<T, out CaseGroupTree>.body() = listFunc(CaseGroupTree::body)

fun <T> SingleSelector<T, out CaseGroupTree>.listLabels() = func(CaseGroupTree::labels)
fun <T> OptionalSelector<T, out CaseGroupTree>.listLabels() = func(CaseGroupTree::labels)
fun <T> ManySelector<T, out CaseGroupTree>.listLabels() = func(CaseGroupTree::labels)
fun <T> SingleSelector<T, out CaseGroupTree>.labels() = listFunc(CaseGroupTree::labels)
fun <T> OptionalSelector<T, out CaseGroupTree>.labels() = listFunc(CaseGroupTree::labels)
fun <T> ManySelector<T, out CaseGroupTree>.labels() = listFunc(CaseGroupTree::labels)