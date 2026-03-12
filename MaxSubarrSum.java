// Brute Force Approach Time Complexity: O(n^3) Space Complexity: O(1)
public class MaxSubarrSum {
    public static void MaxSubarraySum(int numbers[]){
        //Total subarrays
        int CurrentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<numbers.length;i++){
            int start = i;
            for(int j=i;j<numbers.length;j++){
                int end = j;
                    CurrentSum = 0;
                for(int k=start;k<=end;k++){
                    //subarray sum
                    CurrentSum += numbers[k];
                }
                System.out.println(CurrentSum);
            if(CurrentSum>maxSum){
                maxSum = CurrentSum;
            }
                
            }
            
        }
        System.out.println("Max sum is: "+maxSum);
        
    
    }
    public static void main(String args[]){
        int numbers[] = {2,4,6,8,10};
        MaxSubarraySum(numbers);
    }
}