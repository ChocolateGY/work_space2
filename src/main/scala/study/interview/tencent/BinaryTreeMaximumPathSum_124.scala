package study.interview.tencent

/**
  * 给定一个非空二叉树，返回其最大路径和。
  *
  * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
  *
  * 示例 1:
  *
  * 输入: [1,2,3]
  *
  * 1
  * / \
  * 2   3
  *
  * 输出: 6
  * 示例 2:
  *
  * 输入: [-10,9,20,null,null,15,7]
  *
  *    -10
  *    / \
  *   9  20
  *     /  \
  *    15   7
  *
  * 输出: 42
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  */
object BinaryTreeMaximumPathSum_124 {

  //题意有点绕，“路径和”
  def maxPathSum(root: TreeNode): Int = {
    var res_max = Int.MinValue

    def recursive(node: TreeNode): Int = {
      if (node == null)
        0
      else {
        val left = recursive(node.left)
        val right = recursive(node.right)
        val sum = node.value + left.max(right)
        var cur_max = node.value
        if (left > 0)
          cur_max += left
        if (right > 0)
          cur_max += right
        if (cur_max > res_max)
          res_max = cur_max

        node.value.max(sum)
      }
    }

    recursive(root)
    res_max
  }
}
