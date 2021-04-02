public class FindIntersectionInSingleLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(5);
        n1.next = new Node(8);
        n1.next.next = new Node(9);
        n1.next.next.next = new Node(13);
        n1.next.next.next.next = new Node(23);
        n1.next.next.next.next.next = new Node(25);
        n1.next.next.next.next.next.next = new Node(27);

        Node n2 = new Node(6);
        n2.next = new Node(10);
        n2.next.next = new Node(13);
        n2.next.next.next = new Node(23);
        n2.next.next.next.next = new Node(25);
        n2.next.next.next.next.next = new Node(27);

        System.out.print(getIntersection(n1, n2));

    }

    private static int getIntersection(Node n1, Node n2) {
        int n1Size = size(n1);
        int n2Size = size(n2);

        if (n1Size > n2Size) {
            int count = n1Size - n2Size;
            while (count != 0) {
                n1 = n1.next;
                count--;
            }
        } else {
            int count = n2Size - n1Size;
            while (count != 0) {
                n2 = n2.next;
                count--;
            }
        }

        while (n1.data != n2.data) {
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1.data == n2.data ? n1.data : -1;
    }

    static int size(Node n) {
        int counter = 0;

        while (n != null) {
            counter++;
            n = n.next;
        }
        return counter;
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
