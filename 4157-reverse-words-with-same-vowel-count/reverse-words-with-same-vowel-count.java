class Solution {
    public String reverseWords(String s) {
        String words[] = s.split(" ");
        int target = countVowel(words[0]);

        for(int i=1; i<words.length; i++){
            if(target == countVowel(words[i])){
                words[i] = reverse(words[i]);
            }
        }
        return String.join(" ", words);
    }
    public int countVowel(String words){
        int count = 0;
        for(int i=0; i<words.length(); i++){
            char ch = words.charAt(i);

            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                count++;
            }
        }
        return count;
    }

    public String reverse(String words){
        char arr[] = words.toCharArray();
        int left = 0, right = arr.length-1;

        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        return new String(arr);
    }
}