package study.leetcode_middle.Design

object TreeNodeSerializePre {

  def preOrder(str: StringBuilder, node: TreeNode) {
    if (node == null)
      str.append("# ")
    else {
      str.append(node.`val` + " ")
      preOrder(str, node.left)
      preOrder(str, node.right)
    }
  }

  def serialize(node: TreeNode): String = {
    val str = new StringBuilder()
    preOrder(str, node)
    str.toString
  }

  var index = 0

  def parse(arr: Array[String]): TreeNode = {
    if (arr(index) == "#") {
      index += 1
      null
    }
    else {
      val node = new TreeNode(arr(index).toInt)
      index += 1
      node.left = parse(arr)
      node.right = parse(arr)
      node
    }

  }

  def deserializ(str: String): TreeNode = {
    val arr = str.split(" ")
    index = 0
    parse(arr)
  }

  def main(args: Array[String]): Unit = {
    val root = new TreeNode(1)
    val node2 = new TreeNode(2)
    val node3 = new TreeNode(3)
    val node4 = new TreeNode(4)
    val node5 = new TreeNode(5)
    root.left = node2
    root.right = node3
    node3.left = node4
    node3.right = node5
    val str = serialize(root)
    println(str)
    val node = deserializ(str)
    val testS = serialize(node)
    println(testS)
  }
}
