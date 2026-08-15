/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        // Step 1: Agly node ki value ko current node mein copy karo
        node.val = node.next.val;
        
        // Step 2: Next node ko bypass/skip kar do
        node.next = node.next.next;
    }
}