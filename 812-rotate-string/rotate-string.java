class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }
        char arr[] = s.toCharArray();
        for(int k=0; k<arr.length; k++){
            char temp = arr[0];

            for(int i=0; i<arr.length-1; i++){
                arr[i] = arr[i+1];
            }
            arr[arr.length-1] = temp;

            if(new String(arr).equals(goal)){
                return true;
            }
        }
        return s.equals(goal);
    }
}