name := "hl7fhir"
organization := "org.ionkin"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.10.1",
  "org.apache.commons" % "commons-csv" % "1.6",
  "com.google.guava" % "guava" % "27.0-jre"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
libraryDependencies += guice