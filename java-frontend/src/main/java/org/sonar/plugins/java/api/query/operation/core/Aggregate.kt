/*
 * SonarQube Java
 * Copyright (C) 2012-2024 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.plugins.java.api.query.operation.core

import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.*
import org.sonar.plugins.java.api.query.graph.ir.nodes.Aggregate
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.ParentNode
import org.sonar.plugins.java.api.query.operation.OperationNto1
import org.sonar.plugins.java.api.query.operation.idFunction

class AggregateOperation<FROM, TO>(
  private val mapper: IdentifiedFunction<(List<FROM>) -> TO>
) : OperationNto1<FROM, TO> {
  override fun applyTo(parent: ParentNode<FROM>): IRNode<*, TO> {
    return Aggregate(parent, mapper)
  }
}

fun <INIT, IN, OUT> OptionalSelector<INIT, IN>.aggregate(aggregator: IdentifiedFunction<(List<IN>) -> OUT>): SingleSelector<INIT, OUT> {
  return apply(AggregateOperation(aggregator))
}

fun <INIT, IN, OUT> ManySelector<INIT, IN>.aggregate(aggregator: IdentifiedFunction<(List<IN>) -> OUT>): SingleSelector<INIT, OUT> {
  return apply(AggregateOperation(aggregator))
}


fun <INIT, IN, OUT> OptionalSelector<INIT, IN>.aggregate(aggregator: (List<IN>) -> OUT) =
  aggregate(idFunction(lambda = aggregator))
fun <INIT, IN, OUT> ManySelector<INIT, IN>.aggregate(aggregator: (List<IN>) -> OUT) =
  aggregate(idFunction(lambda = aggregator))

fun <INIT, IN> ManySelector<INIT, IN>.aggregate(): SingleSelector<INIT, List<IN>> = aggregate(identity("Aggregate"))
fun <INIT, IN> OptionalSelector<INIT, IN>.aggregate(): SingleSelector<INIT, List<IN>> = aggregate(identity("Aggregate"))

fun <INIT, IN> ManySelector<INIT, IN>.count() = aggregate(CountFunction)

fun <INIT, IN> OptionalSelector<INIT, IN>.isPresent() = aggregate(ExistFunction)
fun <INIT, IN> ManySelector<INIT, IN>.exists() = aggregate(ExistFunction)

fun <INIT, IN> OptionalSelector<INIT, IN>.notPresent() = aggregate(NotExistFunction)
fun <INIT, IN> ManySelector<INIT, IN>.noneExists() = aggregate(NotExistFunction)
