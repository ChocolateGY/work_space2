package study.leetcode_primary.Array

/**
  * 两个数组的交集 II
  *
  * 给定两个数组，写一个方法来计算它们的交集。
  * *
  * 例如:
  * 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
  * *
  * 注意：
  * *
  * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
  * 我们可以不考虑输出结果的顺序。
  * 跟进:
  * *
  * 如果给定的数组已经排好序呢？你将如何优化你的算法？
  * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
  * 如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？
  *
  * Created by root on 2018-5-31.
  */
object IntersectionOf2Arrays {


  /**
    * 仿照scala intersect 实现
    *
    * @param nums1
    * @param nums2
    * @return
    */
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    // scala 自己实现了intersect。仿照写一个
        nums1.intersect(nums2)
    val result = collection.mutable.ArrayBuffer[Int]()
    val occ = new collection.mutable.HashMap[Int, Int]() {
      override def default(k: Int) = 0
    }
    for (i <- nums2) occ(i) += 1
    for (i <- nums1) {
      if (occ(i) > 0) {
        result += i
        occ(i) -= 1
      }
    }
    result.toArray

  }

  def intersect2(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    var i1 = 0
    var i2 = 0
    val arr = collection.mutable.ArrayBuffer[Int]()
    while (i1 < nums1.length && i2 < nums2.length) {
      if (nums1(i1) == nums2(i2)) {
        arr += nums1(i1)
        i1 += 1
        i2 += 1
      } else if (nums1(i1) > nums2(i2)) {
        i2 += 1
      } else
        i1 += 1
    }
    arr.toArray
  }

  def main(args: Array[String]): Unit = {
    val nums1 = Array[Int](1, 2, 3, 3)
    val nums2 = Array[Int](3, 3, 4, 5)
    intersect2(nums1, nums2).foreach(println)
  }
}
