/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    minTests= Integer.MAX_VALUE;
    if (possibleSpeeds==1 ||possibleSpeeds ==0 || days == 1) 
    	return 1;
    else 
    	for (int i=1;i<possibleSpeeds+1; i++) {
    		minTests=Math.min(minTests,1+Math.max(runTrialsRecur(possibleSpeeds-i,days),runTrialsRecur(i-1,days-1)));
    	}
    
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your optional code here
    int trialArray[][] = new int[possibleSpeeds][days];
    
    trialArray[possibleSpeeds][days] = Integer.MAX_VALUE;
    
    for (int i=0; i<possibleSpeeds;i++) {
    	trialArray[i][1] =1;
    }
    
    for (int a=1;a<days;a++) {
    	for (int b=1;b<possibleSpeeds;b++) {
    		trialArray[a][b] = Math.min(1+Math.max(trialArray[possibleSpeeds-b][days], trialArray[possibleSpeeds-1][days-1]), trialArray[a][b]);
    	}
    }
    
    return trialArray[possibleSpeeds][days];
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
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
