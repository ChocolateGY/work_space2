package study.interview.prepare.BinarySearchTree

/**
  * Insert into a Binary Search Tree
  * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
  *
  * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
  *
  * 例如,
  *
  * 给定二叉搜索树:
  *
  * 4
  * / \
  * 2   7
  * / \
  * 1   3
  *
  * 和 插入的值: 5
  * 你可以返回这个二叉搜索树:
  *
  * 4
  * /   \
  * 2     7
  * / \   /
  * 1   3 5
  * 或者这个树也是有效的:
  *
  * 5
  * /   \
  * 2     7
  * / \
  * 1   3
  * \
  * 4
  */
object InsertIntoBST {
  //自己实现，迭代。
  def insertIntoBST(root: TreeNode, `val`: Int): TreeNode = {
    var cur = root
    val node = new TreeNode(`val`)
    if (cur != null) {
      while (cur.left != null || cur.right != null) {
        if (`val` > cur.value) {
          if (cur.right != null)
            cur = cur.right
          else {
            cur.right = node
            return root
          }
        } else {
          if (cur.left != null)
            cur = cur.left
          else {
            cur.left = node
            return root
          }
        }
      }
      if (`val` > cur.value)
        cur.right = node
      else
        cur.left = node
      root
    } else
      node
  }
  //递归
  def insertIntoBST2(root: TreeNode, `val`: Int): TreeNode = {
    if(root == null) return new TreeNode(`val`)
    if(`val`>root.value)
      root.right = insertIntoBST2(root.right,`val`)
    else
      root.left = insertIntoBST2(root.left,`val`)
    root
  }
}
