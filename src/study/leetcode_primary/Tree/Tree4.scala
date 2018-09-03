package study.leetcode_primary.Tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  *
  *
  * 二叉树的层次遍历
  * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
  * *
  * 例如:
  * 给定二叉树: [3,9,20,null,null,15,7],
  * *
  * 3
  * /\
  * 9 20
  * /\
  * 15 7
  * 返回其层次遍历结果 ：
  * *
  * [
  * [3],
  * [9, 20],
  * [15, 7]
  * ]
  * Created by root on 2018 - 6 - 26.
  */

object Tree4 {
  def levelOrder(root: TreeNode): List[List[Int]] = {
    val list = ListBuffer[List[Int]]()
    if (root == null)
      list.toList
    else {
      val queue = mutable.Queue(root)
      while (queue.nonEmpty) {
        val subList = ListBuffer[Int]()
        for (i <- queue.indices) {
          val node = queue.dequeue()
          subList += node.value
          if (node.left != null) queue.enqueue(node.left)
          if (node.right != null) queue.enqueue(node.right)
        }
        list += subList.toList
      }
      list.toList
    }
  }

}
