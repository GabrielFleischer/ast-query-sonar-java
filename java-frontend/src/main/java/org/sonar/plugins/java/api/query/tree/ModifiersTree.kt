package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ModifiersTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ModifiersTree>.listAnnotations() = func(ModifiersTree::annotations)
fun <T> OptionalSelector<T, out ModifiersTree>.listAnnotations() = func(ModifiersTree::annotations)
fun <T> ManySelector<T, out ModifiersTree>.listAnnotations() = func(ModifiersTree::annotations)
fun <T> SingleSelector<T, out ModifiersTree>.annotations() = listFunc(ModifiersTree::annotations)
fun <T> OptionalSelector<T, out ModifiersTree>.annotations() = listFunc(ModifiersTree::annotations)
fun <T> ManySelector<T, out ModifiersTree>.annotations() = listFunc(ModifiersTree::annotations)

fun <T> SingleSelector<T, out ModifiersTree>.listModifiers() = func(ModifiersTree::modifiers)
fun <T> OptionalSelector<T, out ModifiersTree>.listModifiers() = func(ModifiersTree::modifiers)
fun <T> ManySelector<T, out ModifiersTree>.listModifiers() = func(ModifiersTree::modifiers)
fun <T> SingleSelector<T, out ModifiersTree>.modifiers() = listFunc(ModifiersTree::modifiers)
fun <T> OptionalSelector<T, out ModifiersTree>.modifiers() = listFunc(ModifiersTree::modifiers)
fun <T> ManySelector<T, out ModifiersTree>.modifiers() = listFunc(ModifiersTree::modifiers)