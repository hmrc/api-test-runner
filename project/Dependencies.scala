import sbt.*

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe"         % "config"                  % "1.4.3",
    "org.playframework"   %% "play-ahc-ws-standalone"  % "3.0.5",
    "org.playframework"   %% "play-ws-standalone-json" % "3.0.5",
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8",
    "org.scalatest"       %% "scalatest"               % "3.2.19",
    "org.slf4j"            % "slf4j-simple"            % "2.0.13"
  )
}
