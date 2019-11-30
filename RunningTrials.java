/**
 * Running Trials
 * Author: Jose Santos (1:40 Class), Jemima Shikdar (3:10 Class) and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {
	
	// Do not change the parameters!
	public int runTrialsRecur(int possibleSpeeds, int days) {
	  int minTests = 0;
	  // Your code here
	  
	  int N = possibleSpeeds;
	  int M = days;
	  if(M == 1) {
		  return N; //worse case I'll have to do N speed tests
	  }
		//1 possible speed to run 
	  if(N == 0 || N == 1) {
		  return N; //No other options 
	  }
				
	  int temp = 0;
	  minTests = Integer.MAX_VALUE;
	  for(int i = 1; i < N; i++) {
		  temp = Math.max(runTrialsRecur(i-1, M-1), runTrialsRecur(N-i, M));
		  minTests = Math.min(minTests, temp);
	  }
				
	  return minTests + 1; 
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
	  for(int n = 0; n < possibleSpeeds; n++) {
		  
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
	    System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
        System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
	}
}
