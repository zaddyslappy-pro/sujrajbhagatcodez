class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int count=1;
        int prevEnd = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] >= prevEnd){
                count++;
                prevEnd = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}