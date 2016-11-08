/**
 * Created by igarashi.ryosuke on 2016/10/17.
 */
class Obj {
  private var var1: String = ""
  private[this] var var2: String = ""
}

object Obj {
  def apply(_var1: String, _var2: String): Obj ={
    val obj = new Obj()
    obj.var1 = _var1  // accessable
    // obj.var2 = _var2  // inaccessable

    obj
  }
}

// 練習問題
class Obj2 () {
  private var var1: String = "var1"
  private[this] var var2: String = "var2"
}

object Obj2 {
  def printvar(): Unit = {
    val obj2 = new Obj2()
    println(obj2.var1)  // accessable
//    println(obj2.var2)  // inaccessable
  }
}
