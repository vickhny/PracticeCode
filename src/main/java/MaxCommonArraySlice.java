import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*You are given a sequence of n integers a0, a1, . . . , an−1 and the task is to find the maximum slice of the array which contains no more than two different numbers.

        Input 1 :

        [1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 6, 2, 1, 8]

        Result 1 : Answer is 10 because the array slice of (0, 9) is the largest slice of the array with no more than two different numbers.

        There are 10 items in this slice which are "1, 1, 1, 2, 2, 2, 1, 1, 2, 2".
        2 different numbers for this slice are 1 and 2.*/

public class MaxCommonArraySlice {

    public static int maxCommonArraySlice(List<Integer> inputSequence) {
        if (inputSequence.size() < 2)
            return inputSequence.size(); // I'm doubting this line should be <= 2

        List<Integer> twoInts = new LinkedList<>();
        twoInts.add(inputSequence.get(0));
        int start = 0;
        int end = inputSequence.size();
        int count = 0;
        int max_length = 0;

        while (start < end) {
            if (twoInts.contains(inputSequence.get(start))) {
                count++;
                start++;
            } else if (twoInts.size() == 1) {
                twoInts.add(inputSequence.get(start));
            } else {  // twoInts.size() is 2
                count = 0;
                start--;
                twoInts.set(0, inputSequence.get(start));
                twoInts.set(1, inputSequence.get(start + 1));
            }

            if (count > max_length) {
                max_length = count;
            }
        }
        return max_length;

    }

    public static void main(String[] args) {

        List<Integer> input = new LinkedList<Integer>(Arrays.asList(53, 800, 0, 0, 0, 356, 8988, 1, 1));
        System.out.println(maxCommonArraySlice(input));

    }

}
