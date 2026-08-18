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
    public ListNode removeElements(ListNode head, int val) {
        // Dummy node points to head to simplify deleting head nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode curr = dummy;
        
        while (curr.next != null) {
            if (curr.next.val == val) {
                // Skip the node matching val
                curr.next = curr.next.next;
            } else {
                // Advance pointer only if no deletion happened
                curr = curr.next;
            }
        }
        
        return dummy.next;
    }
}