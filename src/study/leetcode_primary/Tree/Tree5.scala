package study.leetcode_primary.Tree

/**
  *
  * 将有序数组转换为二叉搜索树
  * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
  * *
  * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
  * *
  * 示例:
  * *
  * 给定有序数组: [-10,-3,0,5,9],
  * *
  * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
  * *
  * 0
  * / \
  *- 3 9
  * / /
  *- 10 5
  * Created by root on 2018 - 6 - 27.
  */

object Tree5 {

  def sortedArrayToBST(nums: Array[Int]): TreeNode = {
    getTree(nums, 0, nums.length - 1)
  }


  def getTree(nums: Array[Int], low: Int, high: Int): TreeNode = {
    if (low <= high) {
      val middle = (low + high) / 2
      val node = new TreeNode(nums(middle))
      node.left = getTree(nums, low, middle - 1)
      node.right = getTree(nums, middle + 1, high)
      node
    } else
      null
  }

}
