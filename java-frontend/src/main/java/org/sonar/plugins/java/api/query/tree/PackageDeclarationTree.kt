package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.PackageDeclarationTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out PackageDeclarationTree>.listAnnotations() = func(PackageDeclarationTree::annotations)
fun <T> OptionalSelector<T, out PackageDeclarationTree>.listAnnotations() = func(PackageDeclarationTree::annotations)
fun <T> ManySelector<T, out PackageDeclarationTree>.listAnnotations() = func(PackageDeclarationTree::annotations)
fun <T> SingleSelector<T, out PackageDeclarationTree>.annotations() = listFunc(PackageDeclarationTree::annotations)
fun <T> OptionalSelector<T, out PackageDeclarationTree>.annotations() = listFunc(PackageDeclarationTree::annotations)
fun <T> ManySelector<T, out PackageDeclarationTree>.annotations() = listFunc(PackageDeclarationTree::annotations)

fun <T> SingleSelector<T, out PackageDeclarationTree>.packageKeyword() = func(PackageDeclarationTree::packageKeyword)
fun <T> OptionalSelector<T, out PackageDeclarationTree>.packageKeyword() = func(PackageDeclarationTree::packageKeyword)
fun <T> ManySelector<T, out PackageDeclarationTree>.packageKeyword() = func(PackageDeclarationTree::packageKeyword)

fun <T> SingleSelector<T, out PackageDeclarationTree>.packageName() = func(PackageDeclarationTree::packageName)
fun <T> OptionalSelector<T, out PackageDeclarationTree>.packageName() = func(PackageDeclarationTree::packageName)
fun <T> ManySelector<T, out PackageDeclarationTree>.packageName() = func(PackageDeclarationTree::packageName)

fun <T> SingleSelector<T, out PackageDeclarationTree>.semicolonToken() = func(PackageDeclarationTree::semicolonToken)
fun <T> OptionalSelector<T, out PackageDeclarationTree>.semicolonToken() = func(PackageDeclarationTree::semicolonToken)
fun <T> ManySelector<T, out PackageDeclarationTree>.semicolonToken() = func(PackageDeclarationTree::semicolonToken)