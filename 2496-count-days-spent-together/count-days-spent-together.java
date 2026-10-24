class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aArrive = calculateDayOfYear(arriveAlice);
        int aLeave = calculateDayOfYear(leaveAlice);
        int bArrive = calculateDayOfYear(arriveBob);
        int bLeave = calculateDayOfYear(leaveBob);

        // Find the overlapping interval
        int startTogether = Math.max(aArrive, bArrive);
        int endTogether = Math.min(aLeave, bLeave);

        // If the start date is before or equal to the end date, calculate the duration
        return Math.max(0, endTogether - startTogether + 1);
    }

    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        
        return totalDays;
    }
}