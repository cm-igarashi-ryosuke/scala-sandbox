// Object

// sbt console
// :paste src/main/scala/User.scala

class User(val name: String, private val age: Int)

object User {
  def apply(name: String, age: Int) = new User(name, age)
  // コンパニオンオブジェクトからはClassのプライベート変数にアクセスできる
  def printUser(user: User) = println(user.name + " " + user.age.toString)
}
