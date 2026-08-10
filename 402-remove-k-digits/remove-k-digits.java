import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n) return "0"; // Agar saari digits remove karni hain
        
        // Deque ko stack ki tarah use karenge
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            
            // Jab tak stack top bada hai current character se aur k > 0 hai
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > ch) {
                stack.pollLast(); // pop from top
                k--;
            }
            
            stack.addLast(ch); // push
        }
        
        // Agar k abhi bhi bacha hai (strictly increasing numbers ke liye)
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }
        
        // Stack se result build karna
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst()); // bottom se top ki taraf read kar rahe hain
        }
        
        // Leading zeros remove karna
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        // Final check
        return sb.length() == 0 ? "0" : sb.toString();
    }
}