/*
 * SonarQube Java
 * Copyright (C) 2025-2025 SonarSource SA
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
package org.sonar.java.it;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.sonar.orchestrator.build.*;
import com.sonar.orchestrator.container.Edition;
import com.sonar.orchestrator.container.Server;
import com.sonar.orchestrator.junit4.OrchestratorRule;
import com.sonar.orchestrator.junit4.OrchestratorRuleBuilder;
import com.sonar.orchestrator.locator.FileLocation;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.test.classpath.TestClasspathUtils;
import org.sonarqube.ws.Qualityprofiles.SearchWsResponse.QualityProfile;
import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;
import org.sonarqube.ws.client.qualityprofiles.ActivateRuleRequest;
import org.sonarqube.ws.client.qualityprofiles.SearchRequest;
import org.sonarqube.ws.client.rules.CreateRequest;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class JavaRulingTest {

  private static final int LOGS_NUMBER_LINES = 200;
  private static final Logger LOG = LoggerFactory.getLogger(JavaRulingTest.class);

  // by default all rules are enabled, if you want to enable just a subset of rules you can specify the list of
  // rule keys from the command line using "rules" property, i.e. mvn test -Drules=S100,S101
  private static final ImmutableSet<String> SUBSET_OF_ENABLED_RULES =
    ImmutableSet.of(
      "S112",
      "S135",
      "S2077",
      "S3030",
      "S4635",
      "S2221",
      "S1696",
      "S1147"
    );

  @ClassRule
  public static final OrchestratorRule ORCHESTRATOR = createOrchestrator();

  private static OrchestratorRule createOrchestrator() {
    String pluginPath = System.getProperty("PERF_PLUGIN_PATH");

    File plugin;
    if (pluginPath == null) {
      plugin = TestClasspathUtils.findModuleJarPath("../../sonar-java-plugin").toFile();
    } else {
      System.out.println("Using plugin from " + pluginPath);
      plugin = new File(pluginPath);
    }

    OrchestratorRuleBuilder orchestratorBuilder = OrchestratorRule.builderEnv()
      .useDefaultAdminCredentialsForBuilds(true)
      .setSonarVersion(System.getProperty("sonar.runtimeVersion", "LATEST_RELEASE"))
      .addPlugin(FileLocation.of(plugin))
      .addPlugin(FileLocation.of(TestClasspathUtils.findModuleJarPath("../../java-symbolic-execution/java-symbolic-execution-plugin").toFile()))
      .setEdition(Edition.COMMUNITY);

    return orchestratorBuilder.build();
  }

  @BeforeClass
  public static void prepare_quality_profiles() throws Exception {
    ImmutableMap<String, ImmutableMap<String, String>> rulesParameters = ImmutableMap.<String, ImmutableMap<String, String>>builder()
      .put(
        "S1120",
        ImmutableMap.of("indentationLevel", "4"))
      .put(
        "S1451",
        ImmutableMap.of(
          "headerFormat",
          """
            
            /*
             * Copyright (c) 1998, 2006, Oracle and/or its affiliates. All rights reserved.
             * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms."""))
      .put("S5961", ImmutableMap.of("MaximumAssertionNumber", "50"))
      .put("S6539", ImmutableMap.of("couplingThreshold", "20"))
      .build();
    ImmutableSet<String> disabledRules = ImmutableSet.of(
      "S1874",
      "CycleBetweenPackages",
      // disable because it generates too many issues, performance reasons
      "S1106"
    );
    Set<String> activatedRuleKeys = new HashSet<>();
    ProfileGenerator.generate(ORCHESTRATOR, rulesParameters, disabledRules, SUBSET_OF_ENABLED_RULES, activatedRuleKeys);
    instantiateTemplateRule("S2253", "stringToCharArray", "className=\"java.lang.String\";methodName=\"toCharArray\"", activatedRuleKeys);
    instantiateTemplateRule("S4011", "longDate", "className=\"java.util.Date\";argumentTypes=\"long\"", activatedRuleKeys);
    instantiateTemplateRule("S124", "commentRegexTest", "regularExpression=\"(?i).*TODO\\(user\\).*\";message=\"bad user\"", activatedRuleKeys);
    instantiateTemplateRule("S3546", "InstancesOfNewControllerClosedWithDone",
      "factoryMethod=\"org.sonar.api.server.ws.WebService$Context#createController\";closingMethod=\"org.sonar.api.server.ws.WebService$NewController#done\"", activatedRuleKeys);
    instantiateTemplateRule("S3546", "JsonWriterNotClosed",
      "factoryMethod=\"org.sonar.api.server.ws.Response#newJsonWriter\";closingMethod=\"org.sonar.api.utils.text.JsonWriter#close\"", activatedRuleKeys);

    SUBSET_OF_ENABLED_RULES.stream()
      .filter(ruleKey -> !activatedRuleKeys.contains(ruleKey))
      .forEach(ruleKey -> Fail.fail("Specified rule does not exist: " + ruleKey));
  }

  @AfterClass
  public static void afterAllAnalysis() throws IOException {
    PerformanceStatistics.generate(Paths.get("target","performance"));
  }

  @Test
  public void guava() throws Exception {
    String projectName = "guava-new";
    MavenBuild build = test_project("com.google:guava", projectName);
    build.addArguments("-pl=-com.google.guava:guava-gwt");
    build.setProperty("maven.javadoc.skip", "true");
    executeBuildWithCommonProperties(build, projectName);
  }


  @Test
  public void jetty() throws Exception {
    String projectName = "eclipse-jetty";
    MavenBuild build = test_project("org.eclipse:jetty", projectName);
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void apache_commons_beanutils() throws Exception {
    String projectName = "commons-beanutils";
    MavenBuild build = test_project("commons-beanutils:commons-beanutils", projectName);
      // by default it can not be built with jdk 17 without changing some plugin versions
    build.setProperty("maven-bundle-plugin.version", "5.1.4");
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void sonarqube_server() throws Exception {
    // sonarqube-6.5/server/sonar-server (v.6.5)
    String projectName = "sonar-server";
    MavenBuild build = test_project("org.sonarsource.sonarqube:sonar-server", "sonarqube-6.5/server", projectName);
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void jboss_ejb3_tutorial() throws Exception {
    // https://github.com/jbossejb3/jboss-ejb3-tutorial (18/01/2015)
    String projectName = "jboss-ejb3-tutorial";
    prepareProject(projectName, projectName);
    SonarScanner build = SonarScanner.create(FileLocation.of("../sources/jboss-ejb3-tutorial").getFile())
      .setProjectKey(projectName)
      .setProjectName(projectName)
      .setProjectVersion("0.1.0-SNAPSHOT")
      .setSourceEncoding("UTF-8")
      .setSourceDirs(".")
      .setDebugLogs(true)
      // Dummy sonar.java.binaries to pass validation
      .setProperty("sonar.java.binaries", "asynch")
      .setProperty("sonar.java.source", "1.5");
    executeDebugBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void rxJava() throws IOException {
    String projectName = "rxjava";
    GradleBuild build = test_gradle("io.reactivex:rxjava", projectName);
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void apache_dubbo() throws IOException {
    String projectName = "dubbo";
    MavenBuild build = test_project("org.apache:dubbo", projectName);
    build.addArguments("-pl", "org.apache.dubbo:dubbo-dependencies-all", "-am");
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void netty() throws IOException {
    String projectName = "netty";
    MavenBuild build = test_project("io.netty:netty", projectName);
    // Avoid running the testsuite which cannot build
    build.addArguments("-pl", "io.netty:netty-all", "-am");

    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void ribbon() throws IOException {
    String projectName = "ribbon";
    GradleBuild build = test_gradle("com.netflix:ribbon", projectName);
    executeBuildWithCommonProperties(build, projectName);
  }

  @Test
  public void zuul() throws IOException {
    String projectName = "zuul";
    GradleBuild build = test_gradle("com.netflix:zuul", projectName);
    executeBuildWithCommonProperties(build, projectName);
  }

  private static MavenBuild test_project(String projectKey, String projectName) throws IOException {
    return test_project(projectKey, null, projectName);
  }

  private static MavenBuild test_project(String projectKey, @Nullable String path, String projectName) throws IOException {
    String pomLocation = "../sources/" + (path != null ? path + "/" : "") + projectName + "/pom.xml";
    File pomFile = FileLocation.of(pomLocation).getFile().getCanonicalFile();
    prepareProject(projectKey, projectName);
    MavenBuild mavenBuild = MavenBuild.create()
      .setPom(pomFile)
      // .setGoals("package", "sonar:sonar")
      .setGoals("sonar:sonar")
      .addArgument("-DskipTests");
    setProperties(mavenBuild);
    return mavenBuild.setProperty("sonar.projectKey", projectKey);
  }

  private static GradleBuild test_gradle(String projectKey, String projectName) throws IOException {
    return test_gradle(projectKey, null, projectName);
  }

  private static GradleBuild test_gradle(String projectKey, @Nullable String path, String projectName) throws IOException {
    String projectLocation = "../sources/" + (path != null ? path + "/" : "") + projectName + "/";
    File projectDir = FileLocation.of(projectLocation).getFile().getCanonicalFile();
    prepareProject(projectKey, projectName);
    GradleBuild build = GradleBuild.create(projectDir)
      .setEnvironmentVariable("GRADLE_OPTS", "-Xmx1024m")
      .addArguments("--stacktrace", "--info", "--console=plain", "-x", "test");
    setProperties(build);

    return build
      // .setTasks("assemble")
      .addSonarTask()
      .addArguments("-x", "test")
      .setProperty("sonar.projectKey", projectKey);
  }

  private static void prepareProject(String projectKey, String projectName) {
    ORCHESTRATOR.getServer().provisionProject(projectKey, projectName);
    ORCHESTRATOR.getServer().associateProjectToQualityProfile(projectKey, "java", "rules");
  }

  private static void executeDebugBuildWithCommonProperties(Build<?> build, String projectName) throws IOException {
    executeBuildWithCommonProperties(build, projectName, true);
  }

  private static void executeBuildWithCommonProperties(Build<?> build, String projectName) throws IOException {
    executeBuildWithCommonProperties(build, projectName, false);
  }

  private static void setProperties(Build<?> build) {
    build.setProperty("sonar.cpd.exclusions", "**/*")
      .setProperty("sonar.java.performance.measure", "true")
      .setProperty("sonar.java.performance.measure.path", Path.of(".", "target","performance", "sonar.java.performance.measure.json").toAbsolutePath().toString())
      .setProperty("sonar.java.skipUnchanged", "false")
      .setProperty("sonar.import_unknown_files", "true")
      .setProperty("sonar.java.fileByFile", "true")
      .setProperty("sonar.skipPackageDesign", "true")
      .setProperty("sonar.internal.analysis.failFast", "true");
  }

  private static void executeBuildWithCommonProperties(Build<?> build, String projectName, boolean buildQuietly) throws IOException {
    BuildResult buildResult;
    if (buildQuietly) {
      // if build fail, ruling job is not violently interrupted, allowing time to dump SQ logs
      buildResult = ORCHESTRATOR.executeBuildQuietly(build);
    } else {
      buildResult = ORCHESTRATOR.executeBuild(build);
    }

    if (!buildResult.isSuccess()) {
      dumpServerLogs();
      Fail.fail("Build failure for project: " + projectName);
    }
  }

  private static void dumpServerLogs() throws IOException {
    Server server = ORCHESTRATOR.getServer();
    LOG.error("::::::::::::::::::::::::::::::::::: DUMPING SERVER LOGS :::::::::::::::::::::::::::::::::::");
    dumpServerLogLastLines(server.getAppLogs());
    dumpServerLogLastLines(server.getCeLogs());
    dumpServerLogLastLines(server.getEsLogs());
    dumpServerLogLastLines(server.getWebLogs());
  }

  private static void dumpServerLogLastLines(File logFile) throws IOException {
    if (!logFile.exists()) {
      return;
    }
    List<String> logs = Files.readAllLines(logFile.toPath());
    int nbLines = logs.size();
    if (nbLines > LOGS_NUMBER_LINES) {
      logs = logs.subList(nbLines - LOGS_NUMBER_LINES, nbLines);
    }
    LOG.error("=================================== START " + logFile.getName() + " ===================================");
    LOG.error(System.lineSeparator() + logs.stream().collect(Collectors.joining(System.lineSeparator())));
    LOG.error("===================================== END " + logFile.getName() + " ===================================");
  }

  private static void instantiateTemplateRule(String ruleTemplateKey, String instantiationKey, String params, Set<String> activatedRuleKeys) {
    if (!SUBSET_OF_ENABLED_RULES.isEmpty() && !SUBSET_OF_ENABLED_RULES.contains(instantiationKey)) {
      return;
    }
    activatedRuleKeys.add(instantiationKey);
    newAdminWsClient(ORCHESTRATOR)
      .rules()
      .create(new CreateRequest()
        .setName(instantiationKey)
        .setMarkdownDescription(instantiationKey)
        .setSeverity("INFO")
        .setStatus("READY")
        .setTemplateKey("java:" + ruleTemplateKey)
        .setCustomKey(instantiationKey)
        .setPreventReactivation("true")
        .setParams(Arrays.asList(("name=\"" + instantiationKey + "\";key=\"" + instantiationKey + "\";" +
          "markdown_description=\"" + instantiationKey + "\";" + params).split(";", 0))));

    String profileKey = newAdminWsClient(ORCHESTRATOR).qualityprofiles()
      .search(new SearchRequest())
      .getProfilesList().stream()
      .filter(qualityProfile -> "rules".equals(qualityProfile.getName()))
      .map(QualityProfile::getKey)
      .findFirst()
      .orElse(null);

    if (StringUtils.isEmpty(profileKey)) {
      LOG.error("Could not retrieve profile key : Template rule " + ruleTemplateKey + " has not been activated");
    } else {
      String ruleKey = "java:" + instantiationKey;
      newAdminWsClient(ORCHESTRATOR).qualityprofiles()
        .activateRule(new ActivateRuleRequest()
          .setKey(profileKey)
          .setRule(ruleKey)
          .setSeverity("INFO")
          .setParams(Collections.emptyList()));
      LOG.info(String.format("Successfully activated template rule '%s'", ruleKey));
    }
  }

  static WsClient newAdminWsClient(OrchestratorRule orchestrator) {
    return WsClientFactories.getDefault().newClient(HttpConnector.newBuilder()
      .credentials(Server.ADMIN_LOGIN, Server.ADMIN_PASSWORD)
      .url(orchestrator.getServer().getUrl())
      .build());
  }
}
