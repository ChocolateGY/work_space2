package study.interview.prepare.Tree

/**
  * 106 从中序与后序遍历序列构造二叉树
  *
  * 根据一棵树的中序遍历与后序遍历构造二叉树。
  *
  * 注意:
  * 你可以假设树中没有重复的元素。
  *
  * 例如，给出
  *
  * 中序遍历 inorder = [9,3,15,20,7]
  * 后序遍历 postorder = [9,15,7,20,3]
  * 返回如下的二叉树：
  *
  * 3
  * / \
  * 9  20
  * /  \
  * 15   7
  *
  * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/hou-xu-bian-li-python-dai-ma-java-dai-ma-by-liwe-2/
  *
  * 总结：这里的索引 画个图一计算就ok
  */
object BuildTreeInAndPostOrder {
  def buildTree(inorder: Array[Int], postorder: Array[Int]): TreeNode = {
    val inlen = inorder.length
    val postlen = postorder.length
    if (inlen != postlen)
      return null
    val hash = scala.collection.mutable.Map[Int, Int]()
    for (i <- postorder.indices)
      hash += (inorder(i) -> i)

    def recursive(inleft: Int, inright: Int, postleft: Int, postright: Int): TreeNode = {
      if (inleft > inright || postleft > inright) {
        return null
      }
      val pivot = postorder(postright)
      val pivotIndex = hash(pivot)
      val root = new TreeNode(pivot)
      //画图，先计算右边
      //      root.right = recursive(pivotIndex + 1, inright, postright - inright + pivotIndex, postright - 1)
      //      root.left = recursive(inleft, pivotIndex - 1, postleft, postright - inright + pivotIndex - 1)
      //画图，先计算左边 ，都可以
      root.right = recursive(pivotIndex + 1, inright, pivotIndex - inleft + postleft, postright - 1)
      root.left = recursive(inleft, pivotIndex - 1, postleft, pivotIndex - 1 - inleft + postleft)
      root
    }

    recursive(0, inlen - 1, 0, postlen - 1)
  }
}
