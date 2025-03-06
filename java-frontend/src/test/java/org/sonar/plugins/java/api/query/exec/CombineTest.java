/*
 * SonarQube Java
 * Copyright (C) 2012-2025 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Sonar Source-Available License Version 1, as published by SonarSource SA.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the Sonar Source-Available License for more details.
 *
 * You should have received a copy of the Sonar Source-Available License
 * along with this program; if not, see https://sonarsource.com/license/ssal/
 */
package org.sonar.plugins.java.api.query.exec;

import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sonar.java.model.JavaTree;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.query.Query;
import org.sonar.plugins.java.api.query.graph.ir.IdentifiedLambda;
import org.sonar.plugins.java.api.query.graph.ir.nodes.*;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class CombineTest {

  @Test
  public void noScopeCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var left = mapNode(flatten, s -> s + "-");
    var right = mapNode(flatten, s -> s + "!");
    var combine = combineNode(left, right, String::concat);

    var ctx = mockCtx();
    var query = getQuery(root, combine);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = Set.of(
      "A-A!", "A-B!", "A-C!",
      "B-A!", "B-B!", "B-C!",
      "C-A!", "C-B!", "C-C!"
    );

    Assertions.assertEquals(expected, Set.copyOf(result));
  }

  @Test
  public void scopeCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scopeStart = new Scope<>(flatten, 0);
    var left = mapNode(scopeStart, s -> s + "-");
    var right = mapNode(scopeStart, s -> s + "!");
    var combine = combineNode(left, right, String::concat);
    var scopeEnd = new UnScope<>(combine, scopeStart);

    var ctx = mockCtx();
    var query = getQuery(root, scopeEnd);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of("A-A!", "B-B!", "C-C!");

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void unbalancedScopeCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);
    var left = mapNode(scope1Start, s -> s + "-");

    var right = flatMapNode(scope1Start, s -> List.of("1" + s, "2" + s, "3" + s));
    var scope2Start = new Scope<>(right, 1);

    var combine = combineNode(left, scope2Start, String::concat);
    var scope1End = new UnScope<>(combine, scope1Start);
    var scope2End = new UnScope<>(scope1End, scope2Start);

    var ctx = mockCtx();
    var query = getQuery(root, scope2End);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(
      "A-1A", "A-2A", "A-3A",
      "B-1B", "B-2B", "B-3B",
      "C-1C", "C-2C", "C-3C"
    );

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void unbalancedDoubleScopeCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);
    var left = mapNode(scope1Start, s -> s + "-");

    var right1 = flatMapNode(scope1Start, s -> List.of("1" + s, "2" + s, "3" + s));
    var scope2Start = new Scope<>(right1, 1);
    var right2 = flatMapNode(scope2Start, s -> List.of("a" + s, "b" + s, "c" + s));
    var scope3Start = new Scope<>(right2, 2);

    var combine = combineNode(left, scope3Start, String::concat);
    var scope1End = new UnScope<>(combine, scope1Start);
    var scope2End = new UnScope<>(scope1End, scope2Start);
    var scope3End = new UnScope<>(scope2End, scope3Start);

    var ctx = mockCtx();
    var query = getQuery(root, scope3End);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(
      "A-a1A", "A-b1A", "A-c1A", "A-a2A", "A-b2A", "A-c2A", "A-a3A", "A-b3A", "A-c3A",
      "B-a1B", "B-b1B", "B-c1B", "B-a2B", "B-b2B", "B-c2B", "B-a3B", "B-b3B", "B-c3B",
      "C-a1C", "C-b1C", "C-c1C", "C-a2C", "C-b2C", "C-c2C", "C-a3C", "C-b3C", "C-c3C"
    );

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void unbalancedScopeMoreItemsCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);
    var left = mapNode(scope1Start, s -> s + "-");

    var right1 = flatMapNode(scope1Start, s -> List.of("1" + s, "2" + s, "3" + s));
    var scope2Start = new Scope<>(right1, 1);
    var right2 = flatMapNode(scope2Start, s -> List.of("a" + s, "b" + s, "c" + s));

    var combine = combineNode(left, right2, String::concat);
    var scope1End = new UnScope<>(combine, scope1Start);
    var scope2End = new UnScope<>(scope1End, scope2Start);

    var ctx = mockCtx();
    var query = getQuery(root, scope2End);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(
      "A-a1A", "A-b1A", "A-c1A", "A-a2A", "A-b2A", "A-c2A", "A-a3A", "A-b3A", "A-c3A",
      "B-a1B", "B-b1B", "B-c1B", "B-a2B", "B-b2B", "B-c2B", "B-a3B", "B-b3B", "B-c3B",
      "C-a1C", "C-b1C", "C-c1C", "C-a2C", "C-b2C", "C-c2C", "C-a3C", "C-b3C", "C-c3C"
    );

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void bothSideWithUnrelatedScopesCombineOutputsCorrectValues() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);

    var left = flatMapNode(scope1Start, s -> List.of("a" + s, "b" + s));
    var right = flatMapNode(scope1Start, s -> List.of("1" + s, "2" + s));

    var scopeLStart = new Scope<>(left, 1);
    var scopeRStart = new Scope<>(right, 2);

    var combine = combineNode(scopeLStart, scopeRStart, (l, r) -> l + "-" + r);
    var scope1End = new UnScope<>(combine, scope1Start);
    var scopeLEnd = new UnScope<>(scope1End, scopeLStart);
    var scopeREnd = new UnScope<>(scopeLEnd, scopeRStart);

    var ctx = mockCtx();
    var query = getQuery(root, scopeREnd);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = Set.of(
      "aA-1A", "aA-2A", "bA-1A", "bA-2A",
      "aB-1B", "aB-2B", "bB-1B", "bB-2B",
      "aC-1C", "aC-2C", "bC-1C", "bC-2C"
    );

    Assertions.assertEquals(expected, Set.copyOf(result));
  }

  @Test
  public void emptyBatchScopeCombineWorks1() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);
    var left = mapNode(scope1Start, s -> s + "-");

    var right1 = flatMapNode(scope1Start, s -> List.of());
    var scope2Start = new Scope<>(right1, 1);
    var right2 = countNode(scope2Start);

    var combine = combineNode(left, right2, (l, r) -> l + "-" + r);
    var scope2End = new UnScope<>(combine, scope2Start);
    var scope1End = new UnScope<>(scope2End, scope1Start);

    var ctx = mockCtx();
    var query = getQuery(root, scope1End);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of();

    Assertions.assertEquals(expected, result);
  }

  @Test
  public void emptyBatchScopeCombineWorks2() {
    // Given
    var root = new Root<List<String>>();
    var flatten = flattenRoot(root);
    var scope1Start = new Scope<>(flatten, 0);

    var left = mapNode(scope1Start, s -> s + "-");

    var right1 = flatMapNode(scope1Start, s -> List.of());
    var scope2Start = new Scope<>(right1, 1);
    var count = countNode(scope2Start);
    var scope2End = new UnScope<>(count, scope2Start);
    var count2 = countNode(scope2End);

    var combine = combineNode(left, count2, (l, r) -> l + "-" + r);
    var scope1End = new UnScope<>(combine, scope1Start);

    var ctx = mockCtx();
    var query = getQuery(root, scope1End);

    // When
    var input = List.of("A", "B", "C");
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(
      "A--0", "B--0", "C--0"
    );

    Assertions.assertEquals(expected, result);
  }

  private static JavaFileScannerContext mockCtx() {
    var ctx = Mockito.mock(JavaFileScannerContext.class);
    Mockito.when(ctx.getTree())
      .thenReturn(
        new JavaTree.CompilationUnitTreeImpl(
          null,
          List.of(),
          List.of(),
          null,
          null
        )
      );
    return ctx;
  }

  private <IN, OUT> Query<IN, List<OUT>> getQuery(Root<IN> start, IRNode<?, OUT> end) {
    return buildQuery(start, end, values -> values);
  }

  protected abstract <IN, OUT> Query<IN, List<OUT>> buildQuery(Root<IN> start, IRNode<?, ? extends OUT> end, Function<List<OUT>, List<OUT>> resultMapper);

  @NotNull
  private static <T> FlatMap<List<T>, T> flattenRoot(Root<List<T>> root) {
    return new FlatMap<>(root, new IdentifiedLambda<>("flatten", "Flatten", list -> SequencesKt.asSequence(list.iterator())));
  }

  @NotNull
  private static <T, R> FlatMap<T, R> flatMapNode(IRNode<?, T> parent, Function<T, List<R>> function) {
    return new FlatMap<>(parent, new IdentifiedLambda<>(null, "FlatMap", value -> SequencesKt.asSequence(function.apply(value).iterator())));
  }

  @NotNull
  private static <T> Aggregate<T, List<? extends T>> aggregateNode(IRNode<?, T> parent) {
    return new Aggregate<>(parent, new IdentifiedLambda<>("aggregate", "Aggregate", list -> list));
  }

  @NotNull
  private static <T, R> IRMap<T, R> mapNode(IRNode<?, T> parent, Function<T, R> function) {
    return new IRMap<>(parent, new IdentifiedLambda<>(null, "Map", function::apply));
  }

  @NotNull
  private static <LT, RT, R> Combine<LT, RT, R> combineNode(IRNode<?, LT> left, IRNode<?, RT> right, BiFunction<LT, RT, R> function) {
    return new Combine<>(left, right, new IdentifiedLambda<>(null, "Combine", function::apply));
  }

  @NotNull
  private static <T> Aggregate<T, Integer> countNode(IRNode<?, T> parent) {
    return new Aggregate<>(parent, new IdentifiedLambda<>("count", "Count", List::size));
  }
}
