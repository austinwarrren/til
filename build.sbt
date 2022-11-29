ThisBuild / scalaVersion := "2.13.10"

name := "today-i-learned"
version := "1.0"

lazy val `code-examples` = (project in file("."))
  .settings(
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full),
    libraryDependencies ++= {
      Seq(
        "org.typelevel" %% "cats-core" % "2.8.0",
        "eu.timepit" %% "refined" % "0.10.1",
        "dev.holt" %% "java-time-literals" % "1.1.0",
        "org.typelevel" %% "log4cats-core" % "2.5.0",
        "org.typelevel" %% "munit-cats-effect-3" % "1.0.6"
      )
    }
  )

lazy val `docs` = (project in file("tils"))
  .enablePlugins(MdocPlugin)
  .dependsOn(`code-examples`)
  .settings(
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full),
    mdocOut := file("tils")
  )
