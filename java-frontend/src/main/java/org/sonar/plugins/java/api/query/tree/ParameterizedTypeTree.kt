package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.ParameterizedTypeTree
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out ParameterizedTypeTree>.listTypeArguments() = func(ParameterizedTypeTree::typeArguments)
fun <T> OptionalSelector<T, out ParameterizedTypeTree>.listTypeArguments() = func(ParameterizedTypeTree::typeArguments)
fun <T> ManySelector<T, out ParameterizedTypeTree>.listTypeArguments() = func(ParameterizedTypeTree::typeArguments)
fun <T> SingleSelector<T, out ParameterizedTypeTree>.typeArguments() = listFunc(ParameterizedTypeTree::typeArguments)
fun <T> OptionalSelector<T, out ParameterizedTypeTree>.typeArguments() = listFunc(ParameterizedTypeTree::typeArguments)
fun <T> ManySelector<T, out ParameterizedTypeTree>.typeArguments() = listFunc(ParameterizedTypeTree::typeArguments)

fun <T> SingleSelector<T, out ParameterizedTypeTree>.type() = func(ParameterizedTypeTree::type)
fun <T> OptionalSelector<T, out ParameterizedTypeTree>.type() = func(ParameterizedTypeTree::type)
fun <T> ManySelector<T, out ParameterizedTypeTree>.type() = func(ParameterizedTypeTree::type)