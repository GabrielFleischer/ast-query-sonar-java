package org.sonar.plugins.java.api.query.tree

import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.tree.VarTypeTree
import org.sonar.plugins.java.api.query.operation.composite.func

fun <T> SingleSelector<T, out VarTypeTree>.varToken() = func(VarTypeTree::varToken)
fun <T> OptionalSelector<T, out VarTypeTree>.varToken() = func(VarTypeTree::varToken)
fun <T> ManySelector<T, out VarTypeTree>.varToken() = func(VarTypeTree::varToken)