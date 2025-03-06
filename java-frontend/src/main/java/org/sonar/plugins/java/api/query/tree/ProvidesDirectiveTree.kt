package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ProvidesDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ProvidesDirectiveTree>.listTypeNames() = func(ProvidesDirectiveTree::typeNames)
fun <T> OptionalSelector<T, out ProvidesDirectiveTree>.listTypeNames() = func(ProvidesDirectiveTree::typeNames)
fun <T> ManySelector<T, out ProvidesDirectiveTree>.listTypeNames() = func(ProvidesDirectiveTree::typeNames)
fun <T> SingleSelector<T, out ProvidesDirectiveTree>.typeNames() = listFunc(ProvidesDirectiveTree::typeNames)
fun <T> OptionalSelector<T, out ProvidesDirectiveTree>.typeNames() = listFunc(ProvidesDirectiveTree::typeNames)
fun <T> ManySelector<T, out ProvidesDirectiveTree>.typeNames() = listFunc(ProvidesDirectiveTree::typeNames)

fun <T> SingleSelector<T, out ProvidesDirectiveTree>.typeName() = func(ProvidesDirectiveTree::typeName)
fun <T> OptionalSelector<T, out ProvidesDirectiveTree>.typeName() = func(ProvidesDirectiveTree::typeName)
fun <T> ManySelector<T, out ProvidesDirectiveTree>.typeName() = func(ProvidesDirectiveTree::typeName)

fun <T> SingleSelector<T, out ProvidesDirectiveTree>.withKeyword() = func(ProvidesDirectiveTree::withKeyword)
fun <T> OptionalSelector<T, out ProvidesDirectiveTree>.withKeyword() = func(ProvidesDirectiveTree::withKeyword)
fun <T> ManySelector<T, out ProvidesDirectiveTree>.withKeyword() = func(ProvidesDirectiveTree::withKeyword)