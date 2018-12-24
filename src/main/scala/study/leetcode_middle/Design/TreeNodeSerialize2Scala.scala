package study.leetcode_middle.Design

object TreeNodeSerialize2Scala {
  /**
    * 深度优先
    * 递归
    *
    * @param str
    */
  def serialize(node: TreeNode): String = {
    val sb = new StringBuilder()
    sb.append("[")
    if (node != null) {
      sb.append(node.`val`)
      sb.append(",")
      sb.append(serialize(node.left))
      sb.append(",")
      sb.append(serialize(node.right))
    }
    sb.append("]")
    sb.toString
  }

  private var pos: Int = 0

  def parse(str: String): TreeNode = {
    if (str(pos) != '[')
      return null
    pos += 1
    if (str(pos) == ']') {
      pos += 1
      return null
    }
    var value = 0
    val negative = if (str(pos) == '-') true else false
    while (str(pos) >= '0' && str(pos) <= '9' && pos < str.length) {
      value = value * 10 + (str(pos) - '0')
      pos += 1
    }
    if (negative)
      value = -value
    val node = new TreeNode(value)
    if (str(pos) != ',') {
      return null
    }
    pos += 1
    node.left = parse(str)
    if (str(pos) != ',') {
      return null
    }
    pos += 1
    node.right = parse(str)
    if (str(pos) != ']')
      null
    pos += 1
    node
  }

  def deserialize(str: String): TreeNode = {
    pos = 0
    parse(str)
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
    val seri = new TreeNodeSerialize
    val str2 = seri.serialize(root)
    println(str2)
    val testNode = deserialize(str)
    println(serialize(testNode))
  }
}
