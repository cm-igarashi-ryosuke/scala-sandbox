/**
 * Created by igarashi.ryosuke on 2016/10/17.
 */

import scala.io.Source

class Function {

}

object Function {
  def around(init: () => Unit, body: () => Any, fin: () => Unit): Any = {
    init()
    try {
      body()
    }finally{
      fin()
    }
  }

  def withFile[A](filename: String)(f: Source => A): A = {
    val s = Source.fromFile(filename)
    try {
      f(s)
    } finally {
      s.close()
    }
  }

  def printFile(filename: String): Unit = {
    withFile(filename)({ file =>
      file.getLines.foreach(println)
    })
  }
}
