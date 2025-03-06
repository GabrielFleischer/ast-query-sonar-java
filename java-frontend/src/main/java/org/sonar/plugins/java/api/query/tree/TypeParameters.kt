package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.TypeParameters
import org.sonar.plugins.java.api.query.operation.composite.optFunc

fun <T> SingleSelector<T, out TypeParameters>.closeBracketToken() = optFunc(TypeParameters::closeBracketToken)
fun <T> OptionalSelector<T, out TypeParameters>.closeBracketToken() = optFunc(TypeParameters::closeBracketToken)
fun <T> ManySelector<T, out TypeParameters>.closeBracketToken() = optFunc(TypeParameters::closeBracketToken)

fun <T> SingleSelector<T, out TypeParameters>.openBracketToken() = optFunc(TypeParameters::openBracketToken)
fun <T> OptionalSelector<T, out TypeParameters>.openBracketToken() = optFunc(TypeParameters::openBracketToken)
fun <T> ManySelector<T, out TypeParameters>.openBracketToken() = optFunc(TypeParameters::openBracketToken)