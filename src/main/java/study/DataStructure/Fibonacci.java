package study.DataStructure;

/**
 * 斐波那契数列
 * 数列中的第1，2项为1，第n项由n-1加n-2组成
 */
public class Fibonacci {
    public static int recursive(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return recursive(n - 1) + recursive(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(recursive(1));
        System.out.println(recursive(2));
        System.out.println(recursive(3));
        System.out.println(recursive(4));
    }
}
