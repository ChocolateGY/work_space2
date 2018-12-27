package study.leetcode_advanced.ArrayAndString

/**
  * 盛最多水的容器
  * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
  *
  * 说明：你不能倾斜容器，且 n 的值至少为 2。
  *
  *
  *
  * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
  *
  *
  *
  * 示例:
  *
  * 输入: [1,8,6,2,5,4,8,3,7]
  * 输出: 49
  */
object MaxArea {
  def maxArea(height: Array[Int]): Int = {
    var water = 0
    for (i <- height.indices) {
      for (j <- i + 1 until height.length) {
        water = water.max((j - i) * height(i).min(height(j)))
      }
    }
    water
  }

  /**
    * 耗时最短
    * @param height
    * @return
    */
  def maxArea2(height: Array[Int]): Int = {
    var l = 0
    var r = height.length - 1
    var area = 0
    var h = 0
    while (l < r) {
      h = height(l) min height(r)
      area = area max h * (r - l)
      while (height(l) <= h && l < r)
        l += 1
      while (height(r) <= h && l < r)
        r -= 1
    }
    area
  }
  def main(args: Array[String]): Unit = {
    val res = maxArea(Array(1,8,6,2,5,4,8,3,7))
    println(res)
  }
}
