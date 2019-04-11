package study.leetcode_advanced.ArrayAndString;

public class MaxSlidingWindow_Java {

    /**
     * 速度最快的方法
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[]{};
        }

        int[] result = new int[nums.length - k + 1];
        int maxI = -1;
        //result[0] = nums[maxI];

        for (int i = 0, j = k - 1 + i; i < result.length; i++, j++) {
            // 如果之前的最大元素还在窗口内，且新元素不比它大，则最大元素下标不变
            if (maxI >= i && maxI <= j) {
                if (nums[j] > nums[maxI]) {
                    maxI = j;
                }
            } else {
                // 之前的最大元素不在窗口内，重新计算当前窗口的最大元素
                maxI = getMaxIndex(nums, i, j);
            }
            result[i] = nums[maxI];
        }

        return result;
    }

    public static int getMaxIndex(int[] arr, int i, int j) {
        int maxIndex = i;

        for (int m = 0, k = i + 1; m < j - i; k++, m++) {
            if (arr[k] > arr[maxIndex]) {
                maxIndex = k;
            }
        }

        return maxIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};

        int[] res = maxSlidingWindow(nums, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }
}
