ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "Pekkoroadmap_gpt"
  )
libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor-typed" % "1.1.2",
  "org.apache.pekko" %% "pekko-cluster-typed" % "1.1.2",
  "org.apache.pekko" %% "pekko-serialization-jackson" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.5.6"
)
//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.6"
