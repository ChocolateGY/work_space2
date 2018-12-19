package study.leetcode_middle.Design;


import sun.reflect.generics.tree.Tree;

import java.util.Scanner;

/**
 * Created by root on 2018-9-13.
 * <p>
 * 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例:
 * <p>
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
public class TreeNodeSerialize {

    /**
     * 深度优先+递归
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[" + root.val + ",");
        sb.append(serialize(root.left));
        sb.append(",");
        sb.append(serialize(root.right));
        sb.append("]");
        return sb.toString();
    }

    private int pos;

    private TreeNode parse(String data) {
        if (data.charAt(pos) != '[')
            return null;
        pos++;
        if (data.charAt(pos) == ']') {
            pos++;
            return null;
        }
        int val = 0;
        boolean negative = false;
        if (data.charAt(pos) == '-') {
            negative = true;
            pos++;
        }
        for (; pos < data.length() && data.charAt(pos) >= '0' && data.charAt(pos) <= '9'; pos++) {
            val = val * 10 + data.charAt(pos) - '0';
        }
        if (negative)
            val = -val;
        TreeNode node = new TreeNode(val);
        if (data.charAt(pos) != ',')
            return null;
        pos++;
        node.left = parse(data);
        if (data.charAt(pos) != ',')
            return null;
        pos++;
        node.right = parse(data);
        if (data.charAt(pos) != ']')
            return null;
        pos++;
        return node;

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        pos = 0;
        return parse(data);
    }

    public static void main(String[] args) {
        TreeNodeSerialize codec = new TreeNodeSerialize();
        TreeNode root1 = codec.create();
        TreeNode root2 = codec.deserialize(codec.serialize(root1));
        codec.serialize(root2);

    }
    public TreeNode create() {
        Scanner sc = new Scanner(System.in);
        TreeNode root = null;
        int val = 0;
        String str = sc.next();
        if (str.equals("#"))
            root = null;
        else {
            val = Integer.parseInt(str);
            root = new TreeNode(val);
            root.left = create();
            root.right = create();
        }
        return root;
    }

}
