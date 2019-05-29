package study.leetcode_advanced.TreeAndGraph;

import study.DataStructure.Tree;

import java.util.Stack;

/**
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class LowestCommonAncestor {
    /**
     * 解题思路：
     * <p>
     * 递归搜索左右子树，如果左子树和右子树都不为空，说明最近父节点一定在根节点。
     * <p>
     * 反之，如果左子树为空，说明两个节点一定在右子树；
     * <p>
     * 同理如果右子树为空，说明两个节点一定在左子树。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果p,q刚好在左右两个子树上
        if (left != null && right != null) return root;
        //仅在右子树
        if (left == null) return right;
        //仅在左子树
        return left;
    }

    /**
     * 自己实现，使用栈来对比两节点的父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        Stack<TreeNode> ps = new Stack<>();
        Stack<TreeNode> qs = new Stack<>();

        find(ps, root, p);
        find(qs, root, q);
        TreeNode temp = root;
        TreeNode pNode = null;
        TreeNode qNode = null;
        while (!ps.isEmpty() && !qs.isEmpty() && pNode == qNode) {
            pNode = ps.pop();
            qNode = qs.pop();
            if (pNode == qNode)
                temp = pNode;
        }
        return temp;
    }

    public static void find(Stack<TreeNode> s, TreeNode root, TreeNode node) {
        if (root == null)
            return;
        if (root == node) {
            s.push(node);
            return;
        }
        if (!s.isEmpty() && s.firstElement() == node)
            return;
        find(s, root.left, node);
        find(s, root.right, node);
        if (!s.isEmpty()) {
            TreeNode previousNode = s.peek();
            if (previousNode == root.left || previousNode == root.right) {
                s.push(root);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n1 = new TreeNode(1);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n2.left = n7;
        n2.right = n4;
        n1.left = n0;
        n1.right = n8;

        TreeNode res = lowestCommonAncestor2(n3, n5, n4);
        System.out.println(res.val);
    }
}
