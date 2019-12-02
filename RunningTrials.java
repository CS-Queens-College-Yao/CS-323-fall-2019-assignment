/**
 * Running Trials
 * Author: Omar Mirza and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 * YES
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    minTests = Integer.MAX_VALUE;
    if(days==1 || possibleSpeeds <= 1) return possibleSpeeds;
    for(int i=1;i<=possibleSpeeds;i++){
      int x = Math.max(runTrialsRecur(i-1, days-1), runTrialsRecur(possibleSpeeds-i, days));
      minTests = Math.min(x,minTests);
    }    
    return minTests+1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    int pastTrials[][];
    for(int i=0;i<possibleSpeeds;i++){
      // Check if we have it in our array
      // If we don't, then lets calculate it using Math.min(runTrialsRecur(possibleSpeeds-1, days-1), runTrialsRecur(possibleSpeeds-1, days))+1;
      // Then add it to the array and go next
    }
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    int[][] pastTrials = new int[possibleSpeeds+1][days+1];

    //Base cases
    for(int i=0;i<=days;i++){
      pastTrials[1][i]=1;
      pastTrials[0][i]=0;
    }
    for(int j=1;j<=possibleSpeeds;j++){
      pastTrials[j][1]=j;
    }
    for(int k=2;k<=days;k++){
      for(int l=2;l<=possibleSpeeds;l++){
        pastTrials[l][k] = Integer.MAX_VALUE;
        int x;
        for(int m = 1; m<=l;m++){
          x=Math.max(pastTrials[m-1][k-1],pastTrials[l-m][k]) + 1;
          pastTrials[l][k] = Math.min(x,pastTrials[l][k]);
        }
      }
    }
    minTests = pastTrials[possibleSpeeds][days];




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