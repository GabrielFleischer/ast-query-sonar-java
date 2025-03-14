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
package org.sonar.java.model.declaration;

import java.util.ArrayList;
import org.sonar.java.ast.parser.ListTreeImpl;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.ModifierKeywordTree;
import org.sonar.plugins.java.api.tree.ModifierTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.TreeVisitor;

import java.util.Collections;
import java.util.List;

public class ModifiersTreeImpl extends ListTreeImpl<ModifierTree> implements ModifiersTree {

  private final List<ModifierKeywordTree> modifiers;
  private final List<AnnotationTree> annotations;

  public ModifiersTreeImpl(List<ModifierTree> javaTrees) {
    super(javaTrees);
    List<ModifierKeywordTree> modifiersList = new ArrayList<>();
    List<AnnotationTree> annotationsList = new ArrayList<>();
    for (ModifierTree modifierTree : this) {
      if (modifierTree.is(Kind.ANNOTATION)) {
        annotationsList.add((AnnotationTree) modifierTree);
      } else {
        modifiersList.add((ModifierKeywordTree) modifierTree);
      }
    }
    this.annotations = Collections.unmodifiableList(annotationsList);
    this.modifiers = Collections.unmodifiableList(modifiersList);
  }

  public static ModifiersTreeImpl emptyModifiers() {
    return new ModifiersTreeImpl(Collections.emptyList());
  }

  @Override
  public Kind kind() {
    return Kind.MODIFIERS;
  }

  @Override
  public List<ModifierKeywordTree> modifiers() {
    return modifiers;
  }

  @Override
  public List<AnnotationTree> annotations() {
    return annotations;
  }

  @Override
  public void accept(TreeVisitor visitor) {
    visitor.visitModifier(this);
  }

}
