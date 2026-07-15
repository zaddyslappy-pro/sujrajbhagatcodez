class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumEven = 0, sumOdd = 0;
        for(int i=0; i<=2*n; i++){
            if(i % 2 == 0){
                sumEven += i;
            }
            else{
                sumOdd += i;
            }
        }
        return GCD(sumEven, sumOdd);
    }

    public int GCD(int a, int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}