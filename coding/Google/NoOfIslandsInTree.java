package coding.Google;

import org.hamcrest.core.Is;

/**
 *
 * Given a tree having nodes with value 0 and 1. write a function to return the number of islands ?
 * An island of 1 is defined as a group of ones surrounded by zeroes or are at the boundary of the tree.
 *
 * Given the root of such a binary tree, return the number of islands of one.
 *
 * Example:
 *         0
 * 	     /   \
 * 	   1       0
 *   /  \     /  \
 * 1     1   1    1
 * This tree has 3 islands of 1
 *
 * how to solve for N-ary tree?
 *
 * If parent has 1, & all child are 1 then it is
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class NoOfIslandsInTree {


    public int IslandCounter(TreeNode node){
        if(node == null){
            return 0;
        }
        int islands=0; //note
        if(node.val == 1){
            islands++;
            dfs(node); // mark all connected 1s as 0
        }

        islands+= IslandCounter(node.left);
        islands+= IslandCounter(node.right);
        return islands;
    }

    public void dfs(TreeNode node){
        if(node == null){
            return;
        }
        if(node.val == 1){
            node.val = 0;
        }
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        // Create the example tree
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);

        NoOfIslandsInTree ctr = new NoOfIslandsInTree();
        System.out.println("Number of islands: " + ctr.IslandCounter(root));  // Output: 3

    }
}

