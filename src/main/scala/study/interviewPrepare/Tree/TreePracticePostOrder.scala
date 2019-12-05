package study.interviewPrepare.Tree

object TreePracticePostPrder {
  def main(args: Array[String]): Unit = {

  }

  //递归
  def postorderTraversal(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()

    def recursive(node: TreeNode): Unit = {
      if (node != null) {
        recursive(node.left)
        recursive(node.right)
        list += node.value
      }
    }

    recursive(root)
    list.toList
  }

  /**
    * 循环
    * 接下来我们思考一下前序遍历和后序遍历之间的关系：
    *
    * 前序遍历顺序为：根 -> 左 -> 右
    *
    * 后序遍历顺序为：左 -> 右 -> 根
    *
    * 如果1： 我们将前序遍历中节点插入结果链表尾部的逻辑，修改为将节点插入结果链表的头部
    *
    * 那么结果链表就变为了：右 -> 左 -> 根
    *
    * 如果2： 我们将遍历的顺序由从左到右修改为从右到左，配合如果1
    *
    * 那么结果链表就变为了：左 -> 右 -> 根
    *
    * 这刚好是后序遍历的顺序
    *
    * 基于这两个思路，我们想一下如何处理：
    *
    * 修改前序遍历代码中，节点写入结果链表的代码，将插入队尾修改为插入队首
    *
    * 修改前序遍历代码中，每次先查看左节点再查看右节点的逻辑，变为先查看右节点再查看左节点
    *
    * 作者：18211010139
    * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/die-dai-jie-fa-shi-jian-fu-za-du-onkong-jian-fu-za/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    */

  def postorderTraversal2(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()
    val stack = Stack[TreeNode]()
    if (root == null)
      return List[Int]()
    stack.push(root)
    while (stack.nonEmpty) {
      val node = stack.pop
      list.+=:(node.value)
      if (node.left != null) {
        stack.push(node.left)
      }
      if (node.right != null)
        stack.push(node.right)

    }
    list.toList
  }

  def postorderTraversal22(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()
    val stack = Stack[TreeNode]()
    var cur = root
    while (cur != null || stack.nonEmpty) {
      if (cur != null) {
        //将结果放入链表头
        list.+=:(cur.value)
        stack.push(cur)
        //先遍历右节点
        cur = cur.right
      } else {
        //遍历左节点
        cur = stack.pop()
        cur = cur.left
      }
    }
    list.toList
  }
}
