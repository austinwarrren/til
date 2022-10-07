# Pattern Matching Extractors

Frequently, one writes pattern matches upon objects like this:
```scala mdoc
sealed trait Event
case class Rejected(accountId: Option[String],
                    partnerAccountId: Option[String],
                    bankAccountId: Option[String],
                    displayId: String,
                    returnCode: String,
                    txId: Long,
                    timestamp: String
                   ) extends Event   
case object NotRejected extends Event


val event = NotRejected : Event
event match {
    case r@Rejected(Some(_), Some(partnerId), _, _, _, _, _) => ()
    case _ => ()
  }
```

A more elegant solution involves the use of custom extractors, like so: 
```scala mdoc
import cats.syntax.all._

object RejectedPartner {
  def unapply(rej: Rejected): Option[String] =
    rej.partnerAccountId
}

object RejectedWithWrongAccountId {
  def unapply(rej: Rejected): Option[(String, String)] =
    (rej.bankAccountId, rej.accountId).tupled.filter { x => x._1 != x._2 }
}

object Example {
  def rejected: Rejected = ???

  rejected match {
    case RejectedPartner(partnerAccountId) => ???
    case RejectedWithWrongAccountId(bankAccountId, accountId) => ???
    case _ => ???
  }
}
```