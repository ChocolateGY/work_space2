package study.interviewPrepare.Tree

object MaxDepth {
  //自上而下遍历
  def maxDepth(root: TreeNode): Int = {
    var answer = 0

    def recursive(node: TreeNode, depth: Int): Unit = {
      if (node != null) {
        if (node.left == null && node.right == null)
          answer = answer.max(depth)
        recursive(node.left, depth + 1)
        recursive(node.right, depth + 1)
      }
    }

    recursive(root, 1)
    answer
  }

  //自下而上遍历
  def maxDepth2(root: TreeNode): Int = {

    def recursive(node: TreeNode): Int = {
      if(node==null)
       return  0
      val left = recursive(node.left)
      val right = recursive(node.right)
      left.max(right) + 1
    }
    recursive(root)
  }
}
