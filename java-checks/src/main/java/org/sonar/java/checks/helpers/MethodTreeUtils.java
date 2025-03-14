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
package org.sonar.java.checks.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import org.sonar.java.annotations.VisibleForTesting;
import org.sonar.java.model.ExpressionUtils;
import org.sonar.java.model.ModifiersUtils;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.ArrayTypeTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.LambdaExpressionTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.ParenthesizedTree;
import org.sonar.plugins.java.api.tree.PrimitiveTypeTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.VariableTree;

public final class MethodTreeUtils {

  private MethodTreeUtils() {
  }

  public static boolean isMainMethod(MethodTree m) {
    return isPublic(m) && isStatic(m) && isNamed(m, "main") && returnsPrimitive(m, "void") && hasStringArrayParameter(m);
  }

  private static boolean hasStringArrayParameter(MethodTree m) {
    return m.parameters().size() == 1 && isParameterStringArray(m);
  }

  private static boolean isParameterStringArray(MethodTree m) {
    VariableTree variableTree = m.parameters().get(0);
    boolean result = false;
    if (variableTree.type().is(Tree.Kind.ARRAY_TYPE)) {
      ArrayTypeTree arrayTypeTree = (ArrayTypeTree) variableTree.type();
      result = arrayTypeTree.type().symbolType().isClass() && "String".equals(arrayTypeTree.type().symbolType().name());
    }
    return result;
  }

  /**
   * @return null when:
   *  - argumentCandidate is null
   *  - the parent is not a method invocation (ignoring parent parentheses)
   *  - argumentCandidate is not at the expected argument position
   *  Otherwise, returns the parent method invocation.
   */
  @Nullable
  public static MethodInvocationTree parentMethodInvocationOfArgumentAtPos(@Nullable ExpressionTree argumentCandidate, int expectedArgumentPosition) {
    if (argumentCandidate == null) {
      return null;
    }
    while (argumentCandidate.parent() instanceof ParenthesizedTree parenthesizedTree) {
      argumentCandidate = parenthesizedTree;
    }
    if (argumentCandidate.parent() instanceof Arguments arguments &&
      expectedArgumentPosition < arguments.size() &&
      arguments.get(expectedArgumentPosition) == argumentCandidate &&
      arguments.parent() instanceof MethodInvocationTree methodInvocationTree) {
      return methodInvocationTree;
    }
    return null;
  }

  @Nullable
  public static VariableTree lamdaArgumentAt(@Nullable LambdaExpressionTree lambdaExpressionTree, int argumentPosition) {
    if (lambdaExpressionTree == null) {
      return null;
    }
    List<VariableTree> parameters = lambdaExpressionTree.parameters();
    if (parameters.size() > argumentPosition) {
      return parameters.get(argumentPosition);
    }
    return null;
  }

  public static boolean isEqualsMethod(MethodTree m) {
    boolean hasEqualsSignature = isNamed(m, "equals") && returnsPrimitive(m, "boolean") && hasObjectParameter(m);
    return isPublic(m) && !isStatic(m) && hasEqualsSignature;
  }

  private static boolean hasObjectParameter(MethodTree m) {
    return m.parameters().size() == 1 && m.parameters().get(0).type().symbolType().is("java.lang.Object");
  }

  public static boolean isHashCodeMethod(MethodTree m) {
    boolean hasHashCodeSignature = isNamed(m, "hashCode") && m.parameters().isEmpty() && returnsInt(m);
    return isPublic(m) && !isStatic(m) && hasHashCodeSignature;
  }

  public static boolean isSetterMethod(MethodTree m) {
    boolean publicNonStaticVoid = isPublic(m) && !isStatic(m) && returnsPrimitive(m, "void");
    return publicNonStaticVoid && isNamePrefix(m, "set") && m.parameters().size() == 1;
  }

  private static boolean isNamed(MethodTree m, String name) {
    return name.equals(m.simpleName().name());
  }

  private static boolean isNamePrefix(MethodTree m, String prefix) {
    return m.simpleName().name().startsWith(prefix);
  }

  private static boolean isStatic(MethodTree m) {
    return ModifiersUtils.hasModifier(m.modifiers(), Modifier.STATIC);
  }

  private static boolean isPublic(MethodTree m) {
    return ModifiersUtils.hasModifier(m.modifiers(), Modifier.PUBLIC);
  }

  private static boolean returnsInt(MethodTree m) {
    return returnsPrimitive(m, "int");
  }

  private static boolean returnsPrimitive(MethodTree m, String primitive) {
    TypeTree returnType = m.returnType();
    if (returnType == null) {
      return false;
    }
    return returnType.is(Tree.Kind.PRIMITIVE_TYPE)
      && primitive.equals(((PrimitiveTypeTree) returnType).keyword().text());
  }

  public static Optional<MethodInvocationTree> consecutiveMethodInvocation(Tree tree) {
    Tree memberSelectExpression = tree;
    Tree memberSelectExpressionParent = memberSelectExpression.parent();
    while (hasKind(memberSelectExpressionParent, Tree.Kind.PARENTHESIZED_EXPRESSION)) {
      memberSelectExpression = memberSelectExpressionParent;
      memberSelectExpressionParent = memberSelectExpressionParent.parent();
    }
    if (hasKind(memberSelectExpressionParent, Tree.Kind.MEMBER_SELECT)) {
      if (((MemberSelectExpressionTree) memberSelectExpressionParent).identifier() == memberSelectExpression) {
        // In the case: A.B.M(), B is the identifier of another member select, we want to go one level above.
        memberSelectExpressionParent = memberSelectExpressionParent.parent();
      }

      Tree memberSelectParent = memberSelectExpressionParent.parent();
      if (hasKind(memberSelectParent, Tree.Kind.METHOD_INVOCATION)) {
        return Optional.of((MethodInvocationTree) memberSelectParent);
      }
    }
    return Optional.empty();
  }

  public static Optional<MethodInvocationTree> subsequentMethodInvocation(Tree tree, MethodMatchers methodMatchers) {
    return consecutiveMethodInvocation(tree)
      .map(consecutiveMethod ->
        methodMatchers.matches(consecutiveMethod) ?
          consecutiveMethod : subsequentMethodInvocation(consecutiveMethod, methodMatchers).orElse(null));
  }

  @VisibleForTesting
  static boolean hasKind(@Nullable Tree tree, Tree.Kind kind) {
    return tree != null && tree.kind() == kind;
  }

  public static class MethodInvocationCollector extends BaseTreeVisitor {
    protected final List<Tree> invocationTree = new ArrayList<>();
    private final Predicate<Symbol.MethodSymbol> collectPredicate;

    public MethodInvocationCollector(Predicate<Symbol.MethodSymbol> collectPredicate) {
      this.collectPredicate = collectPredicate;
    }

    public List<Tree> getInvocationTree() {
      return invocationTree;
    }

    @Override
    public void visitMethodInvocation(MethodInvocationTree mit) {
      if (collectPredicate.test(mit.methodSymbol())) {
        invocationTree.add(ExpressionUtils.methodName(mit));
      }
      super.visitMethodInvocation(mit);
    }

    @Override
    public void visitNewClass(NewClassTree tree) {
      if (collectPredicate.test(tree.methodSymbol())) {
        invocationTree.add(tree.identifier());
      }
      super.visitNewClass(tree);
    }

    @Override
    public void visitClass(ClassTree tree) {
      // Skip class
    }

    @Override
    public void visitLambdaExpression(LambdaExpressionTree lambdaExpressionTree) {
      // Skip lambdas
    }
  }

}
