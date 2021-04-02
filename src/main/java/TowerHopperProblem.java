/*Minimum number of jumps to reach end
        Difficulty Level : Medium
        Last Updated : 12 Nov, 2020

        Given an array of integers where each element represents the max number of steps that can be made forward from that element. Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, they cannot move through that element.

        Examples:

        Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
        Output: 3 (1-> 3 -> 8 -> 9)
        Explanation: Jump from 1st element
        to 2nd element as there is only 1 step,
        now there are three options 5, 8 or 9.
        If 8 or 9 is chosen then the end node 9
        can be reached. So 3 jumps are made.

        Input:  arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        Output: 10
        Explanation: In every step a jump
        is needed so the count of jumps is 10.*/

class TowerHopperProblem {

    // Driver code
    public static void main(String args[]) {
        int arr[] = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        int arrFalse[] = {3, 2, 1, 0, 4};
        int n = arrFalse.length;
        System.out.println("Minimum number of jumps to reach end is "
                + jump(arr));

        System.out.println("Can jump- "
                + canJump(arr));

        System.out.println("Can jump possible- "
                + jumpPossible(arr));
    }

    public TowerHopperProblem() {
        super();
    }

    public static boolean canJump(int[] A) {
        if (A.length <= 1)
            return true;

        int max = A[0]; //max stands for the largest index that can be reached.

        for (int i = 0; i < A.length; i++) {
            //if not enough to go to next
            if (max <= i && A[i] == 0)
                return false;

            //update max
            if (i + A[i] > max) {
                max = i + A[i];
            }

            //max is enough to reach the end
            if (max >= A.length - 1)
                return true;
        }

        return false;
    }

    //{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int lastReach = 0;
        int reach = 0;
        int step = 0;

        for (int i = 0; i <= reach && i < nums.length; i++) {
            //when last jump can not read current i, increase the step by 1
            if (i > lastReach) {
                step++;
                lastReach = reach;
            }
            //update the maximal jump
            reach = Math.max(reach, nums[i] + i);
        }

        if (reach < nums.length - 1)
            return 0;

        return step;
    }

    //{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
    public static int jumpPossible(int A[]) {

        if (A == null || A.length == 0) {
            return -1;
        }
        int maxJumptaken = 0;
        int step = 0;
        System.out.print(A[0] + "->");
        int i = 0;
        while (i < A.length) {
            if (A[i] == 0) {
                return -1;
            }
            if (i + A[i] >= A.length) {
                System.out.println(A[A.length - 1]);
                return ++step;
            }
            int low = i;
            int j = i + A[i];
            int index = 0;
            while (++low <= j) {
                int next = A[low];
                if (next != 0 && maxJumptaken < next) {
                    maxJumptaken = next;
                    index = low;
                }
                if (low == j && maxJumptaken == 0) {
                    return -1;
                }
            }
            System.out.print(A[index] + "->");
            maxJumptaken = 0;
            step++;
            i = index;
        }
        return -1;
    }
}