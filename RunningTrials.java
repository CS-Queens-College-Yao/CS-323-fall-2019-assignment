/**
 * Running Trials
 * Author: Rommel Gay
 * Does this compile or finish running within 5 seconds? Y
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    if(possibleSpeeds > 0 && days > 0) {
    	minTests = 1 + runTrialsRecur(possibleSpeeds-2, days-1);
    	return minTests + runTrialsRecur(possibleSpeeds-1, days);
    }
    
    return minTests;
    
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
    int[] t = new int[possibleSpeeds*days];
    
    t[0] = 0;
    t[1] = 1;
    
    for(int i = 2; i <= possibleSpeeds; i++) {
    	t[i] = t[i-1] + t[i-2];
    	for(int j = i+2, k = 1; j <= possibleSpeeds && k <= days; j+=2) {    		
    		t[i]++;
    		k++;
    	}
    }
    
    
    minTests = t[possibleSpeeds];
    
    
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
