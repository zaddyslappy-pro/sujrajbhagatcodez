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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 1. Find middle node using slow/fast pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half of the linked list
        ListNode secondHalfHead = reverseList(slow);
        ListNode firstHalfHead = head;

        // 3. Compare values from both halves
        ListNode temp = secondHalfHead; // Keep track to restore list later if needed
        boolean isPalindrome = true;
        while (temp != null) {
            if (firstHalfHead.val != temp.val) {
                isPalindrome = false;
                break;
            }
            firstHalfHead = firstHalfHead.next;
            temp = temp.next;
        }

        // 4. Restore original list structure
        reverseList(secondHalfHead);

        return isPalindrome;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}