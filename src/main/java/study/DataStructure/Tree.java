package study.DataStructure;

/**
 * 二叉树
 */
public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //插入
    public void insert(int key, int data) {
        Node n = new Node(key, data);
        if (root == null)
            root = n;
        else {
            Node cur = root;
            while (cur != null) {
                if (key > cur.getKey()) {
                    if (cur.getRight() == null) {
                        cur.setRight(n);
                        return;
                    } else
                        cur = cur.getRight();
                } else {
                    if (cur.getLeft() == null) {
                        cur.setLeft(n);
                        return;
                    } else
                        cur = cur.getLeft();

                }
            }
        }
    }

    //查找
    public Node find(int data) {
        Node cur = root;
        while (cur.getData() != data) {
            if (data > cur.getData()) {
                cur = cur.getRight();
            } else
                cur = cur.getLeft();

            if (cur == null)
                return null;
        }
        return cur;
    }
    //修改
    public boolean change(int oldData,int newData){
        Node n = find(oldData);
        if(n==null)
            return false;
        else{
            n.setData(newData);
            return true;
        }
    }

    /**
     * 先序遍历
     * @param n
     */
    public void preOrder(Node n) {
        if (n != null) {
            System.out.println(n);
            preOrder(n.getLeft());
            preOrder(n.getRight());
        }
    }
    //中序遍历

    public void inOrder(Node n) {
        if (n != null) {
            inOrder(n.getLeft());
            System.out.println(n);
            inOrder(n.getRight());
        }
    }
//后序遍历
    public void endOrder(Node n) {
        if (n != null) {
            endOrder(n.getLeft());
            endOrder(n.getRight());
            System.out.println(n);
        }
    }
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(1, 1);
        tree.insert(2, 2);
        tree.insert(3, 3);
        tree.insert(4, 4);
        tree.inOrder(tree.getRoot());
        Node fn = tree.find(3);
        System.out.println(fn);
        tree.change(3,30);
        System.out.println();
        tree.preOrder(tree.getRoot());
        System.out.println();
        tree.inOrder(tree.getRoot());
        System.out.println();
        tree.endOrder(tree.getRoot());
    }
}
