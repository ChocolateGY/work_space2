package study.leetcode_middle.GraphAndTree

import scala.collection.mutable.ListBuffer

/**
  * Created by root on 2018-8-23.
  *
  * 中序遍历二叉树
  * 给定一个二叉树，返回它的中序 遍历。
  * *
  * 示例:
  * *
  * 输入: [1,null,2,3]
  * 1
  * \
  * 2
  * /
  * 3
  * *
  * 输出: [1, 3, 2]
  * 进阶: 递归算法很简单 ， 你可以通过迭代算法完成吗 ？
  *
  */

object InorderTraversal {
  /**
    * 栈的方式 2
    *
    * @param root
    * @return
    */
  def inorderTraversal3(root: TreeNode): List[Int] = {
    val list = collection.mutable.ListBuffer[Int]()
    val stack = collection.mutable.Stack[TreeNode]()
    var node = root
    while (true) {
      if (node != null) {
        stack.push(node)
        node = node.left
      } else {
        if(stack.isEmpty)
          return list.toList
        node = stack.pop()
        list += node.value
        node = node.right
      }

    }
    list.toList
  }

  /**
    * 栈的方式
    * 未学会
    *
    * @param root
    * @return
    */
  def inorderTraversal(root: TreeNode): List[Int] = {
    val list = collection.mutable.ListBuffer[Int]()
    val stack = collection.mutable.Stack[TreeNode]()
    if (root != null) {
      stack.push(root)
      var p = root.left
      while (stack.nonEmpty) {
        while (p != null) {
          stack.push(p)
          p = p.left
        }
        val n = stack.pop()
        list += n.value
        p = n.right
        if (p != null) {
          stack.push(p)
          p = p.left
        }
      }
      list.toList
    } else
      list.toList

  }

  /**
    * 递归方法
    *
    * @param root
    * @return
    */
  def inorderTraversal2(root: TreeNode): List[Int] = {
    val list = collection.mutable.ListBuffer[Int]()
    inorder(root, list)
    list.toList
  }

  def inorder(root: TreeNode, list: ListBuffer[Int]): Unit = {
    if (root != null) {
      if (root.left != null) inorder(root.left, list)
      list += root.value
      if (root.right != null) inorder(root.right, list)
    }
  }
}

class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}