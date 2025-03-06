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
package org.sonar.plugins.java.api.query.exec.batch

import org.sonar.plugins.java.api.query.Query
import org.sonar.plugins.java.api.query.exec.ScopeTest
import org.sonar.plugins.java.api.query.graph.exec.batch.BatchBuilder
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.Root
import java.util.function.Function

class BatchScopeTest : ScopeTest() {
  override fun <IN, OUT> buildQuery(
    start: Root<IN>, end: IRNode<*, out OUT>, transform: Function<List<OUT>, List<OUT>>
  ): Query<IN, List<OUT>> {
    return BatchBuilder().buildQuery(start, end) { values -> transform.apply(values) }
  }
}
