package study.leetcode_advanced.TreeAndGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class CountSmaller {

    /**
     * 输入：
     * [1,0,2]
     * 输出：
     * [0,0,0]
     * 预期：
     * [1,0,0]
     *
     * @param nums
     * @return
     */
    //自己尝试，有问题。逻辑问题
    public List<Integer> countSmaller(int[] nums) {
//        List<Integer> res = new LinkedList<Integer>();
        if (nums == null || nums.length == 0)
            return new LinkedList<>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    if (res[j] == 0 || nums[j] == 0)
                        count++;
                    else
                        count += res[j];

                }
            }
            res[i] = count;
        }
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < res.length; i++) {
            list.add(res[i]);
        }
        return list;
    }

    public static List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        int min = Integer.MAX_VALUE; // nums数组最小值，用来重置区间 eg: 5 6 0 2 1   6 7 1 3 2
        for (int value : nums) {
            if (value < min) {
                min = value;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - min + 1;
        }
        int max = Integer.MIN_VALUE;  // nums数组最大值  ,是树形数组的长度
        for (int value : nums) {
            if (value > max) {
                max = value;
            }
        }
        int[] BITree = new int[max + 1];  //树型数组从1开始计算
        BITree[0] = 0;
        int[] countArr = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int count = getSum(nums[i] - 1, BITree);   //nums[i] -1  比nums[i]小
            countArr[i] = count;
            update(nums[i], BITree); //比nums[i] 小
        }
        List<Integer> result = new ArrayList<>();
        for (int value : countArr) {
            result.add(value);
        }
        return result;
    }

    //背住
    public static int getSum(int value, int[] BITree) { // 获得a[i]从1，value的和
        int sum = 0;
        while (value > 0) {
            sum += BITree[value];
            value -= (value & -value);
        }
        return sum;
    }

    //背住
    public static void update(int value, int[] BITree) {
        while (value <= BITree.length - 1) {
            BITree[value] += 1;
            value += (value & -value); //获得根节点
        }
    }


    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        List<Integer> res = new CountSmaller().countSmaller3(nums);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }


    /**
     * 时间第二梯队，理解简单
     * 比较明显的一种思路是倒序遍历原数组，并将元素升序排列插到一个新数组，这样当元素第一次插入到新数组时的下标就是该元素右侧小于该元素的个数。
     * （第一次 是由于后续该位置可能被别的元素 抢占）。
     * <p>
     * 然而在这里暴力解决显然不合适，因此插入到新数组的过程我们采取二分搜索的方式。
     */
    public List<Integer> countSmaller3(int[] nums) {
        //排序数组
        List<Integer> temp = new ArrayList<>();
        //结果数组
        Integer[] res = new Integer[nums.length];

        //原数组从后向前遍历，根据二分法升序插入到新数组
        for (int i = nums.length - 1; i >= 0; i--) {
            int left = 0, right = temp.size();
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (temp.get(mid) >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            //新数组对应位置的下标即为原数组右侧小于该数的个数
            res[i] = left;
            temp.add(left, nums[i]);
        }
        return Arrays.asList(res);
    }
}
