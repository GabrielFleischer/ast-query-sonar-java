package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.RequiresDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out RequiresDirectiveTree>.listModifiers() = func(RequiresDirectiveTree::modifiers)
fun <T> OptionalSelector<T, out RequiresDirectiveTree>.listModifiers() = func(RequiresDirectiveTree::modifiers)
fun <T> ManySelector<T, out RequiresDirectiveTree>.listModifiers() = func(RequiresDirectiveTree::modifiers)
fun <T> SingleSelector<T, out RequiresDirectiveTree>.modifiers() = listFunc(RequiresDirectiveTree::modifiers)
fun <T> OptionalSelector<T, out RequiresDirectiveTree>.modifiers() = listFunc(RequiresDirectiveTree::modifiers)
fun <T> ManySelector<T, out RequiresDirectiveTree>.modifiers() = listFunc(RequiresDirectiveTree::modifiers)

fun <T> SingleSelector<T, out RequiresDirectiveTree>.listModuleName() = func(RequiresDirectiveTree::moduleName)
fun <T> OptionalSelector<T, out RequiresDirectiveTree>.listModuleName() = func(RequiresDirectiveTree::moduleName)
fun <T> ManySelector<T, out RequiresDirectiveTree>.listModuleName() = func(RequiresDirectiveTree::moduleName)
fun <T> SingleSelector<T, out RequiresDirectiveTree>.moduleName() = listFunc(RequiresDirectiveTree::moduleName)
fun <T> OptionalSelector<T, out RequiresDirectiveTree>.moduleName() = listFunc(RequiresDirectiveTree::moduleName)
fun <T> ManySelector<T, out RequiresDirectiveTree>.moduleName() = listFunc(RequiresDirectiveTree::moduleName)