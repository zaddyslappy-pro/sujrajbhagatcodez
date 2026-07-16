class Solution {
    public long gcdSum(int[] nums) {
        long mx = 0;
        long ans = 0;
        long prefixGcd[] = new long[nums.length];
        for(int i=0; i<nums.length; i++){
            mx = Math.max(nums[i], mx);
            prefixGcd[i] = GCD(nums[i], mx);
        }
        Arrays.sort(prefixGcd);
        int left = 0;
        int right = prefixGcd.length-1;

        while(left < right){
            ans += GCD(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        return ans;
    }

    public long GCD(long a, long b){
        while(b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}