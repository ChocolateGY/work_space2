package study.scalaMP4

/**
  * 伴生类，可以访问伴生对象的所有权限的成员。
  */
class University {
  val id = University.newStudenNo
  private var number = 0

  def aClass(number: Int) {
    this.number += number
  }
}

/**
  * 伴生对象
  * 里面的成员都是静态的，但是只有调用的时候才会执行。
  * 字段和方法都是共享的静态状态。
  */
object University {
  private var studentNo = 0

  def newStudenNo = {
    studentNo += 1
    studentNo
  }
}


object ObjecOps {

  def main(args: Array[String]): Unit = {

    println(University.newStudenNo)
    println(University.newStudenNo)


  }

}