import java.util.*;
// pair in array
public class pairArray {

    public static void printPair(int arr[]){
        // Total pairs
        int totalPairs =0;
        for(int i=0; i<arr.length; i++){
            int curr = arr[i]; //1,2,3,4,5
            for(int j=i+1; j<arr.length; j++){
                System.out.print("("+curr+","+arr[j]+")");
                totalPairs++;
            }
            System.out.println();
        }
        System.out.println("Total Pairs: "+totalPairs);
    }

    public static void main(String args[]){
        int arr[] = {1,2,3,4,5};
        printPair(arr);
    }
}