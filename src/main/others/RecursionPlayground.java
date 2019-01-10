package others;

//理解递归
public class RecursionPlayground {
    /**
     * 求和递归
     *
     * @param arr
     * @return
     */
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    public static int sum(int[] arr, int begin) {
        if (begin == arr.length) {
            return 0;
        } else {
            return arr[begin] + sum(arr, begin + 1);
        }
    }

    public static void main(String[] args) {
        int[] testArr = new int[100];
        for (int i = 0; i < 100; i++) {
            testArr[i] = i + 1;
        }
        int result = RecursionPlayground.sum(testArr);
        System.out.println(result);
    }
}
