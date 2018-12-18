package study.leetcode_middle.Mathematics

object GetSum {
  def getSum(a: Int, b: Int): Int = {
    var tempA = a
    var tempB = b
    while (tempA != 0) {
      val temp = (tempA & tempB) << 1
      tempB = tempA ^ tempB
      tempA = temp
    }
    tempB
  }
}
