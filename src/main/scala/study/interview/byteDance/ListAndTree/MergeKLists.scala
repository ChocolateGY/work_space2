package study.interview.byteDance.ListAndTree

/**
  * 合并K个排序链表
  * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
  *
  * 示例:
  *
  * 输入:
  * [
  * 1->4->5,
  * 1->3->4,
  * 2->6
  * ]
  * 输出: 1->1->2->3->4->4->5->6
  */
object MergeKLists {

  import scala.collection.mutable.ArrayBuffer

  def main(args: Array[String]): Unit = {

  }

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    val arr = ArrayBuffer[Int]()
    lists.foreach {
      li =>
        var node = li
        while (node != null) {
          arr += node.x
          node = node.next
        }
    }
    val resArr = quickSort(arr)
    val headNode = new ListNode(0)
    var curNode = headNode
    resArr.foreach {
      x =>
        val node = new ListNode(x)
        curNode.next = node
        curNode = node
    }
    headNode.next
  }

  def quickSort(arr: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    if (arr.length < 2)
      arr
    else
      quickSort(arr.filter(_ < arr.head)) ++ arr.filter(_ == arr.head) ++ quickSort(arr.filter(_ > arr.head))
  }

}
