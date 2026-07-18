class Solution {
    private int start = 0;
    private int maxLen = 1;
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }

        for(int i=0; i<s.length(); i++){
            expand(s, i,i);
            expand(s, i,i+1);
        }
        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            if(right - left + 1 > maxLen){
                maxLen = right - left + 1;
                start = left;
            }
            left--;
            right++;
        }
    }
}