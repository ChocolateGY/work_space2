package study.DataStructure;

/**
 * 栈
 * 后进先出
 */
public class MyStack2 {
    //栈头
    private int top;
    private int[] arr;
    private int size = 50;


    public MyStack2() {
        arr = new int[size];
        top = -1;
    }

    public MyStack2(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    //推入栈
    public void push(int elem) {
        arr[++top] = elem;
    }

    //推出栈
    public int pop() {
        return arr[top--];
    }

    //访问栈顶元素
    public int peek() {
        return arr[top];
    }

    //是否为空
    public boolean isEmpty() {
        return (top == -1);
    }

    public static void main(String[] args) {
        MyStack2 stack = new MyStack2(20);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
