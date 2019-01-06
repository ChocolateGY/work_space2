package study.DataStructure;

/**
 * 递归
 */
public class Recursive {
    public static void test(int i) {
        if(i<100){
            System.out.println("hello java"+i);
            test(++i);
            System.out.println("hello world"+i);
        }
    }

    public static void main(String[] args) {
        test(0);

    }
}
