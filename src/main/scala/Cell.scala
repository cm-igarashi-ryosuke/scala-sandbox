/**
 * Created by igarashi.ryosuke on 2016/10/27.
 */
class Cell[A](var value: A) {
  def put(newValue: A): Unit = {
    value = newValue
  }

  def get(): A = value
}
