import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int ast : asteroids) {
            boolean exploded = false;
            
            // Collision tabhi hoga jab current negative ho aur stack top positive ho
            while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                if (stack.peek() < -ast) {
                    // Stack waala asteroid chhota hai, wo explode ho jayega
                    stack.pop();
                    continue; // current asteroid abhi bhi active hai, agle se takrayega
                } else if (stack.peek() == -ast) {
                    // Dono barabar hain, dono explode honge
                    stack.pop();
                    exploded = true;
                    break;
                } else {
                    // Stack waala bada hai, current explode ho jayega
                    exploded = true;
                    break;
                }
            }
            
            // Agar current asteroid explode nahi hua, to stack mein daal do
            if (!exploded) {
                stack.push(ast);
            }
        }
        
        // Stack ke elements ko result array mein fill karo
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}