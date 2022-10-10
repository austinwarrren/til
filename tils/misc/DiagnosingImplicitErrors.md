# Diagnosing Implicit Errors

> **Note**: This technique only viable for applications with Scala version 2.13

During the development of Scala applications, a common struggle is determining the cause of compiler errors 
such as these: 

```
No implicit instance of type Order[List[Foo]] was found for parameter order of method sort.
```

One useful technique for discovering the cause of such a compiler error is to make use of Scala's 
`-Vimplicits` compiler flag. This compiler flag will allow the compiler to print the implicit stack 
through which the compiler had iterated prior to deciding that no implicit instance was available.

Here's how to enable this flag in `build.sbt`:

```
lazy val `my-application-module` = (project in file("."))
    .settings(
        scalacOptions ++= Seq("-Vimplicits")
    )
```

**Note**: I recommend disabling this flag after diagnosing the implicit error--the flag can generate a rather large amount of noise!