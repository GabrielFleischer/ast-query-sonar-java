package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ExportsDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ExportsDirectiveTree>.listModuleNames() = func(ExportsDirectiveTree::moduleNames)
fun <T> OptionalSelector<T, out ExportsDirectiveTree>.listModuleNames() = func(ExportsDirectiveTree::moduleNames)
fun <T> ManySelector<T, out ExportsDirectiveTree>.listModuleNames() = func(ExportsDirectiveTree::moduleNames)
fun <T> SingleSelector<T, out ExportsDirectiveTree>.moduleNames() = listFunc(ExportsDirectiveTree::moduleNames)
fun <T> OptionalSelector<T, out ExportsDirectiveTree>.moduleNames() = listFunc(ExportsDirectiveTree::moduleNames)
fun <T> ManySelector<T, out ExportsDirectiveTree>.moduleNames() = listFunc(ExportsDirectiveTree::moduleNames)

fun <T> SingleSelector<T, out ExportsDirectiveTree>.packageName() = func(ExportsDirectiveTree::packageName)
fun <T> OptionalSelector<T, out ExportsDirectiveTree>.packageName() = func(ExportsDirectiveTree::packageName)
fun <T> ManySelector<T, out ExportsDirectiveTree>.packageName() = func(ExportsDirectiveTree::packageName)

fun <T> SingleSelector<T, out ExportsDirectiveTree>.toKeyword() = optFunc(ExportsDirectiveTree::toKeyword)
fun <T> OptionalSelector<T, out ExportsDirectiveTree>.toKeyword() = optFunc(ExportsDirectiveTree::toKeyword)
fun <T> ManySelector<T, out ExportsDirectiveTree>.toKeyword() = optFunc(ExportsDirectiveTree::toKeyword)