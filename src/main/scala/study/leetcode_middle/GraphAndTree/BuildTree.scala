package study.leetcode_middle.GraphAndTree

/**
  * Created by root on 2018-8-24.
  *
  * 从前序与中序遍历序列构造二叉树
  * 根据一棵树的前序遍历与中序遍历构造二叉树。
  * *
  * 注意:
  * 你可以假设树中没有重复的元素。
  * *
  * 例如，给出
  * *
  * 前序遍历 preorder = [3,9,20,15,7]
  * 中序遍历 inorder = [9,3,15,20,7]
  * 返回如下的二叉树：
  * *
  * 3
  * /\
  * 9 20
  * / \
  * 15 7
  */

object BuildTree {
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    if (preorder.nonEmpty && inorder.nonEmpty) {
      buildCore(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1)
    } else
      null
  }

  def buildCore(preorder: Array[Int], preSt: Int, preEnd: Int, inorder: Array[Int], inSt: Int, inEnd: Int): TreeNode = {
    //前序遍历第一个节点是根节点
    val rootValue = preorder(preSt)
    val root = new TreeNode(rootValue)

    //前序序列只有根节点
    if (preSt == preEnd)
      root

    //遍历中序序列，找到根节点的位置
    var rootInorder = inSt
    while (inorder(rootInorder) != rootValue && rootInorder <= inEnd) {
      rootInorder += 1
    }

    //左子树的长度
    var leftLength = rootInorder - inSt
    //前序序列中左子树的最后一个节点
    var leftPreEnd = preSt + leftLength


    //左子树非空
    if (leftLength > 0)
    //重建左子树
      root.left = buildCore(preorder, preSt + 1, leftPreEnd, inorder, inSt, inEnd)
    //右子树非空
    if (leftLength < preEnd - preSt)
    //重建右子树
      root.right = buildCore(preorder, leftPreEnd + 1, preEnd, inorder, rootInorder + 1, inEnd)
    root

  }


  /**
    * java.lang.StackOverflowError
    *
    * @param preorder
    * @param inorder
    * @return
    */
  def buildTree2(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    if (inorder.isEmpty)
      null
    else {
      val root = new TreeNode(preorder(0))
      val x = inorder.indexOf(root.value)
      root.left = buildTree2(preorder.slice(1, x + 1), inorder.slice(0, x))
      root.right = buildTree2(preorder.slice(x + 1, preorder.length), inorder.slice(x + 1, inorder.length))
      root
    }

  }
}
