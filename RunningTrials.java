/**
 * Running Trials
 * Author: Sukharam Gole , Rayamond Calapatia and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 * Yes
 */

public class RunningTrials {

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeed, int days) {
        int minTests = 0;
        int min = Integer.MAX_VALUE;     // inilization variable min as Integer.MAX_VALUE = 2147483647
        // Your code here
        if (possibleSpeed == 1 || possibleSpeed == 0)    // If there is no speed testing, we don't have any days
            return possibleSpeed;                       //  if there is 1 speed testing there then we need  at least one day

        if (days == 1)               // if we have only 1 days then we have PossibleSpeed times of practising speed test
            return possibleSpeed;

        for (int i = 1; i <= possibleSpeed; i++) {
            int result = Math.max(runTrialsRecur(i - 1, days - 1), runTrialsRecur(possibleSpeed - i, days));
            if (result < min)
                min = result;
        }
        minTests = min + 1;
        return minTests;
    }

  // Optional: test
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your optional code here
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
      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}
