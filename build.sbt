import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

assemblySettings

name := "verify"

organization := "com.ldaniels528"

version := "1.0.7"

packageArchetype.java_application

maintainer in Linux := "Lawrence Daniels <ldaniels@verisign.com>"

packageSummary in Linux := "Swiss-Army-Knife for viewing/managing topics for Kafka"

packageDescription := "Swiss-Army-Knife for viewing/managing topics for Kafka"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.6", "-unchecked",
  "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

javacOptions ++= Seq("-Xlint:deprecation", "-Xlint:unchecked", "-source", "1.6", "-target", "1.6", "-g:vars")

mainClass in assembly := Some("com.ldaniels528.verify.VerifyShell")

jarName in assembly := "verify.jar"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("stax", "stax-api", xs @ _*) => MergeStrategy.first
    case PathList("log4j-over-slf4j", xs @ _*) => MergeStrategy.discard
    case PathList("log4j-over-slf4j", xs @ _*) => MergeStrategy.discard
    case PathList("META-INF", "MANIFEST.MF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}

// Local Dependencies
libraryDependencies ++= Seq(
  "com.ldaniels528" %% "tabular" % "0.1.0"
)

// General Dependencies
libraryDependencies ++= Seq(
  "com.twitter" %% "bijection-core" % "0.6.2",
  "com.twitter" %% "bijection-avro" % "0.6.2",
  "com.typesafe.akka" % "akka-actor_2.10" % "2.3.4",
  "log4j" % "log4j" % "1.2.17",
  "net.liftweb" %% "lift-json" % "2.5.1",
  "org.apache.avro" % "avro" % "1.7.6",
  "org.apache.kafka" % "kafka_2.10" % "0.8.1.1",
  "org.apache.storm" % "storm-core" % "0.9.2-incubating" % "provided",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.slf4j" % "slf4j-log4j12" % "1.7.7"
)

// Testing Dependencies
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test"
)

// define the resolvers
resolvers ++= Seq(
  "Clojars" at "http://clojars.org/repo/",
  "Clojure-Releases" at "http://build.clojure.org/releases/",
  "Java Net" at "http://download.java.net/maven/2/",
  "Maven Central Server" at "http://repo1.maven.org/maven2",
  "Sonatype Repository" at "http://oss.sonatype.org/content/repositories/releases/",
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
)

resolvers += Resolver.file("Local repo", file(System.getProperty("user.home") + "/.ivy2/local"))(Resolver.ivyStylePatterns)