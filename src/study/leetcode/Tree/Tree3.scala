package study.leetcode.Tree

import scala.collection.mutable

/**
  * 对称二叉树
  * 给定一个二叉树，检查它是否是镜像对称的。
  *
  * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
  * 1
  * / \
  * 2   2
  * / \/ \
  * 3 4 4 3
  * 但是下面这个[1, 2, 2, null, 3, null, 3] 则不是镜像对称的:
  *
  * 1
  * / \
  * 2 2
  * \ \
  * 3 3
  * 说明:
  *
  * 如果你可以运用递归和迭代两种方法解决这个问题 ， 会很加分 。
  * Created by root on 2018 - 6 - 26.
  */

object Tree3 {


  /**
    * 递归方法
    *
    * @param root
    * @return
    */
  def isSymmetric(root: TreeNode): Boolean = {
    if (root == null)
      true
    else
      checkIsSymmetric(root.left, root.right)
  }

  def checkIsSymmetric(left: TreeNode, right: TreeNode): Boolean = {
    if (left == null && right == null) true
    else if ((left != null && right == null) || (left == null && right != null)) false
    else if (left.value != right.value) false
    else checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left)
  }

  /**
    * 非递归方法
    *
    * java的方法可以，scala的报空指针异常
    *
    * @param root
    * @return
    */
  def isSymmetric2(root: TreeNode): Boolean = {
    var flag = true
    if (root != null) {
      val s1 = new mutable.Stack[TreeNode]()
      val s2 = new mutable.Stack[TreeNode]()
      s1.push(root.left)
      s2.push(root.right)
      while (s1.nonEmpty && s2.nonEmpty && flag) {
        var n1 = s1.pop()
        var n2 = s2.pop()
        if (n1 == null && n2 == null) {

        }
        else {
          if (n1 == null || n2 == null)
            flag = false
          else if (n1.value != n2.value)
            flag = false
          s1.push(n1.left)
          s2.push(n2.right)
          s1.push(n1.right)
          s2.push(n2.left)
        }

      }

    }
    flag
  }


  /**
    * if (root == null)
    * return true;
    * Stack<TreeNode> s1 = new Stack<TreeNode>();
    * Stack<TreeNode> s2 = new Stack<TreeNode>();
    * s1.push(root.left);
    * s2.push(root.right);
    * while (!s1.empty() && !s2.empty()) {
    * TreeNode n1 = s1.pop();
    * TreeNode n2 = s2.pop();
    * if (n1 == null && n2 == null)
    * continue;
    * if (n1 == null || n2 == null)
    * return false;
    * if (n1.val != n2.val)
    * return false;
    * s1.push(n1.left);
    * s2.push(n2.right);
    * s1.push(n1.right);
    * s2.push(n2.left);
    * }
    * return true;
    *
    */

}
