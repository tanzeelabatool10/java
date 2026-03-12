// Kadane's Algorithm Time Complexity: O(n)
public class KadaneMaxSubarrSum {
        public static void KadaneMaxSubarrSum(int numbers[]){
        int Currentsum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<numbers.length;i++){
            Currentsum = Currentsum + numbers[i];
            if(Currentsum<0){
                Currentsum = 0;
            }
            maxSum = Math.max(Currentsum, maxSum);
        }
        System.out.println("Max sum is: "+maxSum);
    }
    public static void main(String args[]){
        int numbers[] = {-2,-3,4,-1,-2,1,5,-3};
        KadaneMaxSubarrSum(numbers);
    }
}