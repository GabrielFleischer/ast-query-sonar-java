package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ModuleDirectiveTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ModuleDirectiveTree>.directiveKeyword() = func(ModuleDirectiveTree::directiveKeyword)
fun <T> OptionalSelector<T, out ModuleDirectiveTree>.directiveKeyword() = func(ModuleDirectiveTree::directiveKeyword)
fun <T> ManySelector<T, out ModuleDirectiveTree>.directiveKeyword() = func(ModuleDirectiveTree::directiveKeyword)

fun <T> SingleSelector<T, out ModuleDirectiveTree>.semicolonToken() = func(ModuleDirectiveTree::semicolonToken)
fun <T> OptionalSelector<T, out ModuleDirectiveTree>.semicolonToken() = func(ModuleDirectiveTree::semicolonToken)
fun <T> ManySelector<T, out ModuleDirectiveTree>.semicolonToken() = func(ModuleDirectiveTree::semicolonToken)