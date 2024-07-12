lazy val library = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) // Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "api-test-runner",
    majorVersion := 0,
    scalaVersion := "2.13.13",
    libraryDependencies ++= Seq(
      "com.typesafe"           % "config"                  % "1.4.3",
      "com.typesafe.play"     %% "play-ahc-ws-standalone"  % "2.2.5",
      "com.typesafe.play"     %% "play-ws-standalone-json" % "2.2.5",
      "com.vladsch.flexmark"   % "flexmark-all"            % "0.64.8",
      "org.scalatest"         %% "scalatest"               % "3.2.18",
      "org.slf4j"              % "slf4j-simple"            % "2.0.9",
      "com.github.tomakehurst" % "wiremock-jre8"           % "2.27.2" % Test
    )
  )
