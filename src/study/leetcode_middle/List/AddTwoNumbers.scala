package study.leetcode_middle.List

/**
  * Created by root on 2018-8-22.
  *
  * 两数相加
  * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
  * *
  * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
  * *
  * 示例：
  * *
  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
  * 输出：7 -> 0 -> 8
  * 原因：342 + 465 = 807
  *
  */
object AddTwoNumbers {

  def addTwoNumbers3(l1: ListNode, l2: ListNode): ListNode = {
    var head = new ListNode()
    var p = head
    var carry = 0
    var l1_ = l1
    var l2_ = l2
    while (l1_ != null || l2_ != null || carry != 0) {
      var n1 = if (l1_ != null) l1_.x
      else
        0
      var n2 = if (l2_ != null) l2_.x
      else
        0
      val sum = n1 + n2 + carry
      carry = sum / 10
      l1_ = if (l1_ != null) l1_.next else l1_
      l2_ = if (l2_ != null) l2_.next else l2_
      p.next = new ListNode(sum % 10)
      p = p.next
    }
    head.next
  }

  /**
    * 超出最大值
    * java.lang.NumberFormatException: For input string: "1000000000000000000000000000001"
    * [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
    * [5,6,4]
    */
  def addTwoNumbers2(l1: ListNode, l2: ListNode): ListNode = {
    var l1_ = l1
    var l2_ = l2
    val arr1, arr2 = collection.mutable.ArrayBuffer[Int]()
    while (l1_ != null) {
      arr1 += l1_.x
      l1_ = l1_.next
    }
    while (l2_ != null) {
      arr2 += l2_.x
      l2_ = l2_.next
    }
    val result = new ListNode(0)
    var result1 = result
    val n1 = arr1.mkString("").reverse.toLong
    val n2 = arr2.mkString("").reverse.toLong
    val sum = (n1 + n2).toString.reverse
    sum.foreach {
      c =>
        result1.next = new ListNode(c.toString.toInt)
        result1 = result1.next
    }
    result.next
  }

  def main(args: Array[String]): Unit = {
    val arr1 = Array(2, 4, 3)
    val arr2 = Array(5, 6, 4)
    val l1 = new ListNode(0)
    var l1_ = l1
    arr1.foreach {
      x =>
        l1_.next = new ListNode(x)
        l1_ = l1_.next
    }
    val l2 = new ListNode(0)
    var l2_ = l2
    arr2.foreach {
      x =>
        l2_.next = new ListNode(x)
        l2_ = l2_.next
    }

    var result = addTwoNumbers2(l1.next, l2.next)
    while (result != null) {
      println(result.x)
      result = result.next
    }
  }
}

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}