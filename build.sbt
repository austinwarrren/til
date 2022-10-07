ThisBuild / scalaVersion := "2.13.8"

name := "today-i-learned"
version := "1.0"

lazy val `code-examples` = (project in file("."))
  .settings(
    libraryDependencies ++= {
      Seq(
        "org.typelevel" %% "cats-core" % "2.8.0",
        "eu.timepit" %% "refined" % "0.10.1",
        "dev.holt" %% "java-time-literals" % "1.1.0"
      )
    }
  )

lazy val `docs` = (project in file("today-i-learned-docs"))
  .enablePlugins(MdocPlugin)
  .dependsOn(`code-examples`)
