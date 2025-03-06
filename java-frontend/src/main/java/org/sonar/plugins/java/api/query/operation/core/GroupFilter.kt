package org.sonar.plugins.java.api.query.operation.core

import org.sonar.plugins.java.api.query.Droppable
import org.sonar.plugins.java.api.query.Droppable.Drop
import org.sonar.plugins.java.api.query.Droppable.Keep
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.Selector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedFunction
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda
import org.sonar.plugins.java.api.query.graph.ir.nodes.CombineDrop
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.ParentNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.Root
import org.sonar.plugins.java.api.query.graph.ir.nodes.Scope
import org.sonar.plugins.java.api.query.graph.ir.nodes.UnScope
import org.sonar.plugins.java.api.query.operation.Operation1toOptional
import org.sonar.plugins.java.api.query.operation.composite.orElse

class GroupFilterWithScopeOperation <FROM, GROUPED, TO>(
  val groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  val grouping: IdentifiedFunction<(FROM, GROUPED) -> Droppable<TO>>,
) : Operation1toOptional<FROM, TO> {
  override fun applyTo(parent: ParentNode<FROM>): IRNode<*, out TO> {
    val scope = Scope(parent)
    val group = groupProducer(SingleSelector(Root<FROM>(), scope)).toSingle()
    val combine = CombineDrop(scope, group.current, grouping)
    return UnScope(combine, setOf(scope))
  }
}

fun <INIT, FROM, GROUPED, TO> SingleSelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> Droppable<TO>>
): OptionalSelector<INIT, TO> {
  return apply(GroupFilterWithScopeOperation(groupProducer, grouping))
}

fun <INIT, FROM, GROUPED, TO> OptionalSelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> Droppable<TO>>
): OptionalSelector<INIT, TO> {
  return apply(GroupFilterWithScopeOperation(groupProducer, grouping))
}

fun <INIT, FROM, GROUPED, TO> ManySelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> Droppable<TO>>
): ManySelector<INIT, TO> {
  return apply(GroupFilterWithScopeOperation(groupProducer, grouping))
}

private fun <A, B> dropPairFunction() = IdentifiedLambda("dropPair", "DroppablePair") { from: A, drop: Droppable<B> ->
  if (drop is Keep) Keep(Pair(from, drop.data)) else Drop
}

private fun <T> keepFunction() = IdentifiedLambda("keep", "Keep") { value: T -> Keep(value) }
private fun <FROM, TO> toDroppable(func: (SingleSelector<*, FROM>) -> OptionalSelector<*, TO>) =
  { from: SingleSelector<*, FROM> -> func(from).map(keepFunction()).orElse(Drop) }

fun <INIT, FROM, TO> SingleSelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> OptionalSelector<*, TO>
): OptionalSelector<INIT, Pair<FROM, TO>> = groupFilterWith(toDroppable(groupProducer), dropPairFunction())

fun <INIT, FROM, TO> OptionalSelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> OptionalSelector<*, TO>
): OptionalSelector<INIT, Pair<FROM, TO>> = groupFilterWith(toDroppable(groupProducer), dropPairFunction())

fun <INIT, FROM, TO> ManySelector<INIT, FROM>.groupFilterWith(
  groupProducer: (SingleSelector<*, FROM>) -> OptionalSelector<*, TO>
): ManySelector<INIT, Pair<FROM, TO>> = groupFilterWith(toDroppable(groupProducer), dropPairFunction())

private fun <T> whereFunction() = IdentifiedLambda<(T, Boolean) -> Droppable<T>>("where", "Where") { value, keep ->
  if (keep) Keep(value) else Drop
}

fun <INIT, FROM> SingleSelector<INIT, FROM>.where(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, Boolean>
): OptionalSelector<INIT, FROM> = groupFilterWith(groupProducer, whereFunction())

fun <INIT, FROM> OptionalSelector<INIT, FROM>.where(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, Boolean>
): OptionalSelector<INIT, FROM> = groupFilterWith(groupProducer, whereFunction())

fun <INIT, FROM> ManySelector<INIT, FROM>.where(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, Boolean>
): ManySelector<INIT, FROM> = groupFilterWith(groupProducer, whereFunction())
