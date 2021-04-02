import java.util.*;

/*Given a Binary Tree, the task is to print the leaf nodes from left to right. The nodes must be printed in the order they appear from left to right.

        Examples:

        Input :
        1
        /  \
        2    3
        / \  / \
        4  5  6  7

        Output :4 5 6 7*/

class PrintLeafNodsOfBTree
{

    // Binary tree node
    static class Node
    {
        Node left;
        Node right;
        int data;
    };

    // Function to create a new
// Binary node
    static Node newNode(int data)
    {
        Node temp = new Node();

        temp.data = data;
        temp.left = null;
        temp.right = null;

        return temp;
    }

    // Function to Print all the leaf nodes
// of Binary tree using two stacks
    static void PrintLeafLeftToRight(Node root)
    {
        // Stack to store all the nodes of tree
        Stack<Node> s1 = new Stack<>();

        // Stack to store all the leaf nodes
        Stack<Node> s2 = new Stack<>();;

        // Push the root node
        s1.push(root);

        while (!s1.empty())
        {
            Node curr = s1.peek();
            s1.pop();

            // If current node has a left child
            // push it onto the first stack
            if (curr.left!=null)
                s1.push(curr.left);

            // If current node has a right child
            // push it onto the first stack
            if (curr.right!=null)
                s1.push(curr.right);

                // If current node is a leaf node
                // push it onto the second stack
            else if (curr.left==null && curr.right==null)
                s2.push(curr);
        }

        // Print all the leaf nodes
        while (!s2.empty())
        {
            System.out.print(s2.peek().data +" ");
            s2.pop();
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.right.left = newNode(5);
        root.right.right = newNode(7);
        root.left.left.left = newNode(10);
        root.left.left.right = newNode(11);
        root.right.right.left = newNode(8);

        PrintLeafLeftToRight(root);
    }
}