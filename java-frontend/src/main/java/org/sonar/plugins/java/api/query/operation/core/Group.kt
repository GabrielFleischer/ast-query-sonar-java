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
import org.sonar.plugins.java.api.query.graph.ir.nodes.Combine
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.ParentNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.Root
import org.sonar.plugins.java.api.query.graph.ir.nodes.Scope
import org.sonar.plugins.java.api.query.graph.ir.nodes.UnScope
import org.sonar.plugins.java.api.query.operation.Operation1to1

class GroupWithScopeOperation<FROM, GROUPED, TO>(
  val groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  val grouping: IdentifiedFunction<(FROM, GROUPED) -> TO>,
) : Operation1to1<FROM, TO> {
  override fun applyTo(parent: ParentNode<FROM>): IRNode<*, out TO> {
    val scope = Scope(parent)
    val group = groupProducer(SingleSelector(Root<FROM>(), scope)).toSingle()
    val combine = Combine(scope, group.current, grouping)
    return UnScope(combine, setOf(scope))
  }
}

fun <INIT, FROM, GROUPED, TO> SingleSelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> TO>
): SingleSelector<INIT, TO> {
  return apply(GroupWithScopeOperation(groupProducer, grouping))
}

fun <INIT, FROM, GROUPED, TO> OptionalSelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> TO>
): OptionalSelector<INIT, TO> {
  return apply(GroupWithScopeOperation(groupProducer, grouping))
}

fun <INIT, FROM, GROUPED, TO> ManySelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>,
  grouping: IdentifiedFunction<(FROM, GROUPED) -> TO>
): ManySelector<INIT, TO> {
  return apply(GroupWithScopeOperation(groupProducer, grouping))
}

private fun <A, B> pairFunction() = IdentifiedLambda<(A, B) -> Pair<A, B>>("pair", "Pair", ::Pair)

fun <INIT, FROM, GROUPED> SingleSelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>
): SingleSelector<INIT, Pair<FROM, GROUPED>> = groupWith(groupProducer, pairFunction())

fun <INIT, FROM, GROUPED> OptionalSelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>
): OptionalSelector<INIT, Pair<FROM, GROUPED>> = groupWith(groupProducer, pairFunction())

fun <INIT, FROM, GROUPED> ManySelector<INIT, FROM>.groupWith(
  groupProducer: (SingleSelector<*, FROM>) -> Selector<*, *, GROUPED>
): ManySelector<INIT, Pair<FROM, GROUPED>> = groupWith(groupProducer, pairFunction())
