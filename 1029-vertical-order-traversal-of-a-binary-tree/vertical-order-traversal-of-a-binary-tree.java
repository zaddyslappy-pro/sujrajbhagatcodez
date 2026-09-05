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
import java.util.*;

class Solution {
    // Helper class to store the node along with its row and column coordinates
    class Tuple {
        TreeNode node;
        int row;
        int col;
        
        public Tuple(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Map structure: Column -> Row -> Min-Heap (PriorityQueue for overlapping values)
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> queue = new LinkedList<>();
        
        // Start BFS with the root at (0, 0)
        queue.offer(new Tuple(root, 0, 0));
        
        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            TreeNode node = curr.node;
            int row = curr.row;
            int col = curr.col;
            
            // Initialize nested structures if they don't exist yet
            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            
            // Add the current node's value to the priority queue
            map.get(col).get(row).offer(node.val);
            
            // Traverse left (row + 1, col - 1)
            if (node.left != null) {
                queue.offer(new Tuple(node.left, row + 1, col - 1));
            }
            // Traverse right (row + 1, col + 1)
            if (node.right != null) {
                queue.offer(new Tuple(node.right, row + 1, col + 1));
            }
        }
        
        // Compile the final result
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> verticalList = new ArrayList<>();
            for (PriorityQueue<Integer> nodes : colMap.values()) {
                while (!nodes.isEmpty()) {
                    verticalList.add(nodes.poll());
                }
            }
            result.add(verticalList);
        }
        
        return result;
    }
}