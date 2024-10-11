package com.Uber;

/**
 * https://leetcode.com/discuss/interview-question/5043232/Uber-Phone-Screen
 * Given a binary search tree (BST) find the kth largest element.
 * Example:
 *          10
 *        /       \
 *     4           20
 *   /             /    \
 * 2           15        40
 * k = 2
 *
 *
 * output: 20
 *
 *
 * Method signature:
 * public int kthLargest(TreeNode root, int k) {}
 * To find the k-th largest element in a binary tree, one efficient approach is to perform a reverse in-order traversal (right-root-left traversal)
 * ~= LC: Kth smallest element in BST
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
public class KthLargestInTree {
    // Global counter and result variable
    static int count = 0;
    private static int result = Integer.MIN_VALUE;

    public int findKthLargest(TreeNode root, int k) {
        count = k;
        revInOrder(root);
        return result;
    }

    public void revInOrder(TreeNode root){
        if(root == null){
            return;
        }

        revInOrder(root.right);

        count--;
        if(count == 0){
            result = root.val;
            return;
        }

        revInOrder(root.left);
    }

    public static void main(String[] args) {

        KthLargestInTree kthLargestInTree = new KthLargestInTree();

        // Example binary tree
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(15);
        root.right = new TreeNode(25);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(18);
        root.right.right = new TreeNode(30);

        int k = 3; // Find 3rd largest element
        System.out.println("3rd largest element is: " + kthLargestInTree.findKthLargest(root, k));
    }
}
//TC : O(n), SC : O(1)
