# Testing Logging

During the development of Scala `cats` applications, one may find oneself needing to test 
that specific statements are being logged. One method for accomplishing this testing is by 
defining a `Logger` instance of type `WriterT`. Here's an example:

Necessary imports:
```scala
import cats.Applicative
import cats.effect.IO
import cats.data.{Chain, WriterT}
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.extras.{LogMessage, WriterTLogger}
```

```scala
implicit val writerTLogger: Logger[WriterT[IO, Chain[LogMessage], *]] = WriterTLogger[IO, Chain]()
// writerTLogger: Logger[[γ$0$]WriterT[IO, Chain[LogMessage], γ$0$]] = org.typelevel.log4cats.extras.WriterTLogger$$anon$1@6a3d0175

def log[F[_]: Applicative : Logger]: F[Unit] = Logger[F].info("Hello there")

log[WriterT[IO, Chain[LogMessage], *]].run
// res0: IO[(Chain[LogMessage], Unit)] = Map(
//   ioe = Pure(value = ()),
//   f = cats.data.WriterTFunctions$$Lambda$7157/0x00000008020fe040@309b788e,
//   event = cats.effect.tracing.TracingEvent$StackTrace
// )
```