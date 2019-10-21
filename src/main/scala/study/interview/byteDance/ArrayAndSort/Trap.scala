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
    * 不好理解
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

  /**
    * 按列求
    * 原理：求当前列左边最大值和右边最大值，两者最小的和当前列相比，就是当前列的水位（小于等于当前列没水）
    * 时间O（n2)
    * 空间O(1)
    */
  def trap3(height: Array[Int]): Int = {
    var sum = 0
    for (i <- 1 to (height.length - 2)) {
      var preMax = 0
      for (pre <- 0 until i) {
        preMax = preMax.max(height(pre))
      }
      var lastMax = 0
      for (last <- i + 1 until height.length) {
        lastMax = lastMax.max(height(last))
      }
      val min = preMax.min(lastMax)

      if (height(i) < min)
        sum += (min - height(i))
    }
    sum
  }

  /**
    * 动态规划。
    *
    * 在按列的基础上，把前列最大值、后列最大值放到记录表中。就是trap1的实现
    *
    * 时间O(n)
    * 空间O(n)
    */
  def trap4(height: Array[Int]): Int = {
    val leftMax = Array.fill(height.length)(0)
    val rightMax = Array.fill(height.length)(0)

    for (i <- 1 to (height.length - 2)) {
      leftMax(i) = leftMax(i - 1).max(height(i - 1))
      rightMax(height.length - 1 - i) = rightMax(height.length - 1 - i + 1).max(height(height.length - 1 - i + 1))
    }

    var sum = 0
    for (i <- height.indices) {
      val min = leftMax(i).min(rightMax(i))
      if (min > height(i))
        sum += (min - height(i))
    }
    sum
  }

  /**
    * 动态规划 继续优化，
    *
    * 数组转为一个数字，左max好做，但右max有问题，需要加一个小技巧
    *
    * 根据判断  height(left-1) < height(right+1) 来确认从左开始更新还是从右开始更新
    *
    * ==========
    *
    * 所以这里要用到两个指针，left 和 right，从两个方向去遍历。
    *
    * 那么什么时候从左到右，什么时候从右到左呢？根据下边的代码的更新规则，我们可以知道
    *
    * max_left = Math.max(max_left, height[i - 1]);
    *
    * height [ left - 1] 是可能成为 max_left 的变量， 同理，height [ right + 1 ] 是可能成为 right_max 的变量。
    *
    * 只要保证 height [ left - 1 ] < height [ right + 1 ] ，那么 max_left 就一定小于 max_right。
    *
    * 因为 max_left 是由 height [ left - 1] 更新过来的，而 height [ left - 1 ] 是小于 height [ right + 1] 的，
    *
    * 而 height [ right + 1 ] 会更新 max_right，所以间接的得出 max_left 一定小于 max_right。
    *
    *===============
    * 这个是错误的版本。把遍历index来做指针是错误的
    */
  def trap5(height: Array[Int]): Int = {
    val len = height.length
    var sum = 0
    var leftMax, rightMax = 0
    for (i <- 1 to (len - 2)) {
      if (height(i - 1) < height(len - 1 - i + 1)) {
        leftMax = leftMax.max(height(i - 1))
        if (leftMax > height(i))
          sum += (leftMax - height(i))
      } else {
        rightMax = rightMax.max(height(len - 1 - i + 1))
        if (rightMax > height(len - 1 - i))
          sum += (rightMax - height(len - 1 - i))
      }
    }
    sum
  }

  /**
    * 这个是最终版本的正确版本
    */
  def trap5b(height: Array[Int]): Int = {
    val len = height.length
    var sum = 0
    var leftMax, rightMax = 0
    //两个指针是必须独立的，不能和遍历index有关系
    var left = 1
    var right = len - 2
    for (i <- 1 to (len - 2)) {
      if (height(left - 1) < height(right + 1)) {
        leftMax = leftMax.max(height(left - 1))
        if (leftMax > height(left))
          sum += (leftMax - height(left))
        left += 1
      } else {
        rightMax = rightMax.max(height(right + 1))
        if (rightMax > height(right))
          sum += (rightMax - height(right))
        right -= 1
      }
    }
    sum
  }
}
