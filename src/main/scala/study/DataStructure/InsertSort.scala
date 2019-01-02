package study.DataStructure

/**
  * 插入排序
  *
  * 抽出一个元素，与前面的元素进行比较找到适当的位置进行插入。
  */
object InsertSort {
  def insertSort(arr: Array[Int]): Unit = {
    //从第二个位置开始向前比较
    for (i <- 1 until arr.length) {
      val select = arr(i)
      var j = i
      // 与前面的元素进行比较大小，确定要插入的位置，（直到比它小的元素的后面）
      while (j > 0 && arr(j - 1) > select) {
        //将大元素进行右移
        arr(j) = arr(j - 1)
        j -= 1
      }
      //将抽取元素插入正确位置
      arr(j) = select
    }
  }

  def main(args: Array[String]): Unit = {
    val arr = Array(2, 5, 1, 4, 3)
    insertSort(arr)
    arr.foreach(x => print(x + "\t"))
  }
}
