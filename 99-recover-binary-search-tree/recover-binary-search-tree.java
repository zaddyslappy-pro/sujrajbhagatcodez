/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode first = null;
    private TreeNode middle = null;
    private TreeNode last = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        // Perform in-order traversal to identify swapped nodes
        inorder(root);

        // Swap values back to correct the BST structure
        if (first != null && last != null) {
            // Case 1: Swapped nodes are non-adjacent
            int temp = first.val;
            first.val = last.val;
            last.val = temp;
        } else if (first != null && middle != null) {
            // Case 2: Swapped nodes are adjacent
            int temp = first.val;
            first.val = middle.val;
            middle.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        // Check for BST order violation
        if (prev != null && root.val < prev.val) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;

        inorder(root.right);
    }
}