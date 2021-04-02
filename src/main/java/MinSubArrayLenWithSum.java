public class MinSubArrayLenWithSum {

    /*    Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

        For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.*/
    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 2, 4, 3};
        int sum = 7;
        System.out.println(minSubArrayLen(sum, nums));

        int num2[] = {2, 3, -2, 4};
        System.out.println(maxProduct(num2));
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int i = 0;
        int j = 0;
        int sum = 0;

        int minLen = Integer.MAX_VALUE;

        while (j < nums.length) {
            if (sum < s) {
                sum += nums[j];
                j++;
            } else {
                minLen = Math.min(minLen, j - i);
                if (i == j - 1)
                    return 1;

                sum -= nums[i];
                i++;
            }

        }

        while (sum >= s) {
            minLen = Math.min(minLen, j - i);

            sum -= nums[i++];
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /*Find the contiguous subarray within an array (containing at least one number) which has the largest product.

    For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.*/
    public static int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }
}
