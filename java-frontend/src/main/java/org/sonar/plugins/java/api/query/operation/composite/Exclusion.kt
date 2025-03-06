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

package org.sonar.plugins.java.api.query.operation.composite

import org.sonar.plugins.java.api.query.Droppable
import org.sonar.plugins.java.api.query.Droppable.Drop
import org.sonar.plugins.java.api.query.Droppable.Keep
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedFunction
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda
import org.sonar.plugins.java.api.query.operation.core.combineFilter

private fun <T> exclusionFunction(): IdentifiedFunction<(T, List<T>) -> Droppable<T>> =
  IdentifiedLambda("exclusion", "Exclusion") { elem: T, excluded: List<T> ->
    if (excluded.contains(elem)) Drop else Keep(elem)
  }

fun <INIT, CUR> SingleSelector<INIT, CUR>.exclude(other: SingleSelector<*, List<CUR>>) =
  combineFilter(other, exclusionFunction())

fun <INIT, CUR> OptionalSelector<INIT, CUR>.exclude(other: SingleSelector<*, List<CUR>>) =
  combineFilter(other, exclusionFunction())

fun <INIT, CUR> ManySelector<INIT, CUR>.exclude(other: SingleSelector<*, List<CUR>>) =
  combineFilter(other, exclusionFunction())
