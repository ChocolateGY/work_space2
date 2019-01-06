package study.DataStructure;

/**
 * 三角数字
 * 数据的第一项为1，第n项为n和n-1的和
 */
public class TriangleNum {
    public static int sum(int num) {
        int total = num;
        while (num > 0) {
            total = num - 1 + total;
            num--;
        }
        return total;
    }

    public static int sumRec(int num) {
        if (num == 1) {
            return 1;
        } else
            return num + sumRec(--num);
    }

    public static void main(String[] args) {
        System.out.println(sum(3));
        System.out.println(sumRec(3));
    }

}
