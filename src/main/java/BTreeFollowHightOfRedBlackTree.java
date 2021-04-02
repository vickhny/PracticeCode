//Check if a given Binary Tree is height balanced like a Red-Black Tree
/*In a Red-Black Tree, the maximum height of a node is at most twice the minimum height (The four Red-Black tree properties make sure this is always followed). Given a Binary Search Tree, we need to check for following property.
        For every node, length of the longest leaf to node path has not more than twice the nodes on shortest path from node to leaf.

        12                                        40
        \                                     /    \
        14                                 10      100
        \                                        /  \
        16                                     60   150
        Cannot be a Red-Black Tree              It can be Red-Black Tree
        with any color assignment
        Max height of 12 is 1
        Min height of 12 is 3


        10
        /   \
        5     100
        /   \
        50   150
        /
        40
        It can also be Red-Black Tree*/
class BTreeFollowHightOfRedBlackTree {

    // Returns returns tree if the Binary
// tree is balanced like a Red-Black
// tree. This function also sets value
// in maxh and minh (passed by reference).
// maxh and minh are set as maximum and
// minimum heights of root.
    static boolean isBalancedUtil(Node root,
                                  INT maxh, INT minh) {

        // Base case
        if (root == null) {
            maxh.d = minh.d = 0;
            return true;
        }

        // To store max and min heights of left subtree
        INT lmxh = new INT(), lmnh = new INT();

        // To store max and min heights of right subtree
        INT rmxh = new INT(), rmnh = new INT();

        // Check if left subtree is balanced,
        // also set lmxh and lmnh
        if (isBalancedUtil(root.left, lmxh, lmnh) == false)
            return false;

        // Check if right subtree is balanced,
        // also set rmxh and rmnh
        if (isBalancedUtil(root.right, rmxh, rmnh) == false)
            return false;

        // Set the max and min heights
        // of this node for the parent call
        maxh.d = Math.max(lmxh.d, rmxh.d) + 1;
        minh.d = Math.min(lmnh.d, rmnh.d) + 1;

        // See if this node is balanced
        if (maxh.d <= 2 * minh.d)
            return true;

        return false;
    }

    // A wrapper over isBalancedUtil()
    static boolean isBalanced(Node root) {
        INT maxh = new INT(), minh = new INT();
        return isBalancedUtil(root, maxh, minh);
    }

    // Driver code
    public static void main(String args[]) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(100);
        root.right.left = new Node(50);
        root.right.right = new Node(150);
        root.right.left.left = new Node(40);
        System.out.println(isBalanced(root) ?
                "Balanced" : "Not Balanced");
    }

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            left = null;
            right = null;
            this.key = key;
        }
    }

    static class INT {
        static int d;

        INT() {
            d = 0;
        }
    }
}