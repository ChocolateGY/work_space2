package study.interview.prepare.Tree

/**
  * 二叉树的层次遍历
  * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
  *
  * 例如:
  * 给定二叉树: [3,9,20,null,null,15,7],
  *
  * 3
  * / \
  * 9  20
  * /  \
  * 15   7
  * 返回其层次遍历结果：
  *
  * [
  * [3],
  * [9,20],
  * [15,7]
  * ]
  *
  * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-ci-bian-li-by-leetcode/
  */
class LevelOrder {
  //迭代
  def levelOrder(root: TreeNode): List[List[Int]] = {
    import scala.collection.mutable._
    val list = ListBuffer[List[Int]]()

    if (root == null)
      return list.toList
    val queue = Queue[TreeNode]()
    queue.enqueue(root)
    while (queue.nonEmpty) {
      val subList = ListBuffer[Int]()
      for (i <- queue.indices) {
        val node = queue.dequeue()
        subList += node.value
        if (node.left != null) queue.enqueue(node.left)
        if (node.right != null) queue.enqueue(node.right)
      }
      list += subList.toList
    }
    list.toList
  }

  //递归
  def levelOrder2(root: TreeNode): List[List[Int]] = {
    import scala.collection.mutable._
    val list = ListBuffer[ListBuffer[Int]]()
    if (root == null)
      return List[List[Int]]()

    def recursive(node: TreeNode, level: Int): Unit = {
      if (list.length == level) {
        list += ListBuffer[Int]()
      }
      list(level) += node.value
      if (node.left != null)
        recursive(node.left, level + 1)
      if (node.right != null)
        recursive(node.right, level + 1)
    }

    recursive(root, 0)
    list.map(_.toList).toList
  }

  def levelOrder3(root: TreeNode): List[List[Int]] = {
    import scala.collection.mutable._
    val list = ListBuffer[List[Int]]()
    val queue = Queue[TreeNode]()
    queue.enqueue(root)
    while (queue.nonEmpty) {
      val subList = ListBuffer[Int]()
      for (i <- queue) {
        val node = queue.dequeue()
        subList += node.value
        if (node.left != null) queue.enqueue(node.left)
        if (node.right != null) queue.enqueue(node.right)
      }
      list += subList.toList
    }
    list.toList
  }
}
