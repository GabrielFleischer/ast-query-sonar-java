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
package org.sonar.java.ast.visitors;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextPointer;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.fs.internal.DefaultTextPointer;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.measures.FileLinesContextFactory;
import org.sonar.java.SonarComponents;
import org.sonar.java.TestUtils;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.classpath.ClasspathForMain;
import org.sonar.java.classpath.ClasspathForTest;
import org.sonar.java.model.VisitorsBridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@EnableRuleMigrationSupport
class SonarSymbolTableVisitorTest {

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();
  private SensorContextTester context;
  private SonarComponents sonarComponents;

  @BeforeEach
  void setUp() {
    context = SensorContextTester.create(temp.getRoot());
    sonarComponents = new SonarComponents(mock(FileLinesContextFactory.class), context.fileSystem(),
      mock(ClasspathForMain.class), mock(ClasspathForTest.class), mock(CheckFactory.class), mock(ActiveRules.class));
    sonarComponents.setSensorContext(context);
  }

  @Test
  void sonar_symbol_table() {
    File source = new File("src/test/files/highlighter/SonarSymTable.java");
    InputFile inputFile = TestUtils.inputFile(source);

    JavaAstScanner.scanSingleFileForTests(inputFile, new VisitorsBridge(Collections.emptyList(), sonarComponents.getJavaClasspath(), sonarComponents));
    String componentKey = inputFile.key();
    verifyUsages(componentKey, 1, 17, reference(5,2), reference(9,10));
    // Example class declaration
    verifyUsages(componentKey, 4, 6);
    verifyUsages(componentKey, 4, 14);
    // list field
    verifyUsages(componentKey, 5, 15, reference(10, 9));

    // Example empty constructor
    verifyUsages(componentKey, 6, 2);
    // Do not reference constructor of class using this() and super() as long as SONAR-5894 is not fixed
    //verify(symboltableBuilder).newReference(any(Symbol.class), eq(offset(7, 5)));

    // Example list constructor
    verifyUsages(componentKey, 9, 2, reference(7, 4));

    // list local var
    verifyUsages(componentKey, 9, 23, reference(10, 16));
    // method
    verifyUsages(componentKey, 12, 6);
    //label
    verifyUsages(componentKey, 13, 4);
    //Enum
    verifyUsages(componentKey, 16, 7);
    verifyUsages(componentKey, 17, 5);
    // Do not reference constructor of enum as it can leads to failure in analysis as long as SONAR-5894 is not fixed
    //verify(symboltableBuilder).newReference(any(Symbol.class), eq(offset(14, 5)));

    verifyUsages(componentKey, 18, 4, reference(17, 4));
    verifyUsages(componentKey, 21, 3, reference(21, 19));
    verifyUsages(componentKey, 21, 11);
    verifyUsages(componentKey, 21, 21);
  }

  private void verifyUsages(String componentKey, int line, int offset, TextPointer... tps) {
    Collection<TextRange> textRanges = context.referencesForSymbolAt(componentKey, line, offset);
    if(tps.length == 0) {
      assertThat(textRanges).isEmpty();
    } else {
      assertThat(textRanges.stream().map(TextRange::start).toList()).isNotEmpty().containsOnly(tps);
    }
  }

  private static TextPointer reference(int line, int column) {
    return new DefaultTextPointer(line, column);
  }

}
