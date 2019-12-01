import java.util.Arrays;

/**
 * Running Trials
 * Author: Sukharam Gole , Raymond Calapatia and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 * Yes
 */

public class RunningTrials {

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeed, int days) {
        int minTests = 0;
        int min = Integer.MAX_VALUE;     // initializing variable min as Integer.MAX_VALUE = 2147483647
        // Your code here
        if (possibleSpeed == 1 || possibleSpeed == 0)    // If there is no speed to test, we did not use any days
            return possibleSpeed;                       //  if we tested at least one speed, we need at least one day

        if (days == 1)               // if we have only 1 day then we have x amount of PossibleSpeed to test on that day.
            return possibleSpeed;

        for (int i = 1; i <= possibleSpeed; i++) {
            int result = Math.max(runTrialsRecur(i - 1, days - 1), runTrialsRecur(possibleSpeed - i, days));
            if (result < min)
                min = result;
        }
        minTests = min + 1;
        return minTests;
    }

    // Optional:test
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
        int[][] runTrialsBottomUp = new int[days + 1][possibleSpeeds + 1];

        // Base Case 1:  if there is no speed to be tested there is no days used.
        // If there is at least 1 speed to be tested, the athlete needs at least one day.

        for (int i = 1; i <= days; i++) {
            runTrialsBottomUp[i][0] = 0;
            runTrialsBottomUp[i][1] = 1;
        }
        // Base case 2 : If the athlete has only one day to practice, the athlete will try to test all the possible speeds.

        for (int j = 1; j <= possibleSpeeds; j++) {
            runTrialsBottomUp[1][j] = j;
        }
        // Third condition for filling entire table using optimal substructure
        for (int i = 2; i <= days; i++) {
            for (int j = 2; j <= possibleSpeeds; j++) {
                runTrialsBottomUp[i][j] = Integer.MAX_VALUE;
                int Result;
                for (int k = 1; k <= j; k++) {
                    Result = 1 + Math.max(runTrialsBottomUp[i - 1][k - 1], runTrialsBottomUp[i][j - k]);
                    runTrialsBottomUp[i][j] = Math.min(Result, runTrialsBottomUp[i][j]);
                    minTests= runTrialsBottomUp[i][j];
                }
            }
        }

        //Pieces of codes needed for the extra Credit:
        // Array for storing the minimum number of speedruns for each speed.
        int [] arrOfSpeeds = new int [possibleSpeeds];
        arrOfSpeeds=runTrialsBottomUp[days];
        printSpeed(arrOfSpeeds);
        return minTests;
    }
    //part h Extra Credit.
    public void printSpeed(int i[]){
        for(int x = 1; x<i.length;x++){
            System.out.println("The athlete can test up to "+ x+ " speed(s) "+"during those "+ i[x]+ " minimum number"
                    + " of speedtest runs");
        }
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