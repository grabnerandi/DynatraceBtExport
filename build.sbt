name := """DynatraceBtExport"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.1.0"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)
