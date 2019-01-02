package study.DataStructure

/**
  * 冒泡排序
  *
  * 比较两个元素，将相邻两个元素中较大的交换到后面。经过对所有元素的比较，最大的元素就被交换到了最后一个位置
  * ，接下来是第二大元素，，等等。最后完成排序
  */
object BubbleSort {

  def bubSort(arr: Array[Int]): Unit = {
    var temp = 0
    for (i <- 0 until arr.length - 1) {
      for (j <- 0 until arr.length - i - 1) {
        if (arr(j) > arr(j + 1)) {
          temp = arr(j)
          arr(j) = arr(j + 1)
          arr(j + 1) = temp
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(5,3,4,2,1)
    bubSort(arr)
    arr.foreach(x=>print(x + " "))
  }
}
