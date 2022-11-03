# Detecting Binary Incompatibilities

For the most part, Scala prevents programmers from making errors which would result in 
unforeseen runtime exceptions. Where this is not true, however, is when managing dependencies.

Runtime exceptions can be introduced when upgrading dependencies. Typically this happens when
the developer updates a dependency version and unknowingly causes an incompatible dependency 
version to be introduced transitively. 

To determine whether or not 
[binary incompatibilities](https://docs.scala-lang.org/overviews/core/binary-compatibility-for-library-authors.html) 
may have been introduced, run the `sbt evicted` command. The command output might look 
something like this:

```
[warn] 	* io.netty:netty-codec-http:4.1.59.Final is selected over {4.1.43.Final, 4.1.43.Final}
[warn] 	    +- software.amazon.awssdk:netty-nio-client:2.15.81    (depends on 4.1.59.Final)
[warn] 	    +- io.netty:netty-handler-proxy:4.1.59.Final          (depends on 4.1.59.Final)
[warn] 	    +- io.netty:netty-codec-http2:4.1.59.Final            (depends on 4.1.59.Final)
[warn] 	    +- com.typesafe.netty:netty-reactive-streams-http:2.0.4 (depends on 4.1.43.Final)
[info] Here are other dependency conflicts that were resolved:
[info] 	* org.reactivestreams:reactive-streams:1.0.3 is selected over {1.0.2, 1.0.2, 1.0.2, 1.0.2}
[info] 	    +- com.typesafe.netty:netty-reactive-streams:2.0.4    (depends on 1.0.3)
[info] 	    +- software.amazon.awssdk:utils:2.15.81               (depends on 1.0.2)
[info] 	    +- software.amazon.awssdk:sdk-core:2.15.81            (depends on 1.0.2)
[info] 	    +- software.amazon.awssdk:netty-nio-client:2.15.81    (depends on 1.0.2)
[info] 	    +- software.amazon.awssdk:http-client-spi:2.15.81     (depends on 1.0.2)
```

## How to know if a warning indicates a binary incompatibility
Any message prefixed with `[warn]` is an `sbt evicted` warning. These warnings *may* be an 
indication that the indicated dependency version could introduce binary incompatibilities.
If the dependency in question makes no binary compatibility guarantees between versions, 
it is possible that the `sbt evicted` warning indicates a binary incompatibility. 