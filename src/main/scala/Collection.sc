
// // foldLeft練習問題
def reverse[T](list: List[T]): List[T] =
  list.foldLeft(Nil: List[T])((x, y) => y :: x)
//  list.foldRight(Nil: List[T])((y, x) => x match {
//    case Nil => y :: x
//    case _: T => x :: y
//  }

reverse(List(1,2,3,4,5))

def sum_l(list: List[Int]): Int = (0 /: list)(_ + _)
sum_l(List(1,2,3))

def sum_r(list: List[Int]): Int = (list :\ 0)(_ + _)
sum_r(List(1,2,3))

// foldRight練習問題
def sum(list: List[Int]): Int =
  list.foldRight(0)(_ + _)
//  list.foldRight(0)((x, y) => x + y)
//  list.sum

sum(List(1,2,3))

sum(List())

def mul(list: List[Int]): Int =
  list.foldRight(1)((x, y) => x * y)
//  list.product

mul(List(2,3,4))

mul(List())

def mkString[T](list: List[T])(sep: String): String = list match {
  case Nil => ""
  case x::xs => xs.foldLeft(x.toString){(x, y) => x + sep + y}
//  case x::xs => xs.foldRight(x.toString){(x, y) => x + sep + y}
}

mkString(List(1,2,3))(" - ")

// map
List(1, 2, 3, 4, 5).map(x => x * 2)

def map[T, U](list: List[T])(f: T => U): List[U] =
  list.foldLeft(Nil: List[U])((x, y) => f(y) :: x).reverse

map[Int, String](List(1,2,3,4,5))(x => x.toString())

// filter
List(1, 2, 3, 4, 5).filter(x => x % 2 == 1)

def filter[T](list: List[T])(f: T => Boolean): List[T] =
  list.foldLeft(Nil: List[T])((x, y) => f(y) match {
    case true  => y :: x
    case false => x
  }).reverse

filter(List(1,2,3,4,5))(x => (x % 3) == 0)

// find
List(1, 2, 3, 4, 5).find(x => x % 2 == 1)

// takeWhile
List(1, 2, 3, 4, 5).takeWhile(x => x != 5)

// count
List(1, 2, 3, 4, 5).count(x => x % 2 == 0)

def count[T](list: List[T])(f: T => Boolean): Int =
  list.foldLeft(0)((x, y) => f(y) match {
    case true  => x + 1
    case false => x
  })

count(List(1,2,3,4,5))(x => x % 2 == 0)

// flatMap
List(List(1, 2, 3), List(4, 5)).flatten
List(List(1, 2, 3), List(4, 5)).flatten.map(x => x + 1)
List(List(1, 2, 3), List(4, 5)).flatMap{e => e.map{g => g + 1}}


List(1, 2, 3).flatMap{e => List(4, 5).map(g => e * g)}

val col1 = List(1,2,3)
val col2 = List(4,5,6)

col1.flatMap{x => col2.map{y => x + y}}

//for {
//  x <- col1
//  y <- col2
//} yield x + y

// Vector
// http://docs.scala-lang.org/ja/overviews/collections/concrete-immutable-collection-classes
// ベクトルは分岐度の高い木構造で表される。
// 全てのノードは32以下の要素か、32以下の他のノードを格納する。
// 32個以下の要素を持つベクトルは単一のノードで表すことができる。
Vector(1, 2, 3, 4, 5)
6 +: Vector(1, 2, 3, 4, 5)
Vector(1, 2, 3, 4, 5) :+ 6
Vector(1, 2, 3, 4, 5).updated(2, 5)

// Map
// scala.collection.immutable.Map
val m = Map("A" -> 1, "B" -> 2, "C" -> 3)
m.updated("B", 4)
m

// scala.collection.mutable.Map
import scala.collection.mutable
val mm = mutable.Map("A" -> 1, "B" -> 2, "C" -> 3)
mm.updated("B", 4)
mm

// Set
// http://docs.scala-lang.org/ja/overviews/collections/sets
// 集合の演算が使えるのは便利
// scala.collection.immutable.Set
val s = Set(1, 1, 2, 3, 4)
s - 1
s

// scala.collection.mutable.Set
import scala.collection.mutable
val ms = mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9) // 順序は保証されていない
ms -= 1
ms

import scala.collection.immutable.TreeSet
TreeSet(1,2,3,4,5,6,7,8,9)
TreeSet(1,8,9,4,7,6,5,2,3)

// 集合演算が使える
Set(1,2,3,4,5) -- Set(1,3,5)
Set(1,2,3,4,5).map(p => p + 1)
Set(1,2,3,4,5).filter(p => p % 2 == 0)


// Stream
// http://docs.scala-lang.org/ja/overviews/collections/concrete-immutable-collection-classes
val str = 1 #:: 2 #:: 3 #:: Stream.empty
