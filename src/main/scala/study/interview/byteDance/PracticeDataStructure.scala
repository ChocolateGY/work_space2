package study.interview.byteDance

object PracticeDataStructure {
  def main(args: Array[String]): Unit = {

  }

  class MinStack() {

    import scala.collection.mutable.Stack

    val data = Stack[Int]()
    val helper = Stack[Int]()

    def push(x: Int): Unit = {
      data.push(x)
      if (helper.nonEmpty || x <= helper.top)
        helper.push(x)
    }

    def pop(): Unit = {
      if (data.nonEmpty) {
        val x = data.pop()
        if (x == helper.top)
          helper.pop
      }
    }

    def top(): Int = {
      if (data.nonEmpty)
        data.top
      else
        throw new RuntimeException("no elements")
    }

    def getMin(): Int = {
      if (helper.nonEmpty)
        helper.top
      else
        throw new RuntimeException("no elements")
    }
  }

}
