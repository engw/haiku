lazy val finagleVersion = "6.35.0"
lazy val finchVersion = "0.11.0-M3"
lazy val circeVersion = "0.5.0-M3"

lazy val root = (project in file(""))
  .enablePlugins(JavaAppPackaging, AshScriptPlugin, DockerPlugin)
  .settings(
    organization := "com.github.engw",
    name := "haiku",
    version := "1.0",
    scalaVersion := "2.11.8",
    resolvers ++= Seq(
      "Twitter's Repository" at "https://maven.twttr.com/"),
    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % finagleVersion,
      "com.github.finagle" %% "finch-core" % finchVersion,
      "com.github.finagle" %% "finch-circe" % finchVersion,
      "com.twitter" %% "twitter-server" % "1.19.0",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-jawn" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-jackson" % circeVersion,
      "com.atilika.kuromoji" % "kuromoji-ipadic" % "0.9.0",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    ),
    mainClass in (Compile, run) := Some("engw.haiku.Main"),
    dockerBaseImage := "java:8-jdk-alpine")

