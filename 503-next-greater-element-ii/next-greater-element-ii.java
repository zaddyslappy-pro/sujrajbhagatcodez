class Solution {
    public int[] nextGreaterElements(int[] nums) {
       int N = nums.length;
       int result[] = new int[N];

       Arrays.fill(result, -1);
       Stack<Integer> stack = new Stack<>();

       for(int i=0; i<2*N; i++){
        int currentNum = nums[i % N];
        while(!stack.isEmpty() && nums[stack.peek()] < currentNum){
            int smallerNum = stack.pop();
            result[smallerNum] = currentNum;
        }
        if(i < N){
            stack.push(i);
        }
       }
       return result;
    }
}