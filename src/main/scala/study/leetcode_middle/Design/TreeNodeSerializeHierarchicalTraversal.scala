package study.leetcode_middle.Design

object TreeNodeSerializeHierarchicalTraversal {
  /**
    * 层次遍历
    *
    * @param node
    * @return
    */
  def serialize(node: TreeNode): String = {
    val sb = new StringBuilder()
    val queue = collection.mutable.Queue[TreeNode]()
    if (node == null)
      return null
    queue.enqueue(node)
    while (queue.nonEmpty) {
      val curNode = queue.dequeue()
      if (curNode != null) {
        sb.append(curNode.`val` + " ")
        queue.enqueue(curNode.left)
        queue.enqueue(curNode.right)
      } else
        sb.append("# ")
    }
    sb.toString().trim
  }

  def createNode(arr: Array[String], index: Int): TreeNode = {
    if (arr(index) == "#")
      null
    else
      new TreeNode(arr(index).toInt)
  }

  def deserialize(str: String): TreeNode = {
    if (str.isEmpty)
      return null
    var index = 0
    val arr = str.split(" ")
    val queue = collection.mutable.Queue[TreeNode]()
    val root = new TreeNode(arr(index).toInt)
    queue.enqueue(root)
    var curNode:TreeNode = null
    while (queue.nonEmpty) {
      curNode = queue.dequeue()
      index += 1
      curNode.left = createNode(arr, index)
      if (curNode.left != null)
        queue.enqueue(curNode.left)
      index += 1
      curNode.right = createNode(arr, index)
      if (curNode.right != null)
        queue.enqueue(curNode.right)
    }
    root
  }

  def main(args: Array[String]): Unit = {
    val root = new TreeNode(1)
    val node2 = new TreeNode(2)
    val node3 = new TreeNode(3)
    val node4 = new TreeNode(4)
    val node5 = new TreeNode(5)
    val node6 = new TreeNode(6)
    val node7 = new TreeNode(7)
    root.left = node2
    root.right = node3
    node2.left = node4
    node2.right = node5
    node3.left = node6
    node3.right = node7
    val str = serialize(root)
    println(str)
    val testNode = deserialize(str)
    val testStr = serialize(testNode)
    println(testStr)
  }
}
