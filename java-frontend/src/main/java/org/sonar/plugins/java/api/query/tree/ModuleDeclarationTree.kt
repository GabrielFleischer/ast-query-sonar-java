package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ModuleDeclarationTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out ModuleDeclarationTree>.closeBraceToken() = func(ModuleDeclarationTree::closeBraceToken)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.closeBraceToken() = func(ModuleDeclarationTree::closeBraceToken)
fun <T> ManySelector<T, out ModuleDeclarationTree>.closeBraceToken() = func(ModuleDeclarationTree::closeBraceToken)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.listAnnotations() = func(ModuleDeclarationTree::annotations)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.listAnnotations() = func(ModuleDeclarationTree::annotations)
fun <T> ManySelector<T, out ModuleDeclarationTree>.listAnnotations() = func(ModuleDeclarationTree::annotations)
fun <T> SingleSelector<T, out ModuleDeclarationTree>.annotations() = listFunc(ModuleDeclarationTree::annotations)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.annotations() = listFunc(ModuleDeclarationTree::annotations)
fun <T> ManySelector<T, out ModuleDeclarationTree>.annotations() = listFunc(ModuleDeclarationTree::annotations)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.listModuleDirectives() = func(ModuleDeclarationTree::moduleDirectives)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.listModuleDirectives() = func(ModuleDeclarationTree::moduleDirectives)
fun <T> ManySelector<T, out ModuleDeclarationTree>.listModuleDirectives() = func(ModuleDeclarationTree::moduleDirectives)
fun <T> SingleSelector<T, out ModuleDeclarationTree>.moduleDirectives() = listFunc(ModuleDeclarationTree::moduleDirectives)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.moduleDirectives() = listFunc(ModuleDeclarationTree::moduleDirectives)
fun <T> ManySelector<T, out ModuleDeclarationTree>.moduleDirectives() = listFunc(ModuleDeclarationTree::moduleDirectives)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.listModuleName() = func(ModuleDeclarationTree::moduleName)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.listModuleName() = func(ModuleDeclarationTree::moduleName)
fun <T> ManySelector<T, out ModuleDeclarationTree>.listModuleName() = func(ModuleDeclarationTree::moduleName)
fun <T> SingleSelector<T, out ModuleDeclarationTree>.moduleName() = listFunc(ModuleDeclarationTree::moduleName)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.moduleName() = listFunc(ModuleDeclarationTree::moduleName)
fun <T> ManySelector<T, out ModuleDeclarationTree>.moduleName() = listFunc(ModuleDeclarationTree::moduleName)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.moduleKeyword() = func(ModuleDeclarationTree::moduleKeyword)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.moduleKeyword() = func(ModuleDeclarationTree::moduleKeyword)
fun <T> ManySelector<T, out ModuleDeclarationTree>.moduleKeyword() = func(ModuleDeclarationTree::moduleKeyword)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.openBraceToken() = func(ModuleDeclarationTree::openBraceToken)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.openBraceToken() = func(ModuleDeclarationTree::openBraceToken)
fun <T> ManySelector<T, out ModuleDeclarationTree>.openBraceToken() = func(ModuleDeclarationTree::openBraceToken)

fun <T> SingleSelector<T, out ModuleDeclarationTree>.openKeyword() = optFunc(ModuleDeclarationTree::openKeyword)
fun <T> OptionalSelector<T, out ModuleDeclarationTree>.openKeyword() = optFunc(ModuleDeclarationTree::openKeyword)
fun <T> ManySelector<T, out ModuleDeclarationTree>.openKeyword() = optFunc(ModuleDeclarationTree::openKeyword)