# Refinement Type Companion Objects

When writing code that utilizes refinement types, one often is subjected to beauties such as this:
```
val uuid = "89b23cec-a713-4b98-8d16-e7b6422dc0f0"
refineV[Uuid](uuid).flatMap{ refinedUuid =>
  refineV[NonEmpty](uuid)
}
```

One way to improve this syntax is to utilize `refined`'s `RefinedTypeOps` class, which provides a number of convenience methods. Here is an example of `RefinedTypeOps` in use:

```
type AccountId = String Refined Uuid
object AccountId extends RefinedTypeOps[AccountId, String]
```

Once the companion object is declared, one may invoke any of the provided convenience methods by referencing the companion object like this:

```
AccountId.from("") : Either[String, Refined[String, Uuid]]
```