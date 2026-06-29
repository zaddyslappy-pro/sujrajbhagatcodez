class Solution {
    public String reversePrefix(String word, char ch) {
        int i=0, j=0, n = word.length()-1;
        char wordArr[] = word.toCharArray();
        while(i <= n){
            if(wordArr[i] == ch){
                while(j < i){
                    char temp = wordArr[i];
                    wordArr[i] = wordArr[j];
                    wordArr[j] = temp;
                    j++;
                    i--;
                }
                return new String(wordArr);
            }
            i++;
        }
        return word;
    }
}