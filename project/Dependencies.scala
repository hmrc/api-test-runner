import sbt.*

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe"         % "config"                  % "1.4.3",
    "org.playframework"   %% "play-ahc-ws-standalone"  % "3.0.7",
    "org.playframework"   %% "play-ws-standalone-json" % "3.0.7",
    "com.vladsch.flexmark" % "flexmark-all"            % "0.64.8",
    "org.scalatest"       %% "scalatest"               % "3.2.19",
    "ch.qos.logback"       % "logback-classic"         % "1.5.18"
  )
}
