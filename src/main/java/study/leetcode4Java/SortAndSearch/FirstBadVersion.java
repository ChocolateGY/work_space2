package study.leetcode4Java.SortAndSearch;

/**
 * Created by root on 2018-6-28.
 */
public class FirstBadVersion extends VersionControl {

    /**
     * 递归，报栈溢出错误。
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        return find(1, n);
    }

    int find(int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;
            boolean flag = isBadVersion(mid);
            if (flag) {
                return find(low, mid - 1);
            } else
                return find(mid + 1, high);
        } else
            return low;
    }

    /**
     * 通过
     * @param n
     * @return
     */
    public int firstBadVersion2(int n) {
        int min = 1, max = n, mid = 0;
        while (min <= max) {
            mid = min + (max - min) / 2;
            if (isBadVersion(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    /**
     * 超时
     * @param n
     * @return
     */
    public int firstBadVersion3(int n) {
        int low = 1, high = n, mid = (low + high) / 2;
        while (low <= high) {
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else
                low = mid + 1;
            mid = (low + high) / 2; //问题在这一句吗，如果是mid = low + (high - low) / 2;则时间可以通过。难道是Int溢出？
        }
        return low;
    }
}
