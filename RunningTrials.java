/**
 * Running Trials
 * Author: Jose Santos (1:40 Class), Jemima Shikdar (3:10 Class) and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 * Y
 */

public class RunningTrials {
	
	// Do not change the parameters!
	public int runTrialsRecur(int possibleSpeeds, int days) {
	  int minTests = 0;
	  // Your code here
	  
	  int N = possibleSpeeds;
	  int M = days;
	  
	  //1 day to train
	  if(M == 1) {
		  return N; //worse case I'll have to do N speed tests
	  }
	  //0 or 1 possible speeds to run 
	  if(N == 0 || N == 1) {
		  return N; //No other options 
	  }
				
	  int temp = 0;
	  minTests = Integer.MAX_VALUE;
	  for(int i = 1; i <= N; i++) {
		  temp = Math.max(runTrialsRecur(i-1, M-1), runTrialsRecur(N-i, M));
		  minTests = Math.min(minTests, temp);
	  }
				
	  return minTests + 1; 
	}

	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.
	public int runTrialsMemoized(int possibleSpeeds, int days) {
	  int minTests = 0;
	  // Your optional code here
	  int[][] mem = new int[possibleSpeeds + 1][days + 1];
	  minTests = runningMem(possibleSpeeds, days, mem);
	  
	  return minTests;
	}
	
	public int runningMem(int N, int M, int[][]mem) {
	  int minTests = 0;
	  //base cases
	  //One day 
	  if(M == 1) {
		  return N; //worse case I'll have to do N speed tests
	  }
	  //1 possible speed to run 
	  if(N == 0 || N == 1) {
		  return N; //No other options 
	  }
	  //check the memory for an answer 
	  if(mem[N][M] > 0 ) {
		  return mem[N][M];
	  }
				
	  int temp = 0;
	  minTests = Integer.MAX_VALUE;
	  for(int i = 1; i <= N; i++) {
		  temp = Math.max(runTrialsRecur(i-1, M-1), runTrialsRecur(N-i, M));
		  minTests = Math.min(minTests, temp);
	  }
	  
	  mem[N][M] = minTests + 1;
				
	  return mem[N][M]; 
	}

	// Do not change the parameters!
	public int runTrialsBottomUp(int possibleSpeeds, int days) {
	  int minTests = 0;
	  
	  // Your code here
	  int[][] tests = new int[possibleSpeeds + 1][days + 1];
	  
	  //fill in the base cases 
	  //1 day to train 
	  for(int n = 1; n <= possibleSpeeds; n++) {
		  tests[n][1] = n;
	  }
	  //0 or 1 possible speed 
	  for(int m = 1; m <= days; m++) {
		  tests[0][m] = 0;
		  tests[1][m] = 1; 
	  }
	  
	  int temp = 0;
	  for(int n = 2; n <= possibleSpeeds; n++) {
		  for(int m = 2; m <= days; m++) {
			  tests[n][m] = Integer.MAX_VALUE;
			  temp = 0;
			  for(int t = 1; t <= n; t++) {
				  temp = 1 + Math.max(tests[t-1][m-1], tests[n-t][m]);
				  tests[n][m] = Math.min(temp, tests[n][m]);
			  }
		  }
	  }
	  
	  minTests = tests[possibleSpeeds][days];
	  
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
        
        /**
         * For bonus question: 
         * int minTrials3Recur = running.runTrialsMemoized(12, 5);
         * int minTrials4Recur = running.runTrialsMemoized(20, 8);
         * System.out.println("12 speeds, 5 weeks: " + minTrials3Recur);
         * System.out.println("20 speeds, 8 weeks: " + minTrials4Recur);
         */
	}
}
