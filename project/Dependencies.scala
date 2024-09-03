import sbt.*

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe"         % "config"                  % "1.4.3",
    "com.typesafe.play"   %% "play-ahc-ws-standalone"  % "2.2.9",
    "com.typesafe.play"   %% "play-ws-standalone-json" % "2.2.9",
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8",
    "org.scalatest"       %% "scalatest"               % "3.2.19",
    "org.slf4j"            % "slf4j-simple"            % "2.0.13"
  )
}
