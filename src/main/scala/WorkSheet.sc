import scala.collection.immutable.List

val num = if (true)
  100
else
  0

// 練習問題
for (i <- 1 to 10) yield {
  new scala.util.Random(new java.security.SecureRandom()).alphanumeric.take(5).toList match {
    case List(a, b, c, d, _) => List(a, b, c, d, a).mkString
  }
}




