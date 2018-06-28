package study.leetcode.Tree

import java.util


/**
  *
  * 二叉树的最大深度
  * 给定一个二叉树，找出其最大深度。
  * *
  * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
  * *
  * 说明: 叶子节点是指没有子节点的节点。
  * *
  * 示例：
  * 给定二叉树 [3,9,20,null,null,15,7]，
  *
  *   3
  * / \
  * 9   20
  *   /  \
  * 15   7
  *
  * 返回它的最大深度 3 。
  * Created by root on 2018 - 6 - 13.
  */

object Tree1 {

  //递归
  def maxDepth(root: TreeNode): Int = {
    root match {
      case null => 0
      case tree: TreeNode => {
        1 + Math.max(maxDepth(tree.left), maxDepth(tree.right))
      }
    }

  }

  // 非递归，使用了队列
  def maxDepth2(root: TreeNode): Int = {
    if (root == null)
      0
    else {
      var res = 0
      val q: util.Queue[TreeNode] = new util.LinkedList[TreeNode]()
      q.offer(root)
      while (!q.isEmpty) {
        res += 1
        val n = q.size()
        for (i <- 0 until n) {
          val t = q.poll()
          if (t.left != null) q.offer(t.left)
          if (t.right != null) q.offer(t.right)
        }
      }
      res
    }
  }


  def main(args: Array[String]): Unit = {
    val tree = new TreeNode(3)
    tree.left = new TreeNode(9)
    tree.right = new TreeNode(20)
    tree.right.left = new TreeNode(15)
    tree.right.right = new TreeNode(7)
    println(maxDepth2(tree))
  }
}

class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}