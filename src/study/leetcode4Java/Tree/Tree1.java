package study.leetcode4Java.Tree;

import study.leetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by root on 2018-6-14.
 */
public class Tree1 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            ++res;
            int n = q.size();
            for (int i = 0; i < n; ++i) {
                TreeNode t = q.poll();
                if (t.left() != null) q.offer(t.left());
                if (t.right() != null) q.offer(t.right());
            }
        }
        return res;
    }

}
