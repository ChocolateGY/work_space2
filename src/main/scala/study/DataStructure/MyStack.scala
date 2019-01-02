package study.DataStructure

class MyStack(date:Array[Long] = new Array[Long](50)) {
  var top: Int = -1
  var size = 50


  def push(elem: Long) = {
    top += 1
    date(top) = elem
  }

  def pop(): Long = {
    val res = date(top)
    top -= 1
    res
  }
  val stack  =collection.mutable.Stack
}
