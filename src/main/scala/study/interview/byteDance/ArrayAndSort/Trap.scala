package study.interview.byteDance.ArrayAndSort

/**
  *
  * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1047/
  * 接雨水
  * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
  *
  *
  *
  * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
  *
  * 示例:
  *
  * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
  * 输出: 6
  */
object Trap {
  def main(args: Array[String]): Unit = {
    val height = Array(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    println(trap(height))
    println(trap2(height))
  }

  /**
    * 对于左木板而言，只要水位低于该位置往左最高的木板，水就能被兜住。对右木板同样道理。
    * 因此，当数组为（换行，对应起来方便看）
    * [0,1,0,2,1,0,1,3,2,1,2,1] <–柱子高度
    * [0,0,1,1,2,2,2,2,3,3,3,3] <–左木板，等于前一个柱子的高度与之前左木板的最大值的最大值
    * [3,3,3,3,3,3,3,2,2,2,1,0] <–右木板 ，等于后一个柱子的高度与之后的有木板的最大值的最大值
    *
    * 最后等兜住的雨水总量=Math.min( 左木板，右木板 ) - 该位置的竹子高度
    * ————————————————
    * 版权声明：本文为CSDN博主「qxdPx」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    * 原文链接：https://blog.csdn.net/qxdPx/article/details/88652520
    *
    * @param height
    * @return
    */
  def trap(height: Array[Int]): Int = {
    val len = height.length
    val left = Array.fill(len)(0)
    val right = Array.fill(len)(0)
    for (i <- 1 until len) {
      left(i) = left(i - 1).max(height(i - 1))
      right(len - i - 1) = right(len - i).max(height(len - i))
    }
    var res = 0
    for (i <- height.indices) {
      val cur = left(i).min(right(i)) - height(i)
      if (cur > 0)
        res += cur
    }
    res
  }

  /**
    * leetcode 最快答案
    *
    * @param height
    * @return
    */
  def trap2(height: Array[Int]): Int = {
    var p1 = 0
    var p2 = height.length - 1
    var max1 = 0
    var max2 = 0

    var rs = 0

    while (p1 < p2) {
      if (height(p1) < height(p2)) {
        if (height(p1) >= max1) max1 = height(p1) else rs += (max1 - height(p1))
        p1 += 1
      } else {
        if (height(p2) >= max2) max2 = height(p2) else rs += (max2 - height(p2))
        p2 -= 1
      }
    }
    rs
  }
}
