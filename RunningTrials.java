/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
	  int minTests = 999;

	  //base case when there is no possibleSpeeds and no days
	  if(possibleSpeeds == 0 || days == 0) {
		  return 0;
	  }
	  
	  //base case when there is only one possible speed
	  if(possibleSpeeds == 1) {
		  return 1;
	  }
	  
	  for(int i = 2; i <= possibleSpeeds; i++) {
		  // when the athelete is injured on that day
		  // when the athlete is not injured on that day
		  minTests = Math.max(runTrialsRecur(i-1, days-1)+1, runTrialsRecur(possibleSpeeds-i, days)+1);
	  }
	    
	  return minTests;
  }


// Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
	
	  int[][] memo = new int[possibleSpeeds+1][days+1];
	  int minTests = 0;
    
    // Your optional code here
    
    //base case
	  if(possibleSpeeds == 0 || days == 0) {
		  return 0;
	  }
	  
	  if(possibleSpeeds == 1) {
		  return 1;
	  }

	  if(memo[possibleSpeeds][days] != 0) {
		  return memo[possibleSpeeds][days];
	  }

	  for(int i = 2; i <= possibleSpeeds; i++) {
		  // when the athelete is injured on that day
		  // when the athlete is not injured on that day
		  minTests = Math.max(runTrialsMemoized(i-1, days-1)+1, runTrialsMemoized(possibleSpeeds-i, days)+1);
	  }
	  
	  memo[possibleSpeeds][days] = minTests;
	  
	  
	  return memo[possibleSpeeds][days];
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
	    int minTests = 0;
	    // Your code here
	    
	    int[][] dp = new int[possibleSpeeds+1][days+1];
	    
	    for(int i = 1; i<= possibleSpeeds; i++) {
	    	dp[i][0] = 0;
	    }
	    
	    for(int j = 1; j<= days; j++) {
	    	dp[0][j] = 0;
	    	dp[1][j] = 1;
	    }
	    
	    for(int i = 2; i <= possibleSpeeds; i++) {
	    	for(int j = 2; j <= days; j++) {
	    		dp[i][j] = 999;
	    		for(int k = 1; k < i; k++) {
	    			// when the athelete is injured
	    			// when the athelete is not injured
	    			minTests = Math.max(dp[i-1][j-1], dp[i-k][j])+1;
	    			dp[i][j] = Math.min(dp[i][j], minTests);
	    			minTests = dp[i][j];
	    		}
	    	}
	    }
	    
	    return minTests;
	  }

  public static void main(String args[]){
      RunningTrials running = new RunningTrials();

      // Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(12, 5);
      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);

      System.out.println("12 speeds, 5 days: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 days: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}
