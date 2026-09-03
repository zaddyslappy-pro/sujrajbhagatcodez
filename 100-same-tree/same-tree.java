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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base Case 1: Dono null hain -> Same hain
        if (p == null && q == null) {
            return true;
        }

        // Base Case 2: Ek null hai ya dono ki values match nahi karti
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Recursive Case: Left subtree aur Right subtree dono check karein
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}