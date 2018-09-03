package study.leetcode_primary.Others;

/**
 * Created by root on 2018-8-7.
 *
 *
 * 颠倒二进制位
 颠倒给定的 32 位无符号整数的二进制位。

 示例:

 输入: 43261596
 输出: 964176192
 解释: 43261596 的二进制表示形式为 00000010100101000001111010011100 ，
 返回 964176192，其二进制表示形式为 00111001011110000010100101000000 。
 进阶:
 如果多次调用这个函数，你将如何优化你的算法？
 */
public class ReverseBits {
    /**
     * 把n最后1位和1相与，并相加给result
     * n向右无符号移1位，result向左移1位
     *
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;
            if (i < 31) {
                result <<= 1;
            }
            System.out.println("i: " + i + "result:" + Integer.toBinaryString(result) + "n: " + Integer.toBinaryString(n));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.print(Integer.toBinaryString(new ReverseBits().reverseBits(7)));
    }
}
