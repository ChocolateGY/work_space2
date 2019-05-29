package study.leetcode_advanced.TreeAndGraph;

/**
 * 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出: 42
 */
public class MaxPathSum {
    public int max = Integer.MIN_VALUE;



    /**
     * 正确最优解
     * @param root
     * @return
     */
    public int maxPathSum2(TreeNode root) {
        if (root == null)
            return 0;
        maxSum(root);

        return max;

    }

    public int maxSum(TreeNode root) {
        if (root == null) return 0;
        int leftValue = maxSum(root.left);
        int rightValue = maxSum(root.right);

        int curMax = root.val;
        if (leftValue > 0)
            curMax += leftValue;
        if (rightValue > 0)
            curMax += rightValue;
        if (curMax > max)
            max = curMax;

        return Math.max(root.val, Math.max(root.val + leftValue, root.val + rightValue));

    }

    public static void main(String arg[]) {
        TreeNode r1 = new TreeNode(10);
        TreeNode r2 = new TreeNode(-9);
        TreeNode r3 = new TreeNode(20);
        TreeNode r4 = new TreeNode(-25);
        TreeNode r5 = new TreeNode(7);
        TreeNode r6 = new TreeNode(14);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r3.right = r5;
        r3.left = r6;
        int maxnum = new MaxPathSum().maxPathSum2(r1);
        System.out.print(maxnum);
    }


}
