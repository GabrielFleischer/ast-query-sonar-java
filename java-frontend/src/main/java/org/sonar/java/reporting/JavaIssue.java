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
package org.sonar.java.reporting;

import java.util.List;
import javax.annotation.Nullable;
import org.sonar.api.batch.fs.InputComponent;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;

public final class JavaIssue {
  private final NewIssue newIssue;

  public JavaIssue(NewIssue newIssue) {
    this.newIssue = newIssue;
  }

  public static JavaIssue create(SensorContext context, RuleKey ruleKey, @Nullable Double effortToFix) {
    NewIssue newIssue = context.newIssue()
      .forRule(ruleKey)
      .gap(effortToFix);
    return new JavaIssue(newIssue);
  }

  public JavaIssue setPrimaryLocationOnComponent(InputComponent fileOrProject, String message) {
    newIssue.at(
      newIssue.newLocation()
        .on(fileOrProject)
        .message(message));
    return this;
  }

  public JavaIssue setPrimaryLocation(InputFile file, String message, int startLine, int startLineOffset, int endLine, int endLineOffset) {
    NewIssueLocation newIssueLocation;
    if (startLineOffset == -1) {
      newIssueLocation = newIssue.newLocation()
        .on(file)
        .at(file.selectLine(startLine))
        .message(message);
    } else {
      newIssueLocation = newIssue.newLocation()
        .on(file)
        .at(file.newRange(startLine, startLineOffset, endLine, endLineOffset))
        .message(message);
    }
    newIssue.at(newIssueLocation);
    return this;
  }

  public JavaIssue addSecondaryLocation(InputFile file, int startLine, int startLineOffset, int endLine, int endLineOffset, String message) {
    newIssue.addLocation(
      newIssue.newLocation()
        .on(file)
        .at(file.newRange(startLine, startLineOffset, endLine, endLineOffset))
        .message(message));
    return this;
  }

  public JavaIssue addFlow(InputFile file, List<List<AnalyzerMessage>> flows) {
    for (List<AnalyzerMessage> flow : flows) {
      newIssue.addFlow(flow.stream()
        .map(am -> newIssue.newLocation()
          .on(file)
          .at(range(file, am.primaryLocation()))
          .message(am.getMessage()))
        .toList());
    }
    return this;
  }

  private static TextRange range(InputFile file, AnalyzerMessage.TextSpan textSpan) {
    if (textSpan.onLine()) {
      return file.selectLine(textSpan.startLine);
    }
    return file.newRange(textSpan.startLine, textSpan.startCharacter, textSpan.endLine, textSpan.endCharacter);
  }



  public void save() {
    newIssue.save();
  }

}
