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
package org.sonar.java.checks.spring;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.java.checks.verifier.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.java.checks.spring.SpringAntMatcherOrderCheck.antMatcherToRegEx;
import static org.sonar.java.checks.spring.SpringAntMatcherOrderCheck.escapeRegExpChars;
import static org.sonar.java.checks.spring.SpringAntMatcherOrderCheck.matches;

class SpringAntMatcherOrderCheckTest {

  @Test
  void test() {
    CheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("checks/spring/SpringAntMatcherOrderCheckSample.java"))
      .withCheck(new SpringAntMatcherOrderCheck())
      .verifyIssues();
    CheckVerifier.newVerifier()
      .onFile(TestUtils.mainCodeSourcesPath("checks/spring/SpringAntMatcherOrderCheckSample.java"))
      .withCheck(new SpringAntMatcherOrderCheck())
      .withoutSemantic()
      .verifyNoIssues();
  }

  @Test
  void escape_regexp_characters() {
    assertThat(escapeRegExpChars("")).isEmpty();
    assertThat(escapeRegExpChars("abc")).isEqualTo("abc");
    assertThat(escapeRegExpChars("a(b)c")).isEqualTo("a\\(b\\)c");
    assertThat(escapeRegExpChars("a{b}c")).isEqualTo("a\\{b\\}c");
    assertThat(escapeRegExpChars("a[b]c")).isEqualTo("a\\[b\\]c");
    assertThat(escapeRegExpChars("a.b+c")).isEqualTo("a\\.b\\+c");
    assertThat(escapeRegExpChars("a|b\\c")).isEqualTo("a\\|b\\\\c");
    assertThat(escapeRegExpChars("a^b$c")).isEqualTo("a\\^b\\$c");
  }

  @Test
  void does_not_escape_ant_matcher_special_characters() {
    assertThat(escapeRegExpChars("a?c")).isEqualTo("a?c");
    assertThat(escapeRegExpChars("a*c")).isEqualTo("a*c");
    assertThat(escapeRegExpChars("a**c")).isEqualTo("a**c");
  }

  @Test
  void ant_matcher_to_regex() {
    assertThat(antMatcherToRegEx("abc")).isEqualTo("abc");
    assertThat(antMatcherToRegEx("a?c")).isEqualTo("a[^/]c");
    assertThat(antMatcherToRegEx("a*c")).isEqualTo("a[^/]*c");
    assertThat(antMatcherToRegEx("a**c")).isEqualTo("a.*c");
    assertThat(antMatcherToRegEx("a**b*c")).isEqualTo("a.*b[^/]*c");
  }

  @Test
  void should_match() {
    assertThat(matches("", "")).isTrue();
    assertThat(matches("/abc", "/abc")).isTrue();
    assertThat(matches("/abc/**", "/abc/def")).isTrue();
    assertThat(matches("/abc/**", "/abc/**/def")).isTrue();
    assertThat(matches("/abc/*ef", "/abc/ddeef")).isTrue();
    assertThat(matches("/abc/?ef", "/abc/def")).isTrue();
  }

  @Test
  void should_not_match() {
    assertThat(matches("", "/def")).isFalse();
    assertThat(matches("/abc", "/def")).isFalse();
    assertThat(matches("/abc/**", "/def/abc")).isFalse();
    assertThat(matches("/abc/*ef", "/abc/deg")).isFalse();
    assertThat(matches("/abc/?ef", "/abc/ddef")).isFalse();
    // does not match because not supported
    assertThat(matches("/abc/**/def", "/abc/**/x/**/def")).isFalse();
    assertThat(matches("/{name:[a-z]}", "/abc")).isFalse();
  }

}
