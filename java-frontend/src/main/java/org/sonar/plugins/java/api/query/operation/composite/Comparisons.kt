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
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda
import org.sonar.plugins.java.api.query.operation.core.combine
import org.sonar.plugins.java.api.query.operation.core.map

private fun <T> constantComparator(constant: T, comparator: IdentifiedLambda<(T, T) -> Boolean>) =
  IdentifiedLambda(
    id = comparator.id + ".constant($constant)",
    desc = comparator.desc,
    function = { const: T -> comparator.function(const, constant) }
  )

fun <INIT, CUR> SingleSelector<INIT, CUR>.compareConst(
  constant: CUR,
  comparator: IdentifiedLambda<(CUR, CUR) -> Boolean>
): SingleSelector<INIT, Boolean> = map(constantComparator(constant, comparator))

fun <INIT, CUR> OptionalSelector<INIT, CUR>.compareConst(
  constant: CUR,
  comparator: IdentifiedLambda<(CUR, CUR) -> Boolean>
): OptionalSelector<INIT, Boolean> = map(constantComparator(constant, comparator))

fun <INIT, CUR> ManySelector<INIT, CUR>.compareConst(
  constant: CUR,
  comparator: IdentifiedLambda<(CUR, CUR) -> Boolean>
): ManySelector<INIT, Boolean> = map(constantComparator(constant, comparator))

private val equalsFunction = IdentifiedLambda("equals", "Equals") { a: Any?, b: Any? -> a == b }
infix fun <INIT, CUR> SingleSelector<INIT, CUR>.eq(other: SingleSelector<*, CUR>) = combine(other, equalsFunction)
infix fun <INIT, CUR> OptionalSelector<INIT, CUR>.eq(other: SingleSelector<*, CUR>) = combine(other, equalsFunction)
infix fun <INIT, CUR> ManySelector<INIT, CUR>.eq(other: SingleSelector<*, CUR>) = combine(other, equalsFunction)

infix fun <INIT, CUR> SingleSelector<INIT, CUR>.eq(constant: CUR) = compareConst(constant, equalsFunction)
infix fun <INIT, CUR> OptionalSelector<INIT, CUR>.eq(constant: CUR) = compareConst(constant, equalsFunction)
infix fun <INIT, CUR> ManySelector<INIT, CUR>.eq(constant: CUR) = compareConst(constant, equalsFunction)

private val greaterFunction = IdentifiedLambda("greater", "Greater") { a: Int, b: Int -> a > b }
infix fun <INIT> SingleSelector<INIT, Int>.gr(other: SingleSelector<*, Int>) = combine(other, greaterFunction)
infix fun <INIT> OptionalSelector<INIT, Int>.gr(other: SingleSelector<*, Int>) = combine(other, greaterFunction)
infix fun <INIT> ManySelector<INIT, Int>.gr(other: SingleSelector<*, Int>) = combine(other, greaterFunction)

infix fun <INIT> SingleSelector<INIT, Int>.gr(constant: Int) = compareConst(constant, greaterFunction)
infix fun <INIT> OptionalSelector<INIT, Int>.gr(constant: Int) = compareConst(constant, greaterFunction)
infix fun <INIT> ManySelector<INIT, Int>.gr(constant: Int) = compareConst(constant, greaterFunction)

/* TODO Implement the rest of the comparison functions
infix fun <INIT> SingleSelector<INIT, Int>.gre(other: Selector<*, Int, *>) = compare(other) { a, b -> a >= b }
infix fun <INIT> OptionalSelector<INIT, Int>.gre(other: Selector<*, Int, *>) = compare(other) { a, b -> a >= b }
infix fun <INIT> ManySelector<INIT, Int>.gre(other: Selector<*, Int, *>) = compare(other) { a, b -> a >= b }

infix fun <INIT> SingleSelector<INIT, Int>.gre(constant: Int) = compareConst(constant) { a, b -> a >= b }
infix fun <INIT> OptionalSelector<INIT, Int>.gre(constant: Int) = compareConst(constant) { a, b -> a >= b }
infix fun <INIT> ManySelector<INIT, Int>.gre(constant: Int) = compareConst(constant) { a, b -> a >= b }

infix fun <INIT> SingleSelector<INIT, Int>.lr(other: Selector<*, Int, *>) = compare(other) { a, b -> a < b }
infix fun <INIT> OptionalSelector<INIT, Int>.lr(other: Selector<*, Int, *>) = compare(other) { a, b -> a < b }
infix fun <INIT> ManySelector<INIT, Int>.lr(other: Selector<*, Int, *>) = compare(other) { a, b -> a < b }

infix fun <INIT> SingleSelector<INIT, Int>.lr(constant: Int) = compareConst(constant) { a, b -> a < b }
infix fun <INIT> OptionalSelector<INIT, Int>.lr(constant: Int) = compareConst(constant) { a, b -> a < b }
infix fun <INIT> ManySelector<INIT, Int>.lr(constant: Int) = compareConst(constant) { a, b -> a < b }

infix fun <INIT> SingleSelector<INIT, Int>.lre(other: Selector<*, Int, *>) = compare(other) { a, b -> a <= b }
infix fun <INIT> OptionalSelector<INIT, Int>.lre(other: Selector<*, Int, *>) = compare(other) { a, b -> a <= b }
infix fun <INIT> ManySelector<INIT, Int>.lre(other: Selector<*, Int, *>) = compare(other) { a, b -> a <= b }

infix fun <INIT> SingleSelector<INIT, Int>.lre(constant: Int) = compareConst(constant) { a, b -> a <= b }
infix fun <INIT> OptionalSelector<INIT, Int>.lre(constant: Int) = compareConst(constant) { a, b -> a <= b }
infix fun <INIT> ManySelector<INIT, Int>.lre(constant: Int) = compareConst(constant) { a, b -> a <= b }
*/
