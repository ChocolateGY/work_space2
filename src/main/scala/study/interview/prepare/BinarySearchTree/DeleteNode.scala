package study.interview.prepare.BinarySearchTree

/**
  * Delete Node in a BST
  * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
  *
  * 一般来说，删除节点可分为两个步骤：
  *
  * 首先找到需要删除的节点；
  * 如果找到了，删除它。
  * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
  *
  * 示例:
  *
  * root = [5,3,6,2,4,null,7]
  * key = 3
  *
  * 5
  * / \
  * 3   6
  * / \   \
  * 2   4   7
  *
  * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
  *
  * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
  *
  * 5
  * / \
  * 4   6
  * /     \
  * 2       7
  *
  * 另一个正确答案是 [5,2,6,null,4,null,7]。
  *
  * 5
  * / \
  * 2   6
  * \   \
  * 4   7
  */
object DeleteNode {
  //未完成
  def deleteNode(root: TreeNode, key: Int): TreeNode = {
    def serach(node: TreeNode, parent: TreeNode, num: Int): (TreeNode, TreeNode, Int) = {
      if (node == null)
        null
      else {
        if (node.value == key)
          (node, parent, num)
        else if (key < node.value)
          serach(node.left, node, 1)
        else
          serach(node.right, node, 2)
      }
    }

    val (node, parent, num) = serach(root, null, 1)
    if (node == null)
      null
    else {
      if (node.left == null && node.right == null) {
        if (node == root)
          return null
        if (num == 1)
          parent.left = null
        else
          parent.right = null

      } else if (node.right == null) {
        var pre = node.left
        val precopy = pre
        var preParent = pre
        while (pre.right != null) {
          preParent = pre
          pre = pre.right
        }
        preParent.right = null
        if (parent != null) {
          if (num == 1) {
            parent.left = pre
          } else
            parent.right = pre
        }
        pre.left = precopy
        if (parent == null)
          return pre
      } else if (node.left == null) {
        var post = node.right
        val postCopy = post
        var postParent = post
        while (post.left != null) {
          postParent = post
          post = post.left
        }
        postParent.left = null
        if (parent != null) {

          if (num == 1) {
            parent.left = post
          } else
            parent.right = post
        }
        post.right = postCopy
        if (parent == null)
          return post
      }
      root
    }
  }

  //后缀节点，使用右子树最小节点代替被删除节点
  //https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/yong-qian-qu-huo-zhe-hou-ji-jie-dian-zi-shu-dai-ti/
  def deleteNode2(root: TreeNode, key: Int): TreeNode = {
    //查找最小节点
    def minNode(node: TreeNode): TreeNode = {
      var n = node
      while (n.left != null)
        n = n.left
      n
    }

    //这个方法很关键，递归的方式删除掉最小节点
    def removeMin(node: TreeNode): TreeNode = {
      if (node.left == null) {
        val right = node.right
        node.right = null
        return right
      }
      node.left = removeMin(node.left)
      node
    }

    if (root == null)
      return null

    if (root.value < key) {
      root.right = deleteNode2(root.right, key)
      root
    } else if (root.value > key) {
      root.left = deleteNode2(root.left, key)
      root
    } else {
      if (root.left == null) {
        val right = root.right
        root.right = null
        return right
      }
      if (root.right == null) {
        val left = root.left
        root.left = null
        return left
      }
      //右子树最小的节点
      val temp = new TreeNode(minNode(root.right).value)
      temp.left = root.left
      temp.right = removeMin(root.right)
      root.left = null
      root.right = null
      temp
    }
  }
}
