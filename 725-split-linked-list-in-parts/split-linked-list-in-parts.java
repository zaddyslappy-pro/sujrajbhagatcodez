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
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];

        // Step 1: Calculate total length of the list
        int totalLength = 0;
        ListNode curr = head;
        while (curr != null) {
            totalLength++;
            curr = curr.next;
        }

        // Step 2: Determine base size and remainder
        int baseSize = totalLength / k;
        int extra = totalLength % k;

        // Step 3: Cut the list into parts
        curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) {
                result[i] = null;
                continue;
            }

            result[i] = curr;
            // First 'extra' parts get 1 additional node
            int currentPartSize = baseSize + (i < extra ? 1 : 0);

            // Traverse to the last node of the current part
            for (int j = 1; j < currentPartSize; j++) {
                curr = curr.next;
            }

            // Cut the link to separate this part from the rest
            ListNode nextPartHead = curr.next;
            curr.next = null;
            curr = nextPartHead;
        }

        return result;
    }
}