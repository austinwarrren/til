# Pattern Matching Extractors

Frequently, one writes pattern matches upon objects like this:
```
event match {
    case r@Rjected(Some(_), Some(partnerId), _, _) => doStuff(r, partnerId)
    case _ =>
  }
```

A more elegant solution involves the use of custom extractors, like so: 
```
import cats.syntax.all._

case class Rejected(accountId: Option[String],
                    partnerAccountId: Option[String],
                    bankAccountId: Option[String],
                    displayId: String,
                    returnCode: String,
                    txId: Long,
                    timestamp: String,
                   )

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