ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "zio-lambda",
    Compile / mainClass := Some("com.encompasscorporation.data.Main")
  )

val deps = Seq(
  "dev.zio" %% "zio" % "2.0.2",
  "dev.zio" %% "zio-test"     % "2.0.2",
  "dev.zio" %% "zio-test-sbt" % "2.0.2",
  "dev.zio" %% "zio-s3" % "0.3.7",
)

libraryDependencies ++= deps
