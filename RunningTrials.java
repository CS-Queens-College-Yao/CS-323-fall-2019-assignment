/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    if(days==1) return possibleSpeeds;
    if(possibleSpeeds==2) return 1;
    minTests = Math.min(runTrialsRecur(possibleSpeeds-1, days-1), runTrialsRecur(possibleSpeeds-1, days))+1;
    return minTests;
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
      System.out.println("12 speeds, 5 days: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 days: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}