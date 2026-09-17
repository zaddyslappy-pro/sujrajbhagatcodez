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
    private int maxSum = 0;

    // Structure returned by helper: [isBST (1 or 0), minVal, maxVal, sum]
    private int[] traverse(TreeNode root) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        // Check if current subtree forms a valid BST
        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            int currentSum = left[3] + right[3] + root.val;
            maxSum = Math.max(maxSum, currentSum);

            int currentMin = Math.min(root.val, left[1]);
            int currentMax = Math.max(root.val, right[2]);

            return new int[]{1, currentMin, currentMax, currentSum};
        }

        // Not a valid BST
        return new int[]{0, 0, 0, 0};
    }

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        traverse(root);
        return maxSum;
    }
}