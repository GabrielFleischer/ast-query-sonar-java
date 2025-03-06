package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.SyntaxTrivia
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out SyntaxTrivia>.comment() = func(SyntaxTrivia::comment)
fun <T> OptionalSelector<T, out SyntaxTrivia>.comment() = func(SyntaxTrivia::comment)
fun <T> ManySelector<T, out SyntaxTrivia>.comment() = func(SyntaxTrivia::comment)

fun <T> SingleSelector<T, out SyntaxTrivia>.range() = func(SyntaxTrivia::range)
fun <T> OptionalSelector<T, out SyntaxTrivia>.range() = func(SyntaxTrivia::range)
fun <T> ManySelector<T, out SyntaxTrivia>.range() = func(SyntaxTrivia::range)