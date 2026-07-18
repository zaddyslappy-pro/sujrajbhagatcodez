class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0], b = nums[nums.length-1];

        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}