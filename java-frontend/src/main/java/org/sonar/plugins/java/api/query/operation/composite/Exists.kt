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

import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.ExistFunction
import org.sonar.plugins.java.api.query.graph.ir.NotExistFunction
import org.sonar.plugins.java.api.query.graph.ir.nodes.IRNode
import org.sonar.plugins.java.api.query.graph.ir.nodes.ParentNode
import org.sonar.plugins.java.api.query.operation.OperationNto1
import org.sonar.plugins.java.api.query.operation.core.aggregate

fun <INIT, CUR> OptionalSelector<INIT, CUR>.isPresent(): SingleSelector<INIT, Boolean> =
  aggregate(ExistFunction)

fun <INIT, CUR> ManySelector<INIT, CUR>.exists(): SingleSelector<INIT, Boolean>  =
  aggregate(ExistFunction)

fun <INIT, CUR> OptionalSelector<INIT, CUR>.notPresent(): SingleSelector<INIT, Boolean>  =
  aggregate(NotExistFunction)

fun <INIT, CUR> ManySelector<INIT, CUR>.noneExists(): SingleSelector<INIT, Boolean>  =
  aggregate(NotExistFunction)
