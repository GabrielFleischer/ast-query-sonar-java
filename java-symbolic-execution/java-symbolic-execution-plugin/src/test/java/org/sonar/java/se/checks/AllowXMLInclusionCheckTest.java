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
package org.sonar.java.se.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.TestUtils;
import org.sonar.java.se.SECheckVerifier;
import org.sonar.java.se.utils.SETestUtils;
import org.sonar.plugins.java.api.JavaFileScanner;

class AllowXMLInclusionCheckTest {

  private static final JavaFileScanner[] CHECKS = {
    // XxeProcessingCheck is needed to set constraints
    new XxeProcessingCheck(),
    new AllowXMLInclusionCheck()
  };

  @Test
  void document_builder_factory() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_DocumentBuilderFactory.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void sax_builder() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_SAXBuilder.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void sax_parser_factory() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_SAXParserFactory.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void sax_reader() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_SAXReader.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void schema_factory() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_SchemaFactory.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void transformer_factory() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_TransformerFactory.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

  @Test
  void xml_input_factory() {
    SECheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("symbolicexecution/checks/S6373_AllowXMLInclusionCheck_XMLInputFactory.java"))
      .withChecks(CHECKS)
      .withClassPath(SETestUtils.CLASS_PATH)
      .verifyIssues();
  }

}
