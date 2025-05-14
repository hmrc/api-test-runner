lazy val library = (project in file("."))
  .disablePlugins(JUnitXmlReportPlugin) // Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    name := "api-test-runner",
    majorVersion := 0,
    scalaVersion := "2.13.16",
    crossScalaVersions := Seq("2.13.16", "3.3.4"),
    isPublicArtefact := true,
    libraryDependencies ++= Dependencies.compile
  )
