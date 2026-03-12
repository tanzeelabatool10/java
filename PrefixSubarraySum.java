// Prefix Array Approach Time Complexity: O(n^2)
public class PrefixSubarraySum {
    public static void PrefixSubarraySum(int numbers[]){
        //Total subarrays
        int CurrentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int prefix[] = new int[numbers.length];
            prefix[0] = numbers[0];
        // Calculate prefix array
        for( int i =1; i<prefix.length;i++){
            prefix[i] = prefix[i-1]+numbers[i];
        }
        for(int i=0;i<numbers.length;i++){
            int start = i;
            for(int j=i;j<numbers.length;j++){
                int end = j;
                    CurrentSum = start ==0? prefix[end] : prefix[end] - prefix[start-1];
                
        
            if(CurrentSum>maxSum){
                maxSum = CurrentSum;
            }
                
            }
            
        }
        System.out.println("Max sum is: "+maxSum);
        
    
    }
    public static void main(String args[]){
        int numbers[] = {2,4,6,8,10};
        PrefixSubarraySum(numbers);
    }
}