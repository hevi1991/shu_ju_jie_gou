package others;

/**
 * 时间复杂度简单说明
 * 1. 忽略常数
 * 2. n趋于无穷的情况
 * 3. 通常考虑最坏的情况
 */
public class TimeComplexity {

    /*
     * 这个函数的算法时间复杂度是O(n)
     * 忽略常数
     * n是nums的元素个数
     */
    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
