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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // Base case 1: Agar dono nodes null hain, toh mirror hain
        if (t1 == null && t2 == null) {
            return true;
        }
        
        // Base case 2: Agar ek null hai aur doosra nahi, ya dono ki values match nahi kar rahi
        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }
        
        // Cross-check: (Left vs Right) aur (Right vs Left)
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}