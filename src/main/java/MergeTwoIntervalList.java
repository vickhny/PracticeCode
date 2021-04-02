import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeTwoIntervalList {

    public static void main(String[] args) {

        /*List<Interval> l1 = new ArrayList<>();
        l1.add(new Interval(1, 5));
        l1.add(new Interval(10, 14));
        l1.add(new Interval(16, 18));
        l1.add(new Interval(20, 24));
        l1.add(new Interval(30, 38));

        List<Interval> l2 = new ArrayList<>();
        l2.add(new Interval(2, 6));
        l2.add(new Interval(8, 10));
        l2.add(new Interval(11, 20));*/

        List<Interval> l1 = new ArrayList<>();
        l1.add(new Interval(8, 9));
        l1.add(new Interval(10, 12));
        l1.add(new Interval(11, 14));
        l1.add(new Interval(20, 21));


        List<Interval> l2 = new ArrayList<>();
        l2.add(new Interval(7, 10));
        l2.add(new Interval(12, 14));
        l2.add(new Interval(14, 16));
        l2.add(new Interval(17, 20));

        Comparator<Interval> sortInterval = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        };

        getMergedInterval(l1, l2, sortInterval).forEach(interval -> System.out.print("[" + interval.start + "," + interval.end + "] "));

    }

    private static List<Interval> getMergedInterval(List<Interval> l1, List<Interval> l2, Comparator sortInterval) {

        if (l1 == null && l1.size() == 0)
            return l2;
        if (l2 == null && l2.size() == 0)
            return l1;

        List<Interval> mergedList = new ArrayList<>();
        mergedList.addAll(l1);
        mergedList.addAll(l2);

        Collections.sort(mergedList, sortInterval);

        int start = mergedList.get(0).start;
        int end = mergedList.get(0).end;

        int idx = 0;

        List<Interval> finalList = new LinkedList<>();

        while (idx < mergedList.size()) {

            if (start == mergedList.get(idx).start && end == mergedList.get(idx).end) {
                idx++;
                continue;
            }
            if (end < mergedList.get(idx).start) {
                finalList.add(new Interval(start, end));
                start = mergedList.get(idx).start;
                end = mergedList.get(idx).end;
                if (idx == mergedList.size() - 1) {
                    finalList.add(new Interval(start, end));
                }
            } else if (end < mergedList.get(idx).end) {
                end = mergedList.get(idx).end;
                if (idx == mergedList.size() - 1) {
                    finalList.add(new Interval(start, end));
                }
            }
            idx++;
        }
        return finalList;
    }

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
