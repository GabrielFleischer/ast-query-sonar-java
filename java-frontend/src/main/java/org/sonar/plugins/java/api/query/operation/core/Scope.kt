package org.sonar.plugins.java.api.query.operation.core

import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.Selector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.GraphUtils
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.ParentNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.Root
import org.sonar.plugins.java.api.query.graph.ir.nodes.Scope
import org.sonar.plugins.java.api.query.graph.ir.nodes.UnScope
import org.sonar.plugins.java.api.query.operation.Operation1to1
import org.sonar.plugins.java.api.query.operation.Operation1toN
import org.sonar.plugins.java.api.query.operation.Operation1toOptional

typealias Single<INIT, CUR> = SingleSelector<INIT, CUR>
typealias Option<INIT, CUR> = OptionalSelector<INIT, CUR>
typealias Many<INIT, CUR> = ManySelector<INIT, CUR>

private fun <CUR, TO> createScope(
  from: ParentNode<CUR>,
  builder: (Single<CUR, CUR>) -> Selector<*, TO, *>
): IRNode<*, TO> {
  val builderInput = Single(Root<CUR>(), from)
  val end = builder(builderInput)

  val scope = Scope(from)
  val unscope = UnScope(end.current, scope)

  GraphUtils.copySubTree(from, scope, unscope)

  return unscope
}

class ScopeOperation1to1<CUR, TO>(val builder: (Single<CUR, CUR>) -> Selector<*, TO, *>) : Operation1to1<CUR, TO> {
  override fun applyTo(parent: ParentNode<CUR>): IRNode<*, out TO> {
    return createScope(parent, builder)
  }
}

class ScopeOperation1toOpt<CUR, TO>(val builder: (Single<CUR, CUR>) -> Selector<*, TO, *>) : Operation1toOptional<CUR, TO> {
  override fun applyTo(parent: ParentNode<CUR>): IRNode<*, out TO> {
    return createScope(parent, builder)
  }
}

class ScopeOperation1toN<CUR, TO>(val builder: (Single<CUR, CUR>) -> Selector<*, TO, *>) : Operation1toN<CUR, TO> {
  override fun applyTo(parent: ParentNode<CUR>): IRNode<*, out TO> {
    return createScope(parent, builder)
  }
}

fun <INIT, CUR, TO> Single<INIT, CUR>.scopedSingle(builder: (Single<CUR, CUR>) -> Selector<*, TO, TO>): Single<INIT, TO> =
  apply(ScopeOperation1to1(builder))

fun <INIT, CUR, TO> Single<INIT, CUR>.scopedOption(builder: (Single<CUR, CUR>) -> Option<*, TO>): Option<INIT, TO> =
  apply(ScopeOperation1toOpt(builder))

fun <INIT, CUR, TO> Single<INIT, CUR>.scoped(builder: (Single<CUR, CUR>) -> Selector<*, TO, *>): Many<INIT, TO> =
  apply(ScopeOperation1toN(builder))

fun <INIT, CUR, TO> Option<INIT, CUR>.scopedSingle(builder: (Single<CUR, CUR>) -> Single<*, TO>): Option<INIT, TO> =
  apply(ScopeOperation1to1(builder))

fun <INIT, CUR, TO> Option<INIT, CUR>.scopedOption(builder: (Single<CUR, CUR>) -> Option<*, TO>): Option<INIT, TO> =
  apply(ScopeOperation1toOpt(builder))

fun <INIT, CUR, TO> Option<INIT, CUR>.scoped(builder: (Single<CUR, CUR>) -> Selector<*, TO, *>): Many<INIT, TO> =
  apply(ScopeOperation1toN(builder))

fun <INIT, CUR, TO> Many<INIT, CUR>.scoped(builder: (Single<CUR, CUR>) -> Selector<*, TO, *>): Many<INIT, TO> =
  apply(ScopeOperation1toN(builder))

fun <INIT, CUR> Single<INIT, CUR>.scopeTo(from: Single<INIT, *>) = from.scopedSingle { this }
fun <INIT, CUR> Single<INIT, CUR>.scopeTo(from: Option<INIT, *>) = from.scopedSingle { this }
fun <INIT, CUR> Single<INIT, CUR>.scopeTo(from: Many<INIT, *>) = from.scoped { this }

fun <INIT, CUR> Option<INIT, CUR>.scopeTo(from: Single<INIT, *>) = from.scopedOption { this }
fun <INIT, CUR> Option<INIT, CUR>.scopeTo(from: Option<INIT, *>) = from.scopedOption { this }
fun <INIT, CUR> Option<INIT, CUR>.scopeTo(from: Many<INIT, *>) = from.scoped { this }

fun <INIT, CUR> Many<INIT, CUR>.scopeTo(from: Single<INIT, *>) = from.scoped { this }
fun <INIT, CUR> Many<INIT, CUR>.scopeTo(from: Option<INIT, *>) = from.scoped { this }
fun <INIT, CUR> Many<INIT, CUR>.scopeTo(from: Many<INIT, *>) = from.scoped { this }

