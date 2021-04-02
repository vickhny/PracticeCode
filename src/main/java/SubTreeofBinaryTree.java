import java.util.*;

class SubTreeofBinaryTree{

    // Structure of the
// binary tree node
    static class Node {
        int data;
        Node left;
        Node right;
    };

    // Function to create
// new node
    static Node newNode(int x)
    {
        Node temp = new Node();
        temp.data = x;
        temp.left = null;
        temp.right = null;
        return temp;
    }

    // Function to check if two trees
// have same pre-order traversal
    static boolean areTreeIdentical(Node t1, Node t2)
    {
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        Node temp1;
        Node temp2;
        s1.add(t1);
        s2.add(t2);

        // Loop to iterate over the stacks
        while (!s1.isEmpty() && !s2.isEmpty()) {
            temp1 = s1.peek();
            temp2 = s2.peek();
            s1.pop();
            s2.pop();

            // Both are None
            // hence they are equal
            if (temp1 == null &&
                    temp2 == null)
                continue;

            // nodes are uneuqal
            if ((temp1 == null && temp2 != null) ||
                    (temp1 != null && temp2 == null))
                return false;

            // nodes have unqual data
            if (temp1.data != temp2.data)
                return false;

            s1.add(temp1.right);
            s2.add(temp2.right);

            s1.add(temp1.left);
            s2.add(temp2.left);
        }

        // if both tree are identical
        // both stacks must be empty.
        if (s1.isEmpty() && s2.isEmpty())
            return true;
        else
            return false;
    }
    // Function to check if the Tree s
// is the subtree of the Tree T
    static boolean isSubTree(Node s, Node t)
    {
        // first we find the root of s in t
        // by traversing in pre order fashion
        Stack<Node> stk = new Stack<Node>();
        Node temp;
        stk.add(t);
        while (!stk.isEmpty()) {
            temp = stk.peek();
            stk.pop();

            // if current node data is equal
            // to root of s then
            if (temp.data == s.data) {
                if (areTreeIdentical(s, temp))
                    return true;
            }
            if (temp.right != null)
                stk.add(temp.right);
            if (temp.left != null)
                stk.add(temp.left);
        }
        return false;
    }

    // Driver Code
    public static void main(String[] args)
    {
    /*
            1
           / \
          2      3
         / \ / \
        4  5 6  7
    */
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.right = newNode(5);
        root.right.left = newNode(6);
        root.right.right = newNode(7);
    /*
         2
        / \
       4   5
    */

        Node root2 = newNode(2);
        root2.left = newNode(4);
        root2.right = newNode(5);
        if (isSubTree(root2, root))
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}