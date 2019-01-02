package study.DataStructure

/**
  * 选择排序
  * 扫描所有元素，将最小元素与第一个位置的元素进行交换。接着扫描剩余的元素，将最小的元素与第二个
  * 位置的元素进行交换，直到排序完成。
  */
object SelectSort {

  /**
    * 交换次数少
    *
    * @param arr
    */
  def selectSort(arr: Array[Int]): Unit = {
    var min = 0
    var temp = 0
    for (i <- 0 until arr.length - 1) {
      min = i
      for (j <- i + 1 until arr.length) {
        if (arr(min) > arr(j)) {
          min = j
        }
      }
      temp = arr(i)
      arr(i) = arr(min)
      arr(min) = temp
    }
  }

  /**
    * 交换次数多
    *
    * @param arr
    */
  def selectSort2(arr: Array[Int]): Unit = {
    var temp = 0
    for (i <- 0 until arr.length - 1) {
      for (j <- i + 1 until arr.length) {
        if (arr(i) > arr(j)) {
          temp = arr(i)
          arr(i) = arr(j)
          arr(j) = temp
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2, 5, 1, 4, 3)
    selectSort(arr)
    arr.foreach(x => print(x + "\t"))
  }
}
