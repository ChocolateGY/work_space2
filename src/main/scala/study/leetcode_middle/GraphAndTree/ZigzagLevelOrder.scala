package study.leetcode_middle.GraphAndTree

import scala.collection.mutable

/**
  * Created by root on 2018-8-24.
  *
  * 二叉树的锯齿形层次遍历
  * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
  * *
  * 例如：
  * 给定二叉树 [3,9,20,null,null,15,7],
  * *
  * 3
  * /\
  * 9 20
  * / \
  * 15 7
  * 返回锯齿形层次遍历如下 ：
  * *
  * [
  * [3],
  * [20, 9],
  * [15, 7]
  * ]
  *
  */

object ZigzagLevelOrder {
  def zigzagLevelOrder(root: TreeNode): List[List[Int]] = {
    val queue = new mutable.Queue[TreeNode]()
    val list = new mutable.ListBuffer[List[Int]]()
    var flag = true
    if (root != null)
      queue.enqueue(root)
    else
      return list.toList
    while (queue.nonEmpty) {
      var subList = List[Int]()
      val subStack = collection.mutable.Stack[Int]()
      val subQueue = collection.mutable.Queue[Int]()
      for (i <- queue.indices) {
        if (queue.head.left != null)
          queue.enqueue(queue.head.left)
        if (queue.head.right != null)
          queue.enqueue(queue.head.right)
        val value = queue.dequeue().value
        subStack.push(value)
        subQueue.enqueue(value)
        subList = subQueue.toList
      }
      if (!flag)
        subList = subList.reverse
      flag = !flag
      list += subList

    }
    list.toList
  }
}
