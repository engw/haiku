organization := "com.github.engw"

name := "haiku"

version := "1.0"

scalaVersion := "2.11.8"

lazy val finagleVersion = "6.37.0"
lazy val finchVersion = "0.11.0-M3"
lazy val circeVersion = "0.5.0-M3"

resolvers += "Twitter's Repository" at "http://maven.twttr.com/"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % finagleVersion excludeAll ExclusionRule(organization = "org.apache.thrift"),
  "com.github.finagle" %% "finch-core" % finchVersion excludeAll ExclusionRule(organization = "org.apache.thrift"),
  "com.github.finagle" %% "finch-circe" % finchVersion excludeAll ExclusionRule(organization = "org.apache.thrift"),
  "com.twitter" %% "twitter-server" % "1.22.0" excludeAll ExclusionRule(organization = "org.apache.thrift") ,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-jawn" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-jackson" % circeVersion,
  "com.atilika.kuromoji" % "kuromoji-ipadic" % "0.9.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.apache.thrift" % "libthrift" % "0.6.1" pomOnly()
)
