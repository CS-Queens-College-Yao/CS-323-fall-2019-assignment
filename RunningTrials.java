import java.util.Arrays;

/**
 * Running Trials
 * Author: Hein Soe and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {
    public static void print (String txt) {
        System.out.println(txt);
    }

    public static void printMemoizedArr(int[][] memoizedArr)
    {
        print("");
        for (int i = 0; i < memoizedArr.length; i++)
        {
            print(Arrays.toString(memoizedArr[i]));
        }
    }

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;

      //If only one day, worst case athlete needs to test all of speeds.
      if (days == 1) {
          return  possibleSpeeds;
      }
      //Below and speed 2 will need to test all possible speeds.
      if (possibleSpeeds <= 2) {
          return possibleSpeeds;
      }

      int minTestAtSpeed = -1;
      for (int speedIndex=1; speedIndex < possibleSpeeds; speedIndex++)
      {
          int belowSpeedMaxTest = runTrialsRecur(speedIndex-1, days-1);
          int aboveSpeedMaxTest = runTrialsRecur(possibleSpeeds-speedIndex, days);

          int maxTestAtSpeedIndex = Math.max(belowSpeedMaxTest, aboveSpeedMaxTest);

          //Choosing worst case from all of speedIndexes.
          if (minTests == 0 || minTests > maxTestAtSpeedIndex)
          {
              minTests = maxTestAtSpeedIndex;
              minTestAtSpeed = speedIndex;
          }
      }

    //Adding one to minTests as at least one speedtest required.
    return minTests + 1;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
    public int runTrialsMemoized(int possibleSpeeds, int days) {

        int minTests = 0;

    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
//    int minTests = 0;

      int memoizeArr [][] = new int [possibleSpeeds + 1][days + 1];

      //In case of only one day, trials are num of speeds.
      for (int eachSpeed = 0; eachSpeed <= days; eachSpeed++) {
          memoizeArr[eachSpeed][1] = eachSpeed;
      }

      for (int eachDay = 1; eachDay <= days; eachDay++) {
          memoizeArr[0][eachDay] = 0;
          memoizeArr[1][eachDay] = 1;
          memoizeArr[2][eachDay] = 2;
      }

      for (int eachDay = 2; eachDay <= days; eachDay++) {
          for (int eachSpeed = 3; eachSpeed <= possibleSpeeds; eachSpeed++) {
              int minTrials = -1;
              for (int x=1; x <= eachSpeed; x++) {
                  int maxSpeedTestAtIndex = 1 + Math.max(memoizeArr[x-1][eachDay-1], memoizeArr[eachSpeed-x][eachDay]);
                  if (minTrials == -1 || minTrials > maxSpeedTestAtIndex) {
                      minTrials = maxSpeedTestAtIndex;
                      memoizeArr[eachSpeed][eachDay] = minTrials;
                  }
              }
          }
        printMemoizedArr(memoizeArr);
      }
      return memoizeArr[possibleSpeeds][days];
  }

  public static void main(String args[]) {
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
