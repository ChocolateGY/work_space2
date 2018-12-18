package study.leetcode_middle.GraphAndTree

/**
  * Created by root on 2018-8-27.
  *
  *
  * 二叉搜索树中第K小的元素
  *
  * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
  * *
  * 说明：
  * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
  * *
  * 示例 1:
  * *
  * 输入: root = [3,1,4,null,2], k = 1
  * 3
  * /\
  * 1 4
  * \
  * 2
  * 输出: 1
  * 示例 2:
  * *
  * 输入: root =[5, 3, 6, 2, 4, null, null, 1], k = 3
  * 5
  * / \
  * 3 6
  * / \
  * 2 4
  * /
  * 1
  * 输出: 3
  * 进阶 ：
  * 如果二叉搜索树经常被修改 （ 插入 / 删除操作 ） 并且你需要频繁地查找第 k 小的值 ， 你将如何优化 kthSmallest 函数 ？
  */

object KthSmallest {
  /**
    * 那么这道题给的提示是让我们用BST的性质来解题，最重要的性质是就是左<根<右，那么如果用中序遍历所有的节点就会得到一个有序数组。
    * 所以解题的关键还是中序遍历啊。关于二叉树的中序遍历可以参见我之前的博客Binary Tree Inorder Traversal 二叉树的中序遍历，
    * 里面有很多种方法可以用，我们先来看一种非递归的方法，中序遍历最先遍历到的是最小的结点，那么我们只要用一个计数器，
    * 每遍历一个结点，计数器自增1，当计数器到达k时，返回当前结点值即可，参见代码如下：
    *
    * @param root
    * @param k
    * @return
    */
  def kthSmallest(root: TreeNode, k: Int): Int = {
    var i = 0
    val stack = collection.mutable.Stack[TreeNode]()
    var node = root
    while (stack.nonEmpty || node != null) {
      while (node != null) {
        stack.push(node)
        node = node.left
      }
      node = stack.pop()
      i += 1
      if (i == k) return node.value
      node = node.right
    }
    -1
  }

  /**
    * 递归方法
    * java 方法通过，scala方法报错
    *
    */
  var count = 0
  var temp: TreeNode = null

  def kthSmallest2(root: TreeNode, k: Int): Int = {
    kthSmallestDFS(root, k)
    temp.value
  }

  def kthSmallestDFS(root: TreeNode, k: Int): Unit = {
    if (root != null) {
      kthSmallestDFS(root.left, k)
      count += 1
      if (count == k) {
        temp = root
        return
      }
      kthSmallestDFS(root.right, k)
    }
  }
}

/** private TreeNode temp;
  * private int counter;
  * *
  * public int kthSmallest(TreeNode root, int k) {
  * inorder(root, k);
  * return temp.val;
  * }
  * *
  * public void inorder(TreeNode node, int k) {
  * // perform in order tree traversal
  * if (node == null)
  * return; // base case
  * inorder(node.left, k);as
  * if (++counter == k) {
  * temp = node;
  * return;
  * }
  * inorder(node.right, k);
  * }
  *
  */
