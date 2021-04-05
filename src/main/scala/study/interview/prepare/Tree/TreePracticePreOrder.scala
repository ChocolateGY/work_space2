package study.interview.prepare.Tree

object TreePracticePreOrder {
  def main(args: Array[String]): Unit = {

  }


  /**
    * 二叉树的前序遍历
    *
    * 给定一个二叉树，返回它的 前序 遍历。
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
    * 输出: [1,2,3]
    * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/
    * 还有一种空间复杂度O（1）的，没理解
    *
    * @param root
    * @return
    */
  def preorderTraversal(root: TreeNode): List[Int] = {
    val list = scala.collection.mutable.ListBuffer[Int]()

    def recursive(node: TreeNode): Unit = {
      if (node != null) {
        list += node.value
        recursive(node.left)
        recursive(node.right)
      }
    }

    recursive(root)
    list.toList
  }

  //递归
  def preorderTraversal2(root: TreeNode): List[Int] = {
    val list = scala.collection.mutable.ListBuffer[Int]()
    val stack = scala.collection.mutable.Stack[TreeNode]()
    if (root == null)
      return List[Int]()
    stack.push(root)
    while (stack.nonEmpty) {
      val node = stack.pop
      list += node.value
      if (node.right != null)
        stack.push(node.right)
      if (node.left != null)
        stack.push(node.left)
    }

    list.toList
  }

  //递归2
  def preorderTraversal22(root: TreeNode): List[Int] = {
    val list = scala.collection.mutable.ListBuffer[Int]()
    val stack = scala.collection.mutable.Stack[TreeNode]()
    var cur = root
    while (cur != null || stack.nonEmpty) {
      if (cur != null) {
        //遇到一个节点，加入list，入栈保存中间结果
        list += cur.value
        stack.push(cur)
        //遍历左子树
        cur = cur.left
      } else {
        //左子树走完，回到中间结果，遍历其右子树
        cur = stack.pop()
        cur = cur.right
      }
    }
    list.toList
  }

  /**
    * 错误的，不能用队列
    *
    * @param root
    * @return
    */
  def preorderTraversal3(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()
    val queue = Queue[TreeNode]()
    if (root == null)
      return List[Int]()
    queue.enqueue(root)
    while (queue.nonEmpty) {
      val node = queue.dequeue()
      list += node.value
      if (node.left != null)
        queue.enqueue(node.left)
      if (node.right != null)
        queue.enqueue(node.right)
    }
    list.toList
  }

  /**
    * 空间复杂度O（1）的，难理解
    * 方法基于 莫里斯的文章，可以优化空间复杂度。算法不会使用额外空间，只需要保存最终的输出结果。
    * 如果实时输出结果，那么空间复杂度是 O(1)。
    *
    * 算法
    *
    * 算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。
    *
    * 首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，
    * 直到到达一个叶子节点（当前节点的中序遍历前驱节点），
    * 所以我们更新输出并建立一条伪边 predecessor.right = root 更新这个前驱的下一个点。
    * 如果我们第二次访问到前驱节点，由于已经指向了当前节点，我们移除伪边并移动到下一个顶点。
    *
    * 如果第一步向左的移动不存在，就直接更新输出并向右移动。
    *
    *
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param root
    * @return
    */
  def preorderTraversal4(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()

    var node = root
    while (node != null) {
      if (node.left == null) {
        list += node.value
        node = node.right
      } else {
        var predecessor = node.left
        while ((predecessor.right != null) && predecessor.right != node) {
          predecessor = predecessor.right
        }
        if (predecessor.right == null) {
          list += node.value
          predecessor.right = node
          node = node.left
        } else {
          predecessor.right = null
          node = node.right
        }
      }
    }
    list.toList
  }

  //练习4
  def preorderTraversal5(root: TreeNode): List[Int] = {
    import scala.collection.mutable._
    val list = ListBuffer[Int]()
    var node = root
    while (node != null) {
      if (node.left == null) {
        list += node.value
        node = node.right
      } else {
        var predecessor = node.left
        while ((predecessor.right != null) && predecessor.right != node) {
          predecessor = predecessor.right
        }
        if (predecessor.right == null) {
          list += node.value
          predecessor.right = node
          node = node.left
        } else {
          predecessor.right = null
          node = node.right

        }
      }

    }
    list.toList
  }
}
