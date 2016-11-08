/**
 * https://dwango.github.io/scala_text/type-parameter.html
 * 演習問題
 */

trait Stack[+A] {
  // 引数eを追加したStack[E]を返す
  // [E >: A]は
  def push[E >: A](e: E): Stack[E]
  // Stackの先頭のAを取り出す
  def top: A
  // Stack[A]を取り出す
  def pop: Stack[A]
  // Stackが空かどうか
  def isEmpty: Boolean
}

// EmptyじゃないStack
class NonEmptyStack[+A](private val first: A, private val rest: Stack[A]) extends Stack[A] {
  def push[E >: A](e: E): Stack[E] = new NonEmptyStack[E](e, this) // ???
  def top: A = first  // ???
  def pop: Stack[A] = rest  // ???
  def isEmpty: Boolean =  false // ???
}

// case objectとは
/*
ケースクラスと同様に、オブジェクトでも「case」を付けて定義するとメソッドが自動生成される。
ただしオブジェクトはコンストラクターに引数を取れないので、フィールドは作られない。
シングルトンなので、copy()メソッドも作られない。
equals()・canEqual()で等しくなるのは自分自身だけ。
オブジェクトなので、コンパニオンオブジェクトやそれに伴うapply()・unapply()も作られない。
*/

// Implement methods
/*
object creation impossible, since method equals(that: Any):Boolean in scala.Equals is not defined
*/
case object EmptyStack extends Stack[Nothing] {
  def push[E >: Nothing](e: E): Stack[E] = new NonEmptyStack[E](e, this)
  def top: Nothing = throw new IllegalArgumentException("empty stack")
  def pop: Nothing = throw new IllegalArgumentException("empty stack")
  def isEmpty: Boolean = true
}

// Stack[Nothing]を生成するオブジェクト
object Stack {
  def apply(): Stack[Nothing] = EmptyStack
}


/*
実行例

scala> :paste src/main/scala/Stack.scala

scala> val s = Stack()
s: Stack[Nothing] = EmptyStack

scala> s.top
java.lang.IllegalArgumentException: empty stack
  at EmptyStack$.top(<console>:61)
  at EmptyStack$.top(<console>:59)
  ... 42 elided

scala> s.pop
java.lang.IllegalArgumentException: empty stack
  at EmptyStack$.pop(<console>:62)
  at EmptyStack$.pop(<console>:59)
  ... 42 elided

scala> s.isEmpty
res8: Boolean = true

scala> val s1 = s.push(1)
s1: Stack[Int] = NonEmptyStack@2dba77dc

scala> s1.top
res10: Int = 1

scala> s1.isEmpty
res11: Boolean = false

scala> s1.pop
res12: Stack[Int] = EmptyStack

scala> val s0 = s1.pop
s0: Stack[Int] = EmptyStack

scala> s0.pop
java.lang.IllegalArgumentException: empty stack
  at EmptyStack$.pop(<console>:62)
  at EmptyStack$.pop(<console>:59)
  ... 42 elided

scala> s1.push("a")
<console>:20: warning: a type was inferred to be `Any`; this may indicate a programming error.
       s1.push("a")
               ^
res14: Stack[Any] = NonEmptyStack@33d216c6

scala> val s2 = s1.push(2)
s2: Stack[Int] = NonEmptyStack@2770f691

 */
