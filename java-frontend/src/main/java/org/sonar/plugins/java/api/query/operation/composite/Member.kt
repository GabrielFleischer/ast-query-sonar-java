package org.sonar.plugins.java.api.query.operation.composite

import org.sonar.plugins.java.api.query.ManySelector
import org.sonar.plugins.java.api.query.OptionalSelector
import org.sonar.plugins.java.api.query.SingleSelector
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedFunction
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedFunction.Companion.fromMember
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda
import org.sonar.plugins.java.api.query.operation.core.flatMap
import org.sonar.plugins.java.api.query.operation.core.flatMapSeq
import org.sonar.plugins.java.api.query.operation.core.map
import org.sonar.plugins.java.api.query.operation.core.mapNonNull
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1

// ========================== Simple Member Mapping ==========================
inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.property(prop: KProperty1<FROM, TO>) =
  map(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.property(prop: KProperty1<FROM, TO>) =
  map(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.property(prop: KProperty1<FROM, TO>) =
  map(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.func(function: KFunction1<FROM, TO>) =
  map(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.func(function: KFunction1<FROM, TO>) =
  map(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.func(function: KFunction1<FROM, TO>) =
  map(fromMember(FROM::class, function))

// ========================== Option Member Mapping ==========================

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.optProperty(prop: KProperty1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.optProperty(prop: KProperty1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.optProperty(prop: KProperty1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.optFunc(function: KFunction1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.optFunc(function: KFunction1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.optFunc(function: KFunction1<FROM, TO>) =
  mapNonNull(fromMember(FROM::class, function))

// ========================== List Member Mapping ==========================

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.listProperty(prop: KProperty1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.listProperty(prop: KProperty1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.listProperty(prop: KProperty1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, prop))

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.listFunc(function: KFunction1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.listFunc(function: KFunction1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, function))

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.listFunc(function: KFunction1<FROM, Collection<TO>>) =
  flatMap(fromMember(FROM::class, function))

// ========================== Nullable List Member Mapping ==========================

fun <T> optListToValues(): IdentifiedFunction<(Collection<T>?) -> Sequence<T>> =
  IdentifiedLambda("optional.to.values", "Values") { it?.asSequence() ?: emptySequence() }

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.optListProperty(prop: KProperty1<FROM, Collection<TO>?>) =
  property(prop).flatMapSeq(optListToValues())

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.optListProperty(prop: KProperty1<FROM, Collection<TO>?>) =
  property(prop).flatMapSeq(optListToValues())

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.optListProperty(prop: KProperty1<FROM, Collection<TO>?>) =
  property(prop).flatMapSeq(optListToValues())

inline fun <INIT, reified FROM : Any, TO> SingleSelector<INIT, out FROM>.optListFunc(function: KFunction1<FROM, Collection<TO>?>) =
  func(function).flatMapSeq(optListToValues())

inline fun <INIT, reified FROM : Any, TO> OptionalSelector<INIT, out FROM>.optListFunc(function: KFunction1<FROM, Collection<TO>?>) =
  func(function).flatMapSeq(optListToValues())

inline fun <INIT, reified FROM : Any, TO> ManySelector<INIT, out FROM>.optListFunc(function: KFunction1<FROM, Collection<TO>?>) =
  func(function).flatMapSeq(optListToValues())
