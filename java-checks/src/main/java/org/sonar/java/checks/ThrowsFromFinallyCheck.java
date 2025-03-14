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
package org.sonar.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.ThrowStatementTree;
import org.sonar.plugins.java.api.tree.TryStatementTree;

@Rule(key = "S1163")
public class ThrowsFromFinallyCheck extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  private int finallyLevel = 0;
  private boolean isInMethodWithinFinally;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitTryStatement(TryStatementTree tree) {
    scan(tree.resourceList());
    scan(tree.block());
    scan(tree.catches());
    finallyLevel++;
    scan(tree.finallyBlock());
    finallyLevel--;
  }

  @Override
  public void visitThrowStatement(ThrowStatementTree tree) {
    if(isInFinally() && !isInMethodWithinFinally){
      context.reportIssue(this, tree, "Refactor this code to not throw exceptions in finally blocks.");
    }
    super.visitThrowStatement(tree);
  }

  @Override
  public void visitMethod(MethodTree tree) {
    isInMethodWithinFinally = isInFinally();
    super.visitMethod(tree);
    isInMethodWithinFinally = false;
  }

  private boolean isInFinally(){
    return finallyLevel>0;
  }

}
