import java.util.LinkedList;
import java.util.Queue;

// A binary tree node
class DepthOrHightOfBinaryTree {
    Node root;

    // Driver program to test above functions
    public static void main(String args[]) {
        DepthOrHightOfBinaryTree tree = new DepthOrHightOfBinaryTree();

        // Let us create a binary tree shown in above diagram
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        System.out.println("Height of tree is " + tree.treeHeight(tree.root));
    }

    // Iterative method to find height of Binary Tree
    int treeHeight(Node node) {
        // Base Case
        if (node == null)
            return 0;

        // Create an empty queue for level order tarversal
        Queue<Node> q = new LinkedList();

        // Enqueue Root and initialize height
        q.add(node);
        int height = 0;

        while (1 == 1) {
            // nodeCount (queue size) indicates number of nodes
            // at current lelvel.
            int nodeCount = q.size();
            if (nodeCount == 0)
                return height;
            height++;

            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                Node newnode = q.remove();

                if (newnode.left != null)
                    q.add(newnode.left);
                if (newnode.right != null)
                    q.add(newnode.right);
                nodeCount--;
            }
        }
    }

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right;
        }
    }
}