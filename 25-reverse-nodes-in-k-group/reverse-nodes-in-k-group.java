/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Step 1: Check if there are at least k nodes remaining
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If we have at least k nodes, reverse them
        if (count == k) {
            ListNode prev = null;
            ListNode nextNode = null;
            curr = head;
            
            // Step 2: Reverse k nodes
            for (int i = 0; i < k; i++) {
                nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }
            
            // Step 3: Recurse for the remaining list and attach it
            if (nextNode != null) {
                head.next = reverseKGroup(nextNode, k);
            }
            
            // prev is the new head of the reversed group
            return prev;
        }
        
        // If remaining nodes are less than k, keep them as is
        return head;
    }
}