package org.sonar.plugins.java.api.query.operation.composite

import org.sonar.plugins.java.api.query.Droppable.Drop
import org.sonar.plugins.java.api.query.Droppable.Keep
import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda
import org.sonar.plugins.java.api.query.operation.core.aggregate
import org.sonar.plugins.java.api.query.operation.core.combine
import org.sonar.plugins.java.api.query.operation.core.combineFilter
import org.sonar.plugins.java.api.query.operation.core.filterNonNull
import org.sonar.plugins.java.api.query.operation.core.map

fun <T1, T2> ifPresentUse() = IdentifiedLambda("ifPresentUse", "IfPresentUse") { value: T1, other: List<T2> -> if (other.isNotEmpty()) Keep(value) else Drop }

fun <INIT, CUR, OTHER> OptionalSelector<INIT, CUR>.ifPresentUse(other: SingleSelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <INIT, CUR, OTHER> OptionalSelector<INIT, CUR>.ifPresentUse(other: OptionalSelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <INIT, CUR, OTHER> OptionalSelector<INIT, CUR>.ifPresentUse(other: ManySelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <INIT, CUR, OTHER> ManySelector<INIT, CUR>.ifPresentUse(other: SingleSelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <INIT, CUR, OTHER> ManySelector<INIT, CUR>.ifPresentUse(other: OptionalSelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <INIT, CUR, OTHER> ManySelector<INIT, CUR>.ifPresentUse(other: ManySelector<*, OTHER>) =
  other.combineFilter(this.aggregate(), ifPresentUse())

fun <T> ifTrueUse() = IdentifiedLambda("ifTrueUse", "IfTrueUse") { value: T, condition: Boolean -> if (condition) Keep(value) else Drop }

fun <INIT, OTHER> SingleSelector<INIT, Boolean>.ifTrueUse(other: SingleSelector<*, OTHER>) =
  other.combineFilter(this, ifTrueUse())

fun <INIT, OTHER> SingleSelector<INIT, Boolean>.ifTrueUse(other: OptionalSelector<*, OTHER>) =
  other.combineFilter(this, ifTrueUse())

fun <INIT, OTHER> SingleSelector<INIT, Boolean>.ifTrueUse(other: ManySelector<*, OTHER>) =
  other.combineFilter(this, ifTrueUse())

fun <T> orElseUse() = IdentifiedLambda("orElseUse", "OrElseUse") { orElse: T, value: T? -> value ?: orElse }
fun <T> ifNoneExistsUse() = IdentifiedLambda("ifNonExistsUse", "IfNonExistsUse") { first: List<T>, other: List<T> -> if (first.isNotEmpty()) first else other }
fun <T> singleton() = IdentifiedLambda("singleton", "Singleton") { value: T -> listOf(value) }

fun <INIT, T> OptionalSelector<*, out T>.orElseUse(other: SingleSelector<INIT, out T>): SingleSelector<INIT, T> =
  other.combine(this.toSingle(), orElseUse())

fun <INIT, T> OptionalSelector<*, out T>.orElseUse(other: OptionalSelector<INIT, out T>): OptionalSelector<INIT, T> =
  other.toSingle().combine(this.toSingle(), orElseUse()).filterNonNull()

fun <INIT, T> OptionalSelector<*, out T>.orElseUse(other: ManySelector<INIT, out T>): ManySelector<INIT, T> =
  other.aggregate().combine(this.aggregate(), ifNoneExistsUse()).flatten()

fun <INIT, T> ManySelector<*, out T>.ifNoneExistsUse(other: SingleSelector<INIT, out T>): ManySelector<INIT, T> =
  other.map(singleton()).combine(this.aggregate(), ifNoneExistsUse()).flatten()

fun <INIT, T> ManySelector<*, out T>.ifNoneExistsUse(other: OptionalSelector<INIT, out T>): ManySelector<INIT, T> =
  other.aggregate().combine(this.aggregate(), ifNoneExistsUse()).flatten()

fun <INIT, T> ManySelector<*, out T>.ifNoneExistsUse(other: ManySelector<INIT, out T>): ManySelector<INIT, T> =
  other.aggregate().combine(this.aggregate(), ifNoneExistsUse()).flatten()
