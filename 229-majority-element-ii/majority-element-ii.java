class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        
        // Step 1: Find candidates
        int cand1 = 0, cand2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // Step 2: Verify candidates
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > n / 3) result.add(cand1);
        if (count2 > n / 3) result.add(cand2);

        return result;
    }
}