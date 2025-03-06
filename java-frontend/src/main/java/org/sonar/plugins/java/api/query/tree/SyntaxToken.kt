package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.SyntaxToken
import org.sonar.plugins.java.api.query.operation.composite.func
import org.sonar.plugins.java.api.query.operation.composite.listFunc

fun <T> SingleSelector<T, out SyntaxToken>.listTrivias() = func(SyntaxToken::trivias)
fun <T> OptionalSelector<T, out SyntaxToken>.listTrivias() = func(SyntaxToken::trivias)
fun <T> ManySelector<T, out SyntaxToken>.listTrivias() = func(SyntaxToken::trivias)
fun <T> SingleSelector<T, out SyntaxToken>.trivias() = listFunc(SyntaxToken::trivias)
fun <T> OptionalSelector<T, out SyntaxToken>.trivias() = listFunc(SyntaxToken::trivias)
fun <T> ManySelector<T, out SyntaxToken>.trivias() = listFunc(SyntaxToken::trivias)

fun <T> SingleSelector<T, out SyntaxToken>.range() = func(SyntaxToken::range)
fun <T> OptionalSelector<T, out SyntaxToken>.range() = func(SyntaxToken::range)
fun <T> ManySelector<T, out SyntaxToken>.range() = func(SyntaxToken::range)

fun <T> SingleSelector<T, out SyntaxToken>.text() = func(SyntaxToken::text)
fun <T> OptionalSelector<T, out SyntaxToken>.text() = func(SyntaxToken::text)
fun <T> ManySelector<T, out SyntaxToken>.text() = func(SyntaxToken::text)