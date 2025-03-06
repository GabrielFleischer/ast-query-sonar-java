package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypeArguments
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out TypeArguments>.closeBracketToken() = func(TypeArguments::closeBracketToken)
fun <T> OptionalSelector<T, out TypeArguments>.closeBracketToken() = func(TypeArguments::closeBracketToken)
fun <T> ManySelector<T, out TypeArguments>.closeBracketToken() = func(TypeArguments::closeBracketToken)

fun <T> SingleSelector<T, out TypeArguments>.openBracketToken() = func(TypeArguments::openBracketToken)
fun <T> OptionalSelector<T, out TypeArguments>.openBracketToken() = func(TypeArguments::openBracketToken)
fun <T> ManySelector<T, out TypeArguments>.openBracketToken() = func(TypeArguments::openBracketToken)