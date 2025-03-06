package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.OpensDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.optFunc
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out OpensDirectiveTree>.listModuleNames() = func(OpensDirectiveTree::moduleNames)
fun <T> OptionalSelector<T, out OpensDirectiveTree>.listModuleNames() = func(OpensDirectiveTree::moduleNames)
fun <T> ManySelector<T, out OpensDirectiveTree>.listModuleNames() = func(OpensDirectiveTree::moduleNames)
fun <T> SingleSelector<T, out OpensDirectiveTree>.moduleNames() = listFunc(OpensDirectiveTree::moduleNames)
fun <T> OptionalSelector<T, out OpensDirectiveTree>.moduleNames() = listFunc(OpensDirectiveTree::moduleNames)
fun <T> ManySelector<T, out OpensDirectiveTree>.moduleNames() = listFunc(OpensDirectiveTree::moduleNames)

fun <T> SingleSelector<T, out OpensDirectiveTree>.packageName() = func(OpensDirectiveTree::packageName)
fun <T> OptionalSelector<T, out OpensDirectiveTree>.packageName() = func(OpensDirectiveTree::packageName)
fun <T> ManySelector<T, out OpensDirectiveTree>.packageName() = func(OpensDirectiveTree::packageName)

fun <T> SingleSelector<T, out OpensDirectiveTree>.toKeyword() = optFunc(OpensDirectiveTree::toKeyword)
fun <T> OptionalSelector<T, out OpensDirectiveTree>.toKeyword() = optFunc(OpensDirectiveTree::toKeyword)
fun <T> ManySelector<T, out OpensDirectiveTree>.toKeyword() = optFunc(OpensDirectiveTree::toKeyword)