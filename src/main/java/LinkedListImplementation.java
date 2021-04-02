import java.security.InvalidAlgorithmParameterException;
import java.util.NoSuchElementException;

class LinkNode<T> {

    T data;
    LinkNode next;

    // Constructor
    LinkNode(T d) {
        data = d;
        next = null;
    }
}

class MyLinkList<T> {

    LinkNode head;
    int size;

    MyLinkList() {
        this.size = 0;
    }

    public LinkNode findMiddleNode() throws InvalidAlgorithmParameterException {
        LinkNode current = head;

        if (current == null) {
            throw new InvalidAlgorithmParameterException();
        }

        LinkNode n1 = current;
        LinkNode n2 = current.next;
        try {
            while (n1 != null && n2 != null) {
                n1 = n1.next;
                n2 = n2.next.next;
            }
        } catch (Exception e) {

        }
        return n1;
    }

    public boolean add(T t) {
        size++;
        if (head == null) {
            head = new LinkNode(t);
            return true;
        }
        LinkNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new LinkNode(t);
        return true;
    }

    public boolean addFirst(T t) {
        size++;
        if (head == null) {
            head = new LinkNode(t);
            return true;
        }
        LinkNode cur = new LinkNode(t);
        cur.next = head;
        head = cur;
        return true;
    }

    public T remove() {
        if (head != null) {
            return removeFirst();
        }
        throw new NoSuchElementException();
    }

    public T remove(int index) {

        int curIndex = 0;
        LinkNode prev = null;
        LinkNode remove = null;
        LinkNode cur = head;

        if (head != null) {

            if (index == 0) {
                head = cur.next;
                size--;
                return (T) cur.data;
            }

            while (cur.next != null) {
                prev = cur;
                if (curIndex == index) {
                    remove = cur;
                    break;
                }
                curIndex++;
                cur = cur.next;
            }

            if (remove != null) {
                prev.next = cur.next.next;
                size--;
                return (T) remove.data;
            }

        }
        throw new NoSuchElementException();
    }

    public T remove(T t) {
        LinkNode cur = null;
        LinkNode prev = null;
        LinkNode next = null;
        LinkNode removeNode = null;

        if (head != null) {
            cur = head;
            if (head.data.equals(t)) {
                next = head.next;
                head = next;
                size--;
                return (T) cur.data;
            }


            while (cur.next != null) {
                prev = cur;
                cur = cur.next;
                if (cur.data.equals(t)) {
                    removeNode = cur;
                    prev.next = cur.next;
                    size--;
                    return (T) removeNode.data;
                }
            }

        }
        throw new NoSuchElementException();
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        LinkNode cur = null;
        if (head != null) {
            cur = head;
            head = cur.next;
            size--;
            return (T) cur.data;
        }
        throw new NoSuchElementException();
    }

}

public class LinkedListImplementation {

    public static void main(String[] args) throws InvalidAlgorithmParameterException {
        MyLinkList linkList = new MyLinkList();
        linkList.add(10);
        linkList.add(20);
        linkList.add(30);
        linkList.add(40);
        //linkList.add(50);

        System.out.println(linkList.size());

        System.out.println(linkList.findMiddleNode().data);

        while (linkList.size() != 0) {
            System.out.println(linkList.remove());
        }

    }

}
