package study.interview.prepare.Tree

/**
  * 路径总和
  * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
  *
  * 说明: 叶子节点是指没有子节点的节点。
  *
  * 示例:
  * 给定如下二叉树，以及目标和 sum = 22，
  *
  * 5
  * / \
  * 4   8
  * /   / \
  * 11  13  4
  * /  \      \
  * 7    2      1
  * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
  *
  * https://leetcode-cn.com/problems/path-sum/solution/lu-jing-zong-he-by-leetcode/
  */
object HasPathSum {

  def main(args: Array[String]): Unit = {
    val a5 = new TreeNode(5)
    val a4 = new TreeNode(4)
    val a11 = new TreeNode(11)
    val a7 = new TreeNode(7)
    val a2 = new TreeNode(2)
    val a8 = new TreeNode(8)
    val a13 = new TreeNode(13)
    val a4b = new TreeNode(4)
    val a1 = new TreeNode(1)
    a5.left = a4
    a4.left = a11
    a11.left = a7
    a11.right = a2
    a5.right = a8
    a8.left = a13
    a8.right = a4b
    a4b.right = a1
    println("res1 = " + hasPathSum(a5, 22))
    println("res2 = " + hasPathSum2(a5, 22))
  }

  //递归
  def hasPathSum(root: TreeNode, sum: Int): Boolean = {
    if (root == null)
      return false
    val res = sum - root.value
    if (root.left == null && root.right == null && res == 0)
      return true
    hasPathSum(root.left, res) || hasPathSum(root.right, res)
  }

  //迭代
  def hasPathSum2(root: TreeNode, sum: Int): Boolean = {
    import scala.collection.mutable._
    if (root == null)
      return false
    val stack = Stack[(TreeNode,Int)]((root,sum-root.value))
    while (stack.nonEmpty) {
      val (node,num) = stack.pop()
      if ((node.right == null) && (node.left == null) && (num == 0))
        return true

      if (node.right != null) {
        stack.push((node.right,num-node.right.value))
      }
      if (node.left != null) {
        stack.push((node.left,num-node.left.value))
      }
    }
    false
  }
}
