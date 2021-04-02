import java.util.LinkedList;
import java.util.Queue;

/*A program to check if a binary tree is BST or not*/
public class CheckValidBST {

    public static void main(String[] args) {
        //false
        /*BstNode root = new BstNode(3);
        // left sub tree
        BstNode node_2 = new BstNode(2); root.setLeft(node_2);
        BstNode node_1 = new BstNode(1); node_2.setLeft(node_1);
        BstNode node_4 = new BstNode(4); node_2.setRight(node_4);
        // right sub tree
        BstNode node_6 = new BstNode(6); root.setRight(node_6);
        BstNode node_5 = new BstNode(5); node_6.setLeft(node_5);
        BstNode node_7 = new BstNode(7); node_6.setRight(node_7);*/

        //true
        BstNode root = new BstNode(8);
        // left sub tree
        BstNode node_3 = new BstNode(3); root.setLeft(node_3);
        BstNode node_1 = new BstNode(1); node_3.setLeft(node_1);
        BstNode node_6 = new BstNode(6); node_3.setRight(node_6);
        // right sub tree
        BstNode node_10 = new BstNode(10); root.setRight(node_10);
        BstNode node_14 = new BstNode(14); node_10.setRight(node_14);

        CheckValidBST ibsTree = new CheckValidBST();
        System.out.println(ibsTree.isBinarySearchTree(root));
    }

    public static class BstNode {

        private BstNode left;
        private BstNode right;
        private Integer data;

        public BstNode(Integer data) {
            this.data = data;
        }

        public BstNode getLeft() {
            return left;
        }
        public void setLeft(BstNode left) {
            this.left = left;
        }
        public BstNode getRight() {
            return right;
        }
        public void setRight(BstNode right) {
            this.right = right;
        }

        public Integer getData() {
            return data;
        }
    }

    public boolean isBinarySearchTree(BstNode root) {

        if(root == null) return Boolean.TRUE;
        return isBstValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBstValid(BstNode root, Integer minValue, Integer maxValue) {

        if(root == null) return Boolean.TRUE;
        if(root.getData() >= minValue && root.getData() < maxValue
                && isBstValid(root.getLeft(), minValue, root.getData())
                && isBstValid(root.getRight(), root.getData(), maxValue)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    static void insert(BstNode temp, int key)
    {
        BstNode root;
        if (temp == null) {
            root = new BstNode(key);
            return;
        }
        Queue<BstNode> q = new LinkedList<BstNode>();
        q.add(temp);

        // Do level order traversal until we find
        // an empty place.
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left == null) {
                temp.left = new BstNode(key);
                break;
            }
            else
                q.add(temp.left);

            if (temp.right == null) {
                temp.right = new BstNode(key);
                break;
            }
            else
                q.add(temp.right);
        }
    }
}
