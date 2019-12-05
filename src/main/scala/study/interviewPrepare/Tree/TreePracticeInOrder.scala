package study.interviewPrepare.Tree

/**
  * 中序遍历二叉树
  * 给定一个二叉树，返回它的中序 遍历。
  *
  * 示例:
  *
  * 输入: [1,null,2,3]
  * 1
  * \
  * 2
  * /
  * 3
  *
  * 输出: [1,3,2]
  * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
  * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/
  */
object TreePracticeInOrder {
  def main(args: Array[String]): Unit = {

  }

  //递归
  def inorderTraversal(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()

    def recursive(node: TreeNode): Unit = {
      if (node != null) {
        recursive(node.left)
        list += node.value
        recursive(node.right)
      }
    }

    recursive(root)
    list.toList
  }

  //循环
  def inorderTraversal2(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()
    val stack = Stack[TreeNode]()
    var cur = root
    while (cur != null || stack.nonEmpty) {
      while (cur != null) {
        stack.push(cur)
        cur = cur.left
      }
      cur = stack.pop()
      list += cur.value
      cur = cur.right
    }
    list.toList
  }
}
