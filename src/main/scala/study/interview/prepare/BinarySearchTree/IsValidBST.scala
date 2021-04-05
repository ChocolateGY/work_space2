package study.interview.prepare.BinarySearchTree

/**
  * 验证二叉搜索树
  * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
  *
  * 假设一个二叉搜索树具有如下特征：
  *
  * 节点的左子树只包含小于当前节点的数。
  * 节点的右子树只包含大于当前节点的数。
  * 所有左子树和右子树自身必须也是二叉搜索树。
  * 示例 1:
  *
  * 输入:
  * 2
  * / \
  * 1   3
  * 输出: true
  * 示例 2:
  *
  * 输入:
  * 5
  * / \
  * 1   4
  * / \
  * 3   6
  * 输出: false
  * 解释: 输入为: [5,1,4,null,null,3,6]。
  * 根节点的值为 5 ，但是其右子节点值为 4 。
  *
  * https://leetcode-cn.com/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode/
  */
object IsValidBST {
  //递归，广度优先
  def isValidBST(root: TreeNode): Boolean = {
    def recursive(node: TreeNode, lower: Option[Int], upper: Option[Int]): Boolean = {
      if (node == null)
        true
      else {
        val value = node.value
        if (upper.nonEmpty && value >= upper.get)
          false
        else if (lower.nonEmpty && value <= lower.get)
          false
        else
          recursive(node.left, lower, Some(value)) && recursive(node.right, Some(value), upper)
      }
    }

    recursive(root, None, None)
  }

  //迭代。深度优先  中序遍历
  def isValidBST2(root: TreeNode): Boolean = {
    import scala.collection.mutable.Stack
    val stack = Stack[TreeNode]()
    var inorder = -Double.MaxValue
    var node = root
    while (stack.nonEmpty || node != null) {
      while (node != null) {
        stack.push(node)
        node = node.left
      }
      node = stack.pop()
      if (node.value<= inorder)
        return false
      inorder = node.value
      node = node.right
    }
    true
  }
}
