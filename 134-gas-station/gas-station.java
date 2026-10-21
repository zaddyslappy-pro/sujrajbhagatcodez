class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        
        // Agar total gas kam hai total cost se, toh chakkar lagana impossible hai
        if (totalGas < totalCost) {
            return -1;
        }
        
        int currentTank = 0;
        int startIndex = 0;
        
        for (int i = 0; i < gas.length; i++) {
            currentTank += gas[i] - cost[i];
            
            // Agar kisi point par tank negative ho gaya
            if (currentTank < 0) {
                // Agla index try karo
                startIndex = i + 1;
                currentTank = 0;
            }
        }
        
        return startIndex;
    }
}