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
import java.util.function.Function;

public abstract class ScopeTest {

  @Test
  public void singleScopeProducesSingleResult() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope = new Scope<>(flatten, 0);
    var count = countNode(scope);
    var unscope = new UnScope<>(count, scope);
    var aggregate = aggregateNode(unscope);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of(1, 1, 1, 1));
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void sequenceScopesProducesSingleResult() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope1 = new Scope<>(flatten, 0);
    var count1 = countNode(scope1);
    var unscope1 = new UnScope<>(count1, scope1);

    var scope2 = new Scope<>(unscope1, 1);
    var count2 = countNode(scope2);
    var unscope2 = new UnScope<>(count2, scope2);

    var aggregate = aggregateNode(unscope2);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of(1, 1, 1, 1));
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void nestedScopesProducesSingleResult() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope1 = new Scope<>(flatten, 0);
    var count1 = countNode(scope1);
    var moreValues = flatMapNode(count1, value -> List.of(value, value + 0.5));
    var scope2 = new Scope<>(moreValues, 1);
    var count2 = countNode(scope2);
    var unscope2 = new UnScope<>(count2, scope2);
    var unscope1 = new UnScope<>(unscope2, scope1);

    var aggregate = aggregateNode(unscope1);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of(1, 1, 1, 1, 1, 1, 1, 1));
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void tangledScopesProducesSingleResult() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope1 = new Scope<>(flatten, 0);
    var moreValues = flatMapNode(scope1, value -> List.of(value, value + 0.5));
    var scope2 = new Scope<>(moreValues, 1);
    var unscope1 = new UnScope<>(scope2, scope1);
    var count2 = countNode(unscope1);
    var unscope2 = new UnScope<>(count2, scope2);

    var aggregate = aggregateNode(unscope2);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of(1, 1, 1, 1, 1, 1, 1, 1));
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void emptyBatchScopedWorks1() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope1 = new Scope<>(flatten, 0);
    var removeVals = flatMapNode(scope1, value -> List.of());
    var scope2 = new Scope<>(removeVals, 1);
    var count = countNode(scope2);
    var unscope2 = new UnScope<>(count, scope2);
    var unscope1 = new UnScope<>(unscope2, scope1);

    var aggregate = aggregateNode(unscope1);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of());
    Assertions.assertEquals(expected, result);
  }


  @Test
  public void emptyBatchScopedWorks2() {
    // Given
    var root = new Root<List<Integer>>();
    var flatten = flattenRoot(root);
    var scope1 = new Scope<>(flatten, 0);
    var removeVals = flatMapNode(scope1, value -> List.of());
    var scope2 = new Scope<>(removeVals, 1);
    var count1 = countNode(scope2);
    var unscope2 = new UnScope<>(count1, scope2);
    var count2 = countNode(unscope2);
    var unscope1 = new UnScope<>(count2, scope1);

    var aggregate = aggregateNode(unscope1);

    var ctx = mockCtx();
    var query = getQuery(root, aggregate);

    // When
    var input = List.of(0, 1, 2, 3);
    var result = query.execute(ctx, input);

    // Then
    var expected = List.of(List.of(0, 0, 0, 0));
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
    return new FlatMap<>(parent, new IdentifiedLambda<>("flatMap", "FlatMap", value -> SequencesKt.asSequence(function.apply(value).iterator())));
  }

  @NotNull
  private static <T> Aggregate<T, List<? extends T>> aggregateNode(IRNode<?, T> parent) {
    return new Aggregate<>(parent, new IdentifiedLambda<>("aggregate", "Aggregate", list -> list));
  }

  @NotNull
  private static <T> Aggregate<T, Integer> countNode(IRNode<?, T> parent) {
    return new Aggregate<>(parent, new IdentifiedLambda<>("count", "Count", List::size));
  }
}
