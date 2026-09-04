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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // LinkedList allows us to easily add to the front or back
            LinkedList<Integer> currentLevel = new LinkedList<>(); 

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Add to the back for normal order, add to the front for reverse
                if (leftToRight) {
                    currentLevel.add(node.val); 
                } else {
                    currentLevel.addFirst(node.val); 
                }

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // Flip the direction for the next level
        }
        return result;
    }
}