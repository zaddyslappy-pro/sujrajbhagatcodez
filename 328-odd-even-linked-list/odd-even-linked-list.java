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
    public ListNode oddEvenList(ListNode head) {
        // Base case: if list is empty or has only 1-2 nodes, no reordering needed
        if (head == null || head.next == null) {
            return head;
        }

        // Pointers initialization
        ListNode odd = head;             // Points to 1st node
        ListNode even = head.next;       // Points to 2nd node
        ListNode evenHead = even;        // Save the start of even list

        // Rearrange links
        while (even != null && even.next != null) {
            odd.next = even.next;        // Link current odd node to next odd node
            odd = odd.next;              // Move odd pointer forward
            
            even.next = odd.next;       // Link current even node to next even node
            even = even.next;            // Move even pointer forward
        }

        // Connect the end of odd list to the start of even list
        odd.next = evenHead;

        return head;
    }
}