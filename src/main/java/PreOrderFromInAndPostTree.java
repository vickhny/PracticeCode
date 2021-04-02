import java.util.Stack;

public class PreOrderFromInAndPostTree {

    static int postIndex;

    // Fills preorder traversal of tree with given
    // inorder and postorder traversals in a stack
    void fillPre(int[] in, int[] post, int inStrt,
                 int inEnd, Stack<Integer> s)
    {
        if (inStrt > inEnd)
            return;

        // Find index of next item in postorder traversal in
        // inorder.
        int val = post[postIndex];
        int inIndex = search(in, val);
        postIndex--;

        // traverse right tree
        fillPre(in, post, inIndex + 1, inEnd, s);

        // traverse left tree
        fillPre(in, post, inStrt, inIndex - 1, s);

        s.push(val);
    }

    // This function basically initializes postIndex
    // as last element index, then fills stack with
    // reverse preorder traversal using printPre
    void printPreMain(int[] in, int[] post)
    {
        int len = in.length;
        postIndex = len - 1;
        Stack<Integer> s = new Stack<Integer>();
        fillPre(in, post, 0, len - 1, s);
        while (s.empty() == false)
            System.out.print(s.pop() + " ");
    }

    // A utility function to search data in in[]
    int search(int[] in, int data)
    {
        int i = 0;
        for (i = 0; i < in.length; i++)
            if (in[i] == data)
                return i;
        return i;
    }

    // Driver code
    public static void main(String ars[])
    {
        int in[] = { 4, 10, 12, 15, 18, 22, 24, 25,
                31, 35, 44, 50, 66, 70, 90 };
        int post[] = { 4, 12, 10, 18, 24, 22, 15, 31,
                44, 35, 66, 90, 70, 50, 25 };
        PreOrderFromInAndPostTree tree = new PreOrderFromInAndPostTree();
        tree.printPreMain(in, post);
    }
}
