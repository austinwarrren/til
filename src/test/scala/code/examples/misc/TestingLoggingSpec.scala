package code.examples.misc

import cats.data.{Chain, WriterT}
import cats.effect.IO
import munit.CatsEffectSuite
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.extras.{LogMessage, WriterTLogger}

class TestingLoggingSpec extends CatsEffectSuite {

  test("foo should log expected message"){
    implicit val writerTLogger: Logger[WriterT[IO, Chain[LogMessage], *]] = WriterTLogger[IO, Chain]()
    TestingLogging[IO].foo("bar")
  }
}
