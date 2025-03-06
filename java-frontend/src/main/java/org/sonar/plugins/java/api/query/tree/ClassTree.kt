package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ClassTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ClassTree>.closeBraceToken() = func(ClassTree::closeBraceToken)
fun <T> OptionalSelector<T, out ClassTree>.closeBraceToken() = func(ClassTree::closeBraceToken)
fun <T> ManySelector<T, out ClassTree>.closeBraceToken() = func(ClassTree::closeBraceToken)

fun <T> SingleSelector<T, out ClassTree>.declarationKeyword() = optFunc(ClassTree::declarationKeyword)
fun <T> OptionalSelector<T, out ClassTree>.declarationKeyword() = optFunc(ClassTree::declarationKeyword)
fun <T> ManySelector<T, out ClassTree>.declarationKeyword() = optFunc(ClassTree::declarationKeyword)

fun <T> SingleSelector<T, out ClassTree>.listMembers() = func(ClassTree::members)
fun <T> OptionalSelector<T, out ClassTree>.listMembers() = func(ClassTree::members)
fun <T> ManySelector<T, out ClassTree>.listMembers() = func(ClassTree::members)
fun <T> SingleSelector<T, out ClassTree>.members() = listFunc(ClassTree::members)
fun <T> OptionalSelector<T, out ClassTree>.members() = listFunc(ClassTree::members)
fun <T> ManySelector<T, out ClassTree>.members() = listFunc(ClassTree::members)

fun <T> SingleSelector<T, out ClassTree>.listModifiers() = func(ClassTree::modifiers)
fun <T> OptionalSelector<T, out ClassTree>.listModifiers() = func(ClassTree::modifiers)
fun <T> ManySelector<T, out ClassTree>.listModifiers() = func(ClassTree::modifiers)
fun <T> SingleSelector<T, out ClassTree>.modifiers() = listFunc(ClassTree::modifiers)
fun <T> OptionalSelector<T, out ClassTree>.modifiers() = listFunc(ClassTree::modifiers)
fun <T> ManySelector<T, out ClassTree>.modifiers() = listFunc(ClassTree::modifiers)

fun <T> SingleSelector<T, out ClassTree>.listPermittedTypes() = func(ClassTree::permittedTypes)
fun <T> OptionalSelector<T, out ClassTree>.listPermittedTypes() = func(ClassTree::permittedTypes)
fun <T> ManySelector<T, out ClassTree>.listPermittedTypes() = func(ClassTree::permittedTypes)
fun <T> SingleSelector<T, out ClassTree>.permittedTypes() = listFunc(ClassTree::permittedTypes)
fun <T> OptionalSelector<T, out ClassTree>.permittedTypes() = listFunc(ClassTree::permittedTypes)
fun <T> ManySelector<T, out ClassTree>.permittedTypes() = listFunc(ClassTree::permittedTypes)

fun <T> SingleSelector<T, out ClassTree>.listRecordComponents() = func(ClassTree::recordComponents)
fun <T> OptionalSelector<T, out ClassTree>.listRecordComponents() = func(ClassTree::recordComponents)
fun <T> ManySelector<T, out ClassTree>.listRecordComponents() = func(ClassTree::recordComponents)
fun <T> SingleSelector<T, out ClassTree>.recordComponents() = listFunc(ClassTree::recordComponents)
fun <T> OptionalSelector<T, out ClassTree>.recordComponents() = listFunc(ClassTree::recordComponents)
fun <T> ManySelector<T, out ClassTree>.recordComponents() = listFunc(ClassTree::recordComponents)

fun <T> SingleSelector<T, out ClassTree>.listSuperInterfaces() = func(ClassTree::superInterfaces)
fun <T> OptionalSelector<T, out ClassTree>.listSuperInterfaces() = func(ClassTree::superInterfaces)
fun <T> ManySelector<T, out ClassTree>.listSuperInterfaces() = func(ClassTree::superInterfaces)
fun <T> SingleSelector<T, out ClassTree>.superInterfaces() = listFunc(ClassTree::superInterfaces)
fun <T> OptionalSelector<T, out ClassTree>.superInterfaces() = listFunc(ClassTree::superInterfaces)
fun <T> ManySelector<T, out ClassTree>.superInterfaces() = listFunc(ClassTree::superInterfaces)

fun <T> SingleSelector<T, out ClassTree>.listTypeParameters() = func(ClassTree::typeParameters)
fun <T> OptionalSelector<T, out ClassTree>.listTypeParameters() = func(ClassTree::typeParameters)
fun <T> ManySelector<T, out ClassTree>.listTypeParameters() = func(ClassTree::typeParameters)
fun <T> SingleSelector<T, out ClassTree>.typeParameters() = listFunc(ClassTree::typeParameters)
fun <T> OptionalSelector<T, out ClassTree>.typeParameters() = listFunc(ClassTree::typeParameters)
fun <T> ManySelector<T, out ClassTree>.typeParameters() = listFunc(ClassTree::typeParameters)

fun <T> SingleSelector<T, out ClassTree>.openBraceToken() = func(ClassTree::openBraceToken)
fun <T> OptionalSelector<T, out ClassTree>.openBraceToken() = func(ClassTree::openBraceToken)
fun <T> ManySelector<T, out ClassTree>.openBraceToken() = func(ClassTree::openBraceToken)

fun <T> SingleSelector<T, out ClassTree>.permitsKeyword() = optFunc(ClassTree::permitsKeyword)
fun <T> OptionalSelector<T, out ClassTree>.permitsKeyword() = optFunc(ClassTree::permitsKeyword)
fun <T> ManySelector<T, out ClassTree>.permitsKeyword() = optFunc(ClassTree::permitsKeyword)

fun <T> SingleSelector<T, out ClassTree>.recordCloseParenToken() = optFunc(ClassTree::recordCloseParenToken)
fun <T> OptionalSelector<T, out ClassTree>.recordCloseParenToken() = optFunc(ClassTree::recordCloseParenToken)
fun <T> ManySelector<T, out ClassTree>.recordCloseParenToken() = optFunc(ClassTree::recordCloseParenToken)

fun <T> SingleSelector<T, out ClassTree>.recordOpenParenToken() = optFunc(ClassTree::recordOpenParenToken)
fun <T> OptionalSelector<T, out ClassTree>.recordOpenParenToken() = optFunc(ClassTree::recordOpenParenToken)
fun <T> ManySelector<T, out ClassTree>.recordOpenParenToken() = optFunc(ClassTree::recordOpenParenToken)

fun <T> SingleSelector<T, out ClassTree>.simpleName() = optFunc(ClassTree::simpleName)
fun <T> OptionalSelector<T, out ClassTree>.simpleName() = optFunc(ClassTree::simpleName)
fun <T> ManySelector<T, out ClassTree>.simpleName() = optFunc(ClassTree::simpleName)

fun <T> SingleSelector<T, out ClassTree>.superClass() = optFunc(ClassTree::superClass)
fun <T> OptionalSelector<T, out ClassTree>.superClass() = optFunc(ClassTree::superClass)
fun <T> ManySelector<T, out ClassTree>.superClass() = optFunc(ClassTree::superClass)

fun <T> SingleSelector<T, out ClassTree>.symbol() = func(ClassTree::symbol)
fun <T> OptionalSelector<T, out ClassTree>.symbol() = func(ClassTree::symbol)
fun <T> ManySelector<T, out ClassTree>.symbol() = func(ClassTree::symbol)