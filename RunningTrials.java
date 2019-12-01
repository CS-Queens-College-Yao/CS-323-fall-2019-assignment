/**
 * Running Trials
 * Author: Zhiwen Jiang, Yubo Sun, Chuhui Chen and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    int minTests = possibleSpeeds;
		int fail;
		int success;
		int max;
		//We use Memo to do the recursion
		//take out the value from memo if it's not empty.
		int retrivedValue = runTrialsMemoized(days,possibleSpeeds,storedTests);
		if(retrivedValue != -1)
			return retrivedValue;
		else //do the recursion to find the max of each pair, then find the min of those pairs.
		for(int i = 1; i <= possibleSpeeds; i++) {
			fail = runTrialsRecur(i - 1, days - 1) + 1;
			success = runTrialsRecur(possibleSpeeds - i, days) + 1;
			max = Math.max(fail, success);
			if(max < minTests) 
        minTests = max;
		}
		//Store the next value into the 2D array
		storedTests[days][possibleSpeeds] = minTests;
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int days, int possibleSpeeds, int array[][]) {
     if(days<0||possibleSpeeds<0)
			  return 0;
		if(array[days][possibleSpeeds] != 0)
			 return array[days][possibleSpeeds]; 
	    		
	  return -1;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
   // Setup 2D array
	   int dp[][] = new int[days + 1][possibleSpeeds + 1];
	 // Use nested for loop to find the max for each pair and then find the min for those pairs.
	   for(int i = 0; i <= days; i++) {
		   for(int j = 0; j <= possibleSpeeds; j++) {
			   if(i == 0 || j == 0) 
           dp[i][j] = 0;
			   else if(i == 1) 
           dp[i][j] = j;
			   else {
				   int findMin = Math.max(dp[i][j-1], dp[i-1][0])+1;
				   // Find the max value between fail and success.
				   for(int a = 2; a < j; a++) {
					   int temp = Math.max(dp[i][j-a], dp[i-1][a-1] )+1;
					   if(temp < findMin) findMin = temp;
			   }
			   // Store the value into 2D array.
			   dp[i][j] = findMin;
			  }
		   }
	   }
	   return dp[days][possibleSpeeds];
  }

  public static void main(String args[]){
      RunningTrials running = new RunningTrials();

      // Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(12, 5);
      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}
