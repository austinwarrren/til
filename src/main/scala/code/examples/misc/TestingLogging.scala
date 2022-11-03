package code.examples.misc

import cats.Applicative
import org.typelevel.log4cats.Logger
import cats.syntax.functor._

trait TestingLogging[F[_]]{
  def foo(s: String): F[String]
}


object TestingLogging {
  def apply[F[_]: Applicative : Logger]: TestingLogging[F] = new TestingLogging[F] {
    override def foo(s: String): F[String] =
      Logger[F].info("Hello there").map(_ => "HI")
  }
}
