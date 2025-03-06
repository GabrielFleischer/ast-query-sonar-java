package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ModifierKeywordTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out ModifierKeywordTree>.keyword() = func(ModifierKeywordTree::keyword)
fun <T> OptionalSelector<T, out ModifierKeywordTree>.keyword() = func(ModifierKeywordTree::keyword)
fun <T> ManySelector<T, out ModifierKeywordTree>.keyword() = func(ModifierKeywordTree::keyword)

fun <T> SingleSelector<T, out ModifierKeywordTree>.modifier() = func(ModifierKeywordTree::modifier)
fun <T> OptionalSelector<T, out ModifierKeywordTree>.modifier() = func(ModifierKeywordTree::modifier)
fun <T> ManySelector<T, out ModifierKeywordTree>.modifier() = func(ModifierKeywordTree::modifier)