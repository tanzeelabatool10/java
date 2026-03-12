// Time Complexity: O(n) where n is the number of days
// Space Complexity: O(1) as we are using only constant space
import java.util.*;
public class BuySellStock {
    public static int BuySellStock(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i =0;i<prices.length;i++) {
            if(buyPrice <prices[i]) { //profit
                int profit = prices[i] - buyPrice;// today's price - buy price
                maxProfit = Math.max(maxProfit, profit); //Total profit = max of current profit and max profit
           } else {
                buyPrice = prices[i];
            }
            
        }
        
        return maxProfit;
    }
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(BuySellStock(prices));
    }
}