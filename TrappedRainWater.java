//Time Complexity: O(n) - we traverse the height array three times
//Space Complexity: O(n) - we use two additional arrays to store the left and right
public class TrappedRainWater{

    public static int TrappedRainWater(int height[]){

    // To calculate Left Max Boundary -array
    int leftMax[] = new int[height.length];
    leftMax[0] = height[0];
    for(int i=1; i<height.length; i++){
        leftMax[i] = Math.max(height[i], leftMax[i-1]);
    }
   // To calculate Right Max Boundary -array
    int rightMax[] = new int[height.length];
    rightMax[height.length-1] = height[height.length-1];
    for(int i=height.length-2; i>=0; i--){
        rightMax[i] = Math.max(height[i], rightMax[i+1]);
    }
   // Loop
int trappedwater = 0;
    for(int i=0; i<height.length; i++){
        //waterLevel = min(leftMaxBoundary, rightMaxBoundary)
        int waterLevel = Math.min(leftMax[i], rightMax[i]);
         //waterTrapped = waterLevel - height[i]
        trappedWater += waterLevel - height[i];
    }
    return trappedWater;
   }
    public static void main(String[] args){
        int height[] = {4,2,0,6,3,2,5};
       System.out.println(TrappedRainWater(height));

    }
}