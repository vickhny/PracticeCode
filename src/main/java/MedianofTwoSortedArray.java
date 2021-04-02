import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MedianofTwoSortedArray {

    static List<Integer> list;

    public static void main(String[] args) {
        list = new LinkedList<>();
        int nums1[] = {1,1};
        int nums2[] = {1,2};
        MedianofTwoSortedArray m = new MedianofTwoSortedArray();
        System.out.println("Median- "+m.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        if (nums1.length > nums2.length) {
            addToArray(nums1, nums2, i);
            i = nums2.length;
            for (int j = i; j < nums1.length; j++) {
                list.add(nums1[j]);
            }
        } else {
            addToArray(nums2, nums1, i);
            i = nums1.length;
            for (int j = i; j < nums2.length; j++) {
                list.add(nums2[j]);
            }
        }

        Collections.sort(list);

        if (list.size() % 2 == 0) {
            return (double) (list.get(((list.size() / 2)-1)) + list.get((list.size() / 2))) / 2;
        }
        return (double) list.get((list.size() / 2));

    }

    private void addToArray(int[] nums1, int[] nums2, int i) {
        for (int j = i; j < nums2.length; j++) {
            list.add(nums1[j]);
            list.add(nums2[j]);
        }
    }

}
