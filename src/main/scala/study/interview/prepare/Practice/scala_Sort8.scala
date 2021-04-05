package study.interview.prepare.Practice

import scala.collection.mutable.ArrayBuffer

object scala_Sort8 {
  def main(args: Array[String]): Unit = {
    val arr = Array(4)
    //    q2Sort(arr)
    //    heapSort(arr)
    //    shellSort(arr)

    //    quickSort(arr)
    //    shellSort(arr)
    //    bubleSort(arr)
    //    insertSort(arr)
    //    radixSort(arr,1)
    //    buketSort(ArrayBuffer[Int](arr:_*),8)
    heapSort(arr)
    arr.foreach(println)
    //    val list = List[Int](4, 5, 7, 8, 1, 2, 3, 6)
    //    mergeSort(list).foreach(println)

    //    val res = qSort(list)
    //    res.foreach(println)
    //    println()
    //Range(1,1,-1) 不包含1, Range.inclusive(1,1,-1)包含1
    //    Range.inclusive(1,1,-1).foreach(println)
    //    (1 to 1).foreach(println)
  }

  //归并
  /**
    * def mergeSort(list: List[Int]): List[Int] = {
    * def merge(xList: List[Int], yList: List[Int]): List[Int] = {
    * (xList, yList) match {
    * case (Nil, _) => yList
    * case (_, Nil) => xList
    * case (x :: xTail, y :: yTail) =>
    * if (x > y)
    * x :: merge(xTail, yList)
    * else
    * y :: merge(xList, yTail)
    * }
    * }
    * *
    * val n = list.length / 2
    * if (n == 0)
    * list
    * else {
    * val (x, y) = list.splitAt(n)
    * merge(mergeSort(x), mergeSort(y))
    * }
    * }
    */

  def mergeSort(list: List[Int]): List[Int] = {
    def merge(xList: List[Int], yList: List[Int]): List[Int] = {
      (xList, yList) match {
        case (Nil, _) => yList
        case (_, Nil) => xList
        case (x :: xTail, y :: yTail) =>
          if (x > y)
            y :: merge(xList, yTail)
          else
            x :: merge(xTail, yList)
      }
    }

    val n = list.length / 2
    if (n == 0)
      list
    else {
      val (x, y) = list.splitAt(n / 2)
      merge(mergeSort(x), mergeSort(y))
    }
  }

  /**
    * 交换排序
    */

  //冒泡排序:
  //
  // 冒泡排序（Bubble Sort）是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，
  // 如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
  // 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
  def bubleSort(arr: Array[Int]) = {
    for (i <- 0 until arr.length - 1) {
      for (j <- 0 until arr.length - 1 - i) {
        if (arr(j) > arr(j + 1))
          swap(arr, j, j + 1)
      }
    }
  }

  //快速排序
  /**
    * 三步：
    * 1、选择基准值
    * 2、将数组分成两个子数组，小于基准值的和大于基准值的
    * 3、对这两个子数组快速排序
    * *
    * def qSort(list: List[Int]): List[Int] = list match {
    * case Nil => Nil
    * case pivot :: tail => qSort(list.filter(_ < pivot)) ++ list.filter(_ == pivot) ++ qSort(list.filter(_ > pivot))
    * }
    */
  def qSort(arr: Array[Int]): Array[Int] = {
    if (arr.length <= 1)
      arr
    else
      qSort(arr.filter(_ < arr.head)) ++ arr.filter(_ == arr.head) ++ qSort(arr.filter(_ > arr.head))
  }

  /**
    * java式 快排
    * *
    * def q2Sort(arr: Array[Int]): Unit = {
    * def partition(lo: Int, hi: Int): Int = {
    * var i = lo
    * var j = hi
    * val v = arr(lo)
    * while (i < j) {
    * while (arr(j) >= v && i < j)
    * j -= 1
    * arr(i) = arr(j)
    * while (arr(i) <= v && i < j)
    * i += 1
    * arr(j) = arr(i)
    * }
    * arr(i) = v
    * i
    * }
    * *
    * def sort(lo: Int, hi: Int): Unit = {
    * if (lo < hi) {
    * val i = partition(lo, hi)
    * sort(lo, i - 1)
    * sort(i + 1, hi)
    * }
    * }
    * *
    * sort(0, arr.length - 1)
    * }
    */
  def q2Sort(arr: Array[Int]) = {
    def sort(lo: Int, hi: Int): Unit = {
      if (lo >= hi) return
      val i = partition(lo, hi)
      sort(lo, i - 1)
      sort(i + 1, hi)
    }

    def partition(lo: Int, hi: Int): Int = {
      var i = lo
      var j = hi
      val temp = arr(lo)
      while (i < j) {
        while (i < j && arr(j) >= temp)
          j -= 1
        arr(i) = arr(j)
        while (i < j && arr(i) <= temp)
          i += 1
        arr(j) = arr(i)
      }
      arr(i) = temp
      i
    }

    if (arr.length >= 1)
      sort(0, arr.length - 1)
    else
      arr
  }

  /**
    * 优化：三数取中法
    * 思路是这样，但是没运行通
    */
  def quick3Sort(arr: Array[Int]): Unit = quick3Sort(arr, 0, arr.length - 1)

  def quick3Sort(arr: Array[Int], left: Int, right: Int): Unit = {
    dealPivot(arr, left, right)
    val pivot = right - 1
    var i = left
    var j = right - 1
    while (i < j) {
      i += 1
      while (arr(i) < arr(pivot)) {
        i += 1
      }
      j -= 1
      while (j > left && arr(j) > arr(pivot)) {
        j -= 1
      }
      if (i < j)
        swap(arr, i, j)
    }
    if (i < right)
      swap(arr, i, right - 1)
    quick3Sort(arr, left, i - 1)
    quick3Sort(arr, i + 1, right)
  }

  def dealPivot(arr: Array[Int], left: Int, right: Int): Unit = {
    val mid = left + (right - left) / 2
    if (arr(left) > arr(mid)) swap(arr, left, mid)
    if (arr(left) > arr(right)) swap(arr, left, right)
    if (arr(mid) > arr(right)) swap(arr, right, mid)
    swap(arr, right - 1, mid)
  }

  def swap(arr: Array[Int], a: Int, b: Int): Unit = {
    val temp = arr(a)
    arr(a) = arr(b)
    arr(b) = temp
  }


  /**
    * 选择排序
    */
  //简单选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。
  // 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
  // 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
  // 以此类推，直到所有元素均排序完毕。
  def selectSort(arr: Array[Int]) = {
    for (i <- arr.indices) {
      var minIdx = i
      for (j <- i + 1 until arr.length) {
        if (arr(j) < arr(minIdx))
          minIdx = j
      }
      swap(arr, i, minIdx)
    }
  }

  //堆排序
  //1、先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
  //2、再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
  //3、交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复步骤2, 直到无序区只有一个元素时停止。
  /**
    * def heapSort(arr: Array[Int]): Unit = {
    * def maxHeapify(arr: Array[Int], n: Int): Unit = {
    * for (i <- Range.inclusive((n - 1) / 2, 0, -1)) {
    * var child = 2 * i + 1
    * if (child != n && arr(child) < arr(child + 1))
    * child += 1
    * *
    * if (arr(i) < arr(child))
    * swap(arr, i, child)
    * }
    * }
    * *
    * for (i <- Range(arr.length - 1, 0, -1)) {
    * maxHeapify(arr, i)
    * swap(arr, 0, i)
    * }
    * }
    */
  def heapSort(arr: Array[Int]) = {
    def heapify(n: Int) = {
      for (i <- Range.inclusive((n - 1) / 2, 0, -1)) {
        var child = i * 2 + 1
        if (child != n && arr(child) < arr(child + 1))
          child += 1
        if (arr(child) > arr(i))
          swap(arr, i, child)
      }
    }

    for (i <- Range(arr.length - 1, 0, -1)) {
      heapify(i)
      swap(arr, 0, i)
    }
  }

  /**
    * 插入排序
    */

  //插简单入排序
  //通常人们整理桥牌的方法是一张一张的来，将每一张牌插入到其他已经有序的牌中的适当位置。
  // 在计算机的实现中，为了要给插入的元素腾出空间，我们需要将其余所有元素在插入之前都向右移动一位。
  /**
    * def insertSort(arr: Array[Int]) = {
    * for (i <- 1 until arr.length) {
    * for (j <- Range.inclusive(i, 1, -1) if (arr(j - 1) > arr(j))) {
    * swap(arr, j - 1, j)
    * }
    * }
    * }
    */
  def insertSort(arr: Array[Int]): Unit = {
    for (i <- 1 until arr.length) {
      for (j <- Range.inclusive(i, 1, -1) if arr(j) < arr(j - 1)) {
        swap(arr, j, j - 1)
      }
    }
  }

  //希尔排序
  //希尔排序是先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
  // 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
  /**
    * def shellSort(arr: Array[Int]): Unit = {
    * val n = arr.length
    * var h = 1
    * while (h < n / 3)
    * h = h * 3 + 1
    * while (h >= 1) {
    * for (i <- h until n) {
    * for (j <- Range.inclusive(i, h, -h) if (arr(j) < arr(j - h))) {
    * swap(arr, j, j - h)
    * }
    * }
    * h = h / 3
    * }
    * }
    */
  def shellSort(arr: Array[Int]): Unit = {
    var h = 1
    val n = arr.length
    while (h < n / 3) h = h * 3 + 1
    while (h >= 1) {
      for (i <- h until n) {
        for (j <- Range.inclusive(i, h, -h) if arr(j) < arr(j - h))
          swap(arr, j, j - h)
      }
      h /= 3
    }
  }

  /**
    * 基数排序
    */

  //基数排序 d为最大数的位数。 O(n * d)
  def radixSort(arr: Array[Int], d: Int) = {
    var k = 0
    var n, m = 1
    val temp = Array.ofDim[Int](10, arr.length)
    val order = new Array[Int](10)
    while (m <= d) {
      for (i <- arr.indices) {
        val lsd = arr(i) / n % 10
        temp(lsd)(order(lsd)) = arr(i)
        order(lsd) += 1
      }
      var i = 0
      while (i < 10) {
        if (order(i) != 0) {
          var j = 0
          while (j < order(i)) {
            arr(k) = temp(i)(j)
            k += 1
            j += 1
          }
        }
        order(i) = 0
        i += 1
      }
      n *= 10
      k = 0
      m += 1
    }
  }

  //桶排序
  def buketSort(arr: ArrayBuffer[Int], max: Int): ArrayBuffer[Int] = {
    var buckets = new Array[Int](max)
    for (i <- arr.indices) {
      buckets(arr(i)) = buckets(arr(i)) + 1
    }
    var j = 0
    for (i <- 0 until max) {
      while (buckets(i) > 0) {
        arr(j) = i
        j += 1
        buckets(i) = buckets(i) - 1
      }
    }
    buckets = null
    arr
  }
}
