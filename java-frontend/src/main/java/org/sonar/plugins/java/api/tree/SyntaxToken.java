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
package org.sonar.plugins.java.api.tree;

import java.util.List;
import org.sonar.java.annotations.Beta;
import org.sonar.plugins.java.api.location.Range;

/**
 * Represents a token in the syntax tree.
 *
 * @since plugin 2.4
 */
@Beta
public interface SyntaxToken extends Tree {

  String text();

  List<SyntaxTrivia> trivias();

  /**
   * @return line number starting at 1
   * @deprecated for removal, since = 7.3, use "range().start().line()". A token is not anymore on a single line
   * since the text block feature has been introduced in the java language.
   */
  @Deprecated(since = "7.3", forRemoval = true)
  int line();

  /**
   * Warning: this is not the column number starting at 1 but the column offset starting at 0
   * @return column offset starting at 0
   * @deprecated for removal, since = 7.3, "column()" can be replaced by range().start().columnOffset()
   * and "column() + 1" can be replaced by range().start().column()
   */
  @Deprecated(since = "7.3", forRemoval = true)
  int column();

  Range range();

}
