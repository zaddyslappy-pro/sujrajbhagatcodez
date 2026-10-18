class Solution {
    public int majorityElement(int[] nums) {
        int cand = nums[0];
        int freq = 0;
        for(int num : nums){
            if(freq == 0){
                cand = num;
            }
            if(cand == num){
                freq++;
            }
            else {
                freq--;
            }
        }
        return cand;
    }
}