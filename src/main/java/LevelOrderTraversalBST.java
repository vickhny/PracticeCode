import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalBST {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(12);
        root.left.left = new Node(4);
        root.left.right = new Node(7);

        System.out.println("LevelOrder: ");
        levelOrderTraversal(root);
        System.out.println();
        System.out.println("PreOrder: ");
        preOrder(root);
        System.out.println();
        System.out.println("PostOrder: ");
        postOrder(root);
        System.out.println();
        System.out.println("InOrder: ");
        inOrder(root);


    }

    private static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            if (root.left != null)
                preOrder(root.left);
            if (root.right != null)
                preOrder(root.right);
        }
    }

    private static void postOrder(Node root) {
        if (root != null) {
            if (root.left != null)
                postOrder(root.left);
            if (root.right != null)
                postOrder(root.right);

            System.out.print(root.data + " ");
        }
    }

    private static void inOrder(Node root) {
        if (root != null) {
            if (root.left != null)
                inOrder(root.left);
            System.out.print(root.data + " ");
            if (root.right != null)
                inOrder(root.right);
        }
    }

    private static void levelOrderTraversal(Node root) {

        Queue<Node> list = new LinkedList();
        list.add(root);

        while (!list.isEmpty()) {
            Node current = list.poll();

            if (current != null) {
                System.out.print(current.data + " ");
            }
            if (current.left != null) {
                list.add(current.left);
            }
            if (current.right != null) {
                list.add(current.right);
            }
        }

    }

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }


}
