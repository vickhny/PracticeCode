public class SortSingleLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(5);
        n1.next = new Node(8);
        n1.next.next = new Node(9);
        n1.next.next.next = new Node(13);
        n1.next.next.next.next = new Node(19);

        Node n2 = new Node(6);
        n2.next = new Node(7);
        n2.next.next = new Node(10);
        n2.next.next.next = new Node(15);

        print(sortedLinkedList(n1, n2));
    }

    private static void print(Node sortedLinkedList) {

        while (sortedLinkedList != null){
            System.out.print(sortedLinkedList.data+", ");
            sortedLinkedList = sortedLinkedList.next;
        }
    }

    private static Node sortedLinkedList(Node n1, Node n2) {

        Node n1Start = null;
        Node n2Start = null;
        Node cur = null;

        if (n1.data < n2.data) {
            n1Start = n1.next;
            n2Start = n2;
            cur = n1;
        } else {
            n1Start = n1;
            n2Start = n2.next;
            cur = n2;
        }

        Node start = cur;


        while (cur.next != null) {

            if (n1Start.data < n2Start.data) {
                cur.next = n1Start;
                n1Start = n1Start.next;
            } else {
                cur.next = n2Start;
                n2Start = n2Start.next;
            }
            cur = cur.next;
        }

        if (n1Start != null){
            cur.next = n1Start;
        }
        if (n2Start != null){
            cur.next = n2Start;
        }

        return start;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }
}
