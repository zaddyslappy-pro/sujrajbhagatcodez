class Solution {
    public int jump(int[] nums) {
        int jump = 0;
        int farthest = 0;
        int countEnd = 0;

        for(int i=0; i<nums.length-1; i++){
            farthest = Math.max(farthest, i+nums[i]);
            if(i == countEnd){
                jump++;
                countEnd = farthest;

                if(countEnd >= nums.length-1){
                    break;
                }
            }
        }
        return jump;
    }
}