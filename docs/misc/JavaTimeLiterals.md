# Java Time Literals

Typically when parsing a string equivalent of a `java.time` instance--for example, `Instant`--it is
necessary to perform the parsing at runtime. This is necessary even when the string to be parsed is a
literal. This means the developer is unable to detect improperly formatted string equivalents until 
runtime, meaning that a runtime exception may be lurking in the codebase!

Fortunately, there exists a library which allows for determining at compile time whether a string literal
is a properly-formatted string equivalent of a `java.time` instance. Enter [java-time-literals](https://github.com/bpholt/java-time-literals)!

How might one use this library? Check out this example:
```scala mdoc
import java.time.Instant
import dev.holt.javatime.literals._

val validInstant : Instant = instant"2022-10-07T21:45:49.227Z"
```

The string literal `instant` takes any literal string and determines whether or not a string is a valid `Instant`!

String literals exist for all `java.time` instances--so any literal may be validated at compile time.