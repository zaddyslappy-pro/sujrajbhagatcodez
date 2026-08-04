class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
       int nextGreater[] = new int[10001];
       Stack<Integer> stack = new Stack<>();

       for(int num : nums2){
        while(!stack.isEmpty() && stack.peek() < num){
            int smallerNum = stack.pop();
            nextGreater[smallerNum] = num;
        }
        stack.push(num);
       }

       while(!stack.isEmpty()){
        nextGreater[stack.pop()] = -1;
       }

       int result[] = new int[nums1.length];
       for(int i=0; i<nums1.length; i++){
        result[i] = nextGreater[nums1[i]];
       }
       return result;
    }
}