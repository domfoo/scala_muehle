val scala3Version = "3.2.0"
val scalafxVersion = "18.0.1-R28"

import org.scoverage.coveralls.Imports.CoverallsKeys._

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala_muehle",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "org.scalafx" %% "scalafx" % scalafxVersion
  )
  .enablePlugins(CoverallsPlugin)