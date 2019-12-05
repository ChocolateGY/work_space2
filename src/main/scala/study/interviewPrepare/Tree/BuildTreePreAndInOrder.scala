package study.interviewPrepare.Tree

/**
  * 105 从前序与中序遍历序列构造二叉树
  *
  * 根据一棵树的前序遍历与中序遍历构造二叉树。
  *
  * 注意:
  * 你可以假设树中没有重复的元素。
  *
  * 例如，给出
  *
  * 前序遍历 preorder = [3,9,20,15,7]
  * 中序遍历 inorder = [9,3,15,20,7]
  * 返回如下的二叉树：
  *
  * 3
  * / \
  * 9  20
  * /  \
  * 15   7
  * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/qian-xu-bian-li-python-dai-ma-java-dai-ma-by-liwei/
  */
object BuildTreePreAndInOrder {
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    val prelen = preorder.length
    val inlen = inorder.length
    if (prelen != inlen)
      return null

    def recursive(preLeft: Int, preRight: Int, inLeft: Int, inRight: Int): TreeNode = {
      //递归国际惯例，先写终止条件
      if (preLeft > preRight || inLeft > inRight)
        return null
      //先序遍历的起点元素很重要
      val pivot = preorder(preLeft)
      val root = new TreeNode(pivot)

      var pivotIndex = inLeft
      while (inorder(pivotIndex) != pivot)
        pivotIndex += 1

      root.left = recursive(preLeft + 1, pivotIndex - inLeft + preLeft, inLeft, pivotIndex - 1)
      root.right = recursive(pivotIndex - inLeft + preLeft + 1, preRight, pivotIndex + 1, inRight)
      root
    }

    recursive(0, prelen - 1, 0, inlen - 1)
  }

  def buildTree2(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    val prelen = preorder.length
    val inlen = inorder.length
    if (prelen != inlen)
      return null
    val hash = scala.collection.mutable.Map[Int, Int]()
    for (i <- inorder.indices) {
      hash += (inorder(i) -> i)
    }

    def recursive(preleft: Int, preright: Int, inleft: Int, inright: Int): TreeNode = {
      if (preleft > preright || inleft > inright) {
        return null
      }
      val pivot = preorder(preleft)
      val root = new TreeNode(pivot)
      val pivotIndex = hash(pivot)
      root.left = recursive(preleft + 1, pivotIndex - inleft + preleft, inleft, pivotIndex - 1)
      root.right = recursive(pivotIndex - inleft + preleft + 1, preright, pivotIndex + 1, inright)
      root
    }

    recursive(0, prelen - 1, 0, inlen - 1)
  }
}
