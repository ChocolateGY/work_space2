package study.scalaMP4

object For_Function_Advanced {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 2; j <- 2 to 3 if i != j) println(i * 100 + j)
  }

  //函数是有值的，所以函数可以作为参数传入另一个函数。
  def addA(x: Int) = x + 100

  //匿名函数传入变量
  val add = (x: Int) => x + 200

  //递归必须声明函数返回类型
  def fibonaci(n: Int): Int = {
    if (n <= 2)
      1
    else
      fibonaci(n - 1) * fibonaci(n - 2)
  }

  //函数参数默认值
  def combine(content: String, left: String = "[", right: String = "]"): Unit = {
    println(left + content + right)
  }

  //函数的多个参数传入
  def connected(args: Int*): Unit = {
    var result = 0
    for (arg <- args) result += arg
    result
  }
}
