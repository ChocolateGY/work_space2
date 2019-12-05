package study.interviewPrepare.Tree


/**
  * 对称二叉树
  * 给定一个二叉树，检查它是否是镜像对称的。
  *
  * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
  *
  * 1
  * / \
  * 2   2
  * / \ / \
  * 3  4 4  3
  * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
  *
  * 1
  * / \
  * 2   2
  * \   \
  * 3    3
  * 说明:
  *
  * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
  *
  * https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode/
  */
object IsSymmetric {
  //递归
  def isSymmetric(root: TreeNode): Boolean = {

    def recursive(left: TreeNode, right: TreeNode): Boolean = {

      if (left == null && right == null) true
      else if ((left != null && right == null) || (left == null && right != null)) false
      else if (left.value != right.value) false
      else recursive(left.left, right.right) && recursive(left.right, right.left)
    }

    if (root == null)
      true
    else
      recursive(root.left, root.right)
  }

  /**
    * 迭代
    * 除了递归的方法外，我们也可以利用队列进行迭代。队列中每两个连续的结点应该是相等的，
    *
    * 而且它们的子树互为镜像。最初，队列中包含的是 root 以及 root。该算法的工作原理类似于 BFS，
    *
    * 但存在一些关键差异。每次提取两个结点并比较它们的值。然后，将两个结点的左右子结点按相反的顺序插入队列中。
    *
    * 当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
    *
    * 作者：LeetCode
    * 链接：https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode/
    * 来源：力扣（LeetCode）
    * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    *
    * @param root
    * @return
    */
  def isSymmetric2(root: TreeNode): Boolean = {
    import scala.collection.mutable._
    val q = Queue[TreeNode]()
    q.enqueue(root)
    q.enqueue(root)
    while (q.nonEmpty) {
      val t1 = q.dequeue()
      val t2 = q.dequeue()
      if (t1 != null || t2 != null) {
        if ((t1 != null && t2 == null) || (t1 == null && t2 != null)) return false
        if (t1.value != t2.value) return false
        q.enqueue(t1.left)
        q.enqueue(t2.right)
        q.enqueue(t1.right)
        q.enqueue(t2.left)
      }
    }
    true
  }
}
