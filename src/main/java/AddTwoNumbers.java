public class AddTwoNumbers {

    public static void main(String args[]) {

        int a[] = {2,4,3};
        int b[] = {5,6,4};

        ListNode l1 = new ListNode(2);

        ListNode l2 = new ListNode(5);

        for (int i = 1; i < a.length; i++) {
            addNode(l1, a[i]);
        }

        for (int i = 1; i < b.length; i++) {
            addNode(l2, b[i]);
        }

        ListNode node = addTwoNumbers(l1, l2);
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuilder s1 = new StringBuilder("");
        StringBuilder s2 = new StringBuilder("");

        if (l1 == null && l2 == null) {
            return new ListNode(0);
        } else if (l1 != null && l2 == null) {
            return l1;
        } else if (l1 == null && l2 != null) {
            return l2;
        }

        while (l1 != null || l2 != null) {

            if (l1 != null) {
                s1.append(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                s2.append(l2.val);
                l2 = l2.next;
            }
        }

        String sum = findSum(s1.reverse().toString(), s2.reverse().toString());

        ListNode listNode = null;
        for (int i = sum.length() - 1; i >= 0; i--) {
            if (listNode == null) {
                listNode = new ListNode(Integer.parseInt("" + sum.charAt(i)));
            } else {
                addNode(listNode, Integer.parseInt("" + sum.charAt(i)));
            }
        }


        return listNode;
    }

    static String findSum(String str1, String str2) {

        if (str1.length() > str2.length()) {
            String temp = str2;
            str2 = str1;
            str1 = temp;
        }
        //651+9951 = 10602
        //110
        // 20
        //------------
        //011
        //02
        //031
        //130
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();
        String sum = "";

        int carry = 0;

        for (int i = 0; i < str1.length(); i++) {

            int s = Integer.parseInt("" + str2.charAt(i)) + Integer.parseInt("" + str1.charAt(i)) + carry;
            sum = sum + s % 10;
            carry = s / 10;

        }

        for (int i = str1.length(); i < str2.length(); i++) {

            int s = Integer.parseInt("" + str2.charAt(i)) + carry;
            sum = sum + s % 10;
            carry = s / 10;
        }

        if (carry > 0) {
            sum = sum + carry;
        }

        return new StringBuilder(sum).reverse().toString();
    }

    static void addNode(ListNode node, int val) {

        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode(val);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
