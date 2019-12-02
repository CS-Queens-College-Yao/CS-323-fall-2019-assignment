/**
 * Running Trials
 * Author: Luis Santos and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N	Y
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    
    // Your code here
    
    // If there is only one day there are only as many trials as possible speeds, n.
    if (days == 1) {
    	return possibleSpeeds;
    }
    
    // If there is only 1 or 0 speeds then only 1 trial needs to be done or 0.
    if (possibleSpeeds == 1 || possibleSpeeds == 0){
    	return possibleSpeeds;
    }
    
    minTests = Integer.MAX_VALUE;
    int temp;
    // Two cases either the runner gets injured or the runner does not get injured.
    // If the runner gets injured at a certain speed, n, then you a day and check the next speed below it.
    // If the runner does not get injured at a certain speed, n,  then there is no need to check the speeds below it
    // so you can just check the speeds above it. You do this for every speed.
    for(int n = 1; n <= possibleSpeeds; n++){
    	temp = 1 + Math.max(runTrialsRecur(n - 1, days - 1), runTrialsRecur(possibleSpeeds - n, days));
    	minTests = Math.min(temp, minTests);

    }
 
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized() {
    int minTests = 0;
    // Your optional code here
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    
    // Your code here
    
    // N*M 2d array
    int [][] solution = new int [possibleSpeeds + 1][days + 1];
    
    // Base cases where speed is equal to 0 or 1
    for(int m = 1; m <= days; m++){
    	solution[0][m] = 0;
    	solution[1][m] = 1;
    }
    
    // Base case where days is equal to 1 so all trials are set to n speeds.
    for (int n = 1; n <= possibleSpeeds; n++){
    	solution[n][1] = n;
    }
    
    
    for(int n = 2; n <= possibleSpeeds; n++){
    	for (int m = 2; m <= days; m++){
    		solution[n][m] = Integer.MAX_VALUE;
    		int temp;
    		for (int k = 1; k <= n; k++) {
    			temp = 1 + Math.max(solution[k - 1][m - 1], solution[n-k][m]);
    			solution[n][m] = Math.min(temp, solution[n][m]);
    		}
    	}
    }
    minTests = solution[possibleSpeeds][days];
    return minTests;
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
