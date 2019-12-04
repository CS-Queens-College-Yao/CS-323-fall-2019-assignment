/**
 * Running Trials
 * Author: Brian Schlapp and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    // Your code here
    
    //both if statements are for the base cases where either the possible speed is 0 or 1, as well
    //as the case where there is only 1 day to test speeds.
    if(possibleSpeeds == 0 || possibleSpeeds == 1){
    	return possibleSpeeds;
    }
    
    if(days == 1){
    	return possibleSpeeds;
    }
    
    int min = Integer.MAX_VALUE;
    int temp;
    
    for(int i = 1; i <= possibleSpeeds; i++){
    	temp = Math.max(runTrialsRecur(i - 1,days - 1), runTrialsRecur(possibleSpeeds - i, days));
    	if(temp < min){
    		min = temp;
    	}
    }
    return min + 1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized() {
    int minTests = 0;
    // Your optional code here
    return minTests;
  }

  public int runTrialsBottomUp(int possibleSpeeds, int days) {
	    
	    // Set up a table containing the trial results
	    int trials[][] = new int[days+1][possibleSpeeds+1];

	    // base cases when there are 0 or 1 days
	    for(int i = 1; i <= days; i++){
	      trials[i][0] = 0;
	      trials[i][1] = 1;
	    }

	    for(int i = 1; i <= possibleSpeeds; i++){
	      trials[1][i] = i;
	    }

	    int curTrial, injured, uninjured;

	    // This is the bottom up approach
	    for(int i=2; i<=days; i++){
	      for(int j=2; j<=possibleSpeeds; j++){
	        
	        // Set up the number of trials to be the largest possible integer value
	        trials[i][j] = Integer.MAX_VALUE;
	        
	        // start from the beginning of the table
	        for(int k=1; k<=j; k++){
	          // If injured, go down one speed level and decrement the number of days
	          injured = trials[i-1][k-1];
	          // If uninjured
	          uninjured = trials[i][j-k];
	          
	          curTrial = 1 + Math.max(injured, uninjured);
	          // Take the minimum of curTrial and the results from previous trials
	          if(curTrial < trials[i][j]){
	            trials[i][j] = curTrial;
	          }
	        }
	      }
	    }

	    return trials[days][possibleSpeeds];

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
