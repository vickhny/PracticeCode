import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {

/*    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.

    Java Solution
    When a room is taken, the room can not be used for anther meeting until the current meeting is over.
    As soon as the current meeting is finished, the room can be used for another meeting.
    We can sort the meetings by start timestamps and sequentially assign each meeting to a room.
    Each time when we assign a room for a meeting, we check if any meeting is finished so that the room can be reused.
    In order to efficiently track the earliest ending meeting, we can use a min heap.
    Whenever an old meeting ends before a new meeting starts, we reuse the room (i.e., do not add more room).
    Otherwise, we need an extra room (i.e., add a room).*/

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing((int[] itv) -> itv[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (int[] itv : intervals) {
            if (heap.isEmpty()) {
                count++;
                heap.offer(itv[1]);
            } else {
                if (itv[0] >= heap.peek()) {
                    heap.poll();
                } else {
                    count++;
                }

                heap.offer(itv[1]);
            }
        }

        return count;
    }

    /*    Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... ,
        determine if a person could attend all meetings.

        For example,
        Given [ [0, 30], [5, 10], [15, 20] ],
                return false.*/
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) {
                return false;
            }
        }

        return true;
    }
}
