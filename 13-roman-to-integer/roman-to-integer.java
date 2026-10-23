class Solution {
    public int romanToInt(String s) {
        int total = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int current = getValue(s.charAt(i));
            
            // Agar next character exist karta hai aur uski value current se badi hai
            if (i < n - 1 && current < getValue(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }
        
        return total;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}