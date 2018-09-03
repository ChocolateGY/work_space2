package study.leetcode_primary.Tree

/**
  *
  * 验证二叉搜索树
  * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
  * *
  * 一个二叉搜索树具有如下特征：
  * *
  * 节点的左子树只包含小于当前节点的数。
  * 节点的右子树只包含大于当前节点的数。
  * 所有左子树和右子树自身必须也是二叉搜索树。
  * 示例 1:
  * *
  * 输入:
  * 2
  * / \
  * 1   3
  * 输出: true
  * 示例 2:
  * *
  * 输入:
  * 5
  * /  \
  * 1    4
  * / \
  * 3  6
  * 输出: false
  * 解释: 输入为: [5, 1, 4, null, null, 3, 6] 。
  * 根节点的值为 5 ， 但是其右子节点值为 4 。
  * Created by root on 2018 - 6 - 14.
  */

object Tree2 {

  // 之判断了当前几节点的左右子节点大小。失败
  def isValidBST(root: TreeNode): Boolean = {
    if (recursive(root) == 0)
      true
    else
      false
  }

  def recursive(root: TreeNode): Int = root match {
    case t: TreeNode => {
      var num = 0
      if (t.left != null) {
        if (t.value > t.left.value)
          num += recursive(t.left)
        else
          num += (1 + recursive(t.left))
      } else
        num += 0
      if (t.right != null) {
        if (t.value < t.right.value)
          num += recursive(t.right)
        else
          num += (1 + recursive(t.right))
      }
      else
        num += 0
      num
    }
    case _ => 0
  }

  //方法一
  def isValidBST2(root: TreeNode): Boolean = {
    if (root == null)
      true
    else
      recursive2(root, Long.MinValue, Long.MaxValue)
  }

  def recursive2(root: TreeNode, low: Long, high: Long): Boolean = {
    root match {
      case null => true
      case r if r.value <= low || root.value >= high => false
      case r => recursive2(r.left, low, r.value) && recursive2(r.right, r.value, high)
    }
  }

  /**
    * 这题实际上简化了难度，因为一般的二叉搜索树是左<=根<右，而这道题设定为左<根<右，那么就可以用中序遍历来做。因为如果不去掉左=根这个条件的话，那么下边两个数用中序遍历无法区分：
    *
    * 20       20
    * /           \
    * 20           20
    *
    * 它们的中序遍历结果都一样，但是左边的是BST，右边的不是BST。去掉等号的条件则相当于去掉了这种限制条件。下面我们来看使用中序遍历来做，这种方法思路很直接，通过中序遍历将所有的节点值存到一个数组里，然后再来判断这个数组是不是有序的，代码如下：
    *
    * @param root
    * @return
    */
  def isValidBST3(root: TreeNode): Boolean = {
    val arr = new collection.mutable.ArrayBuffer[Int]
    inorder(root, arr)
    var flag = true
    for (i <- 0 until arr.length - 1) {
      if (arr(i) > arr(i + 1))
        flag = false
    }
    flag
  }

  def inorder(root: TreeNode, list: collection.mutable.ArrayBuffer[Int]): Unit = {
    if (root != null) {
      inorder(root.left, list)
      list += root.value
      inorder(root.right, list)
    }

  }

  /**
    * 没理解
    *
    * @param root
    * @return
    */
  def isValidBST4(root: TreeNode): Boolean = {
    val s = new collection.mutable.Stack[TreeNode]()
    var p = root
    var pre: TreeNode = null
    var flag = true
    while (p != null || s.nonEmpty) {
      while (p != null) {
        s.push(p)
        p = p.left
      }
      val t = s.pop()
      if (pre != null && t.value <= pre.value)
        flag = false
      pre = t
      p = t.right
    }
    flag
  }


}
