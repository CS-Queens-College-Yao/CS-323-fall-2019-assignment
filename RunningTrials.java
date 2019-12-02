/**
 * Running Trials
 * Author: Arhum Khan and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

public class RunningTrials {

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int days) {
        int minTests = Integer.MAX_VALUE;

        //Base cases:
        if(possibleSpeeds == 0 || days == 0)
            return 0;
        else if (possibleSpeeds == 1)
            return 1;
        else if(days == 1) //
            return possibleSpeeds;

        //More than one day:
        int newMinTests;

        for(int i = 1; i <= possibleSpeeds; i++) {
            //The greatest of either no injury (where days remains the same and next speed is tested)
            //or injury (days is decremented as well as the possible speeds by the previous speeds)
            newMinTests = Math.max(runTrialsRecur(possibleSpeeds - i, days),
                    runTrialsRecur(i - 1, days - 1)) + 1;

            if(newMinTests < minTests)
                minTests = newMinTests;
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
        int[][] minTests = new int[days + 1][possibleSpeeds + 1];

        //Adding base cases
        for(int i = 1; i <= days; i++) {
            minTests[i][0] = 0; //no possibleSpeeds on any day will always be 0
            minTests[i][1] = 1; //only one possibleSpeed will always only yield one test for any amount of days
        }

        for(int i = 1; i <= possibleSpeeds; i++) {
            minTests[1][i] = i; //the possibleSpeeds tested for only 1 day will always be n possibleSpeeds
        }

        int temp;

        for(int i = 2; i <= days; i++) {
            for(int j = 2; j <= possibleSpeeds; j++) {

                minTests[i][j] = Integer.MAX_VALUE;

                for(int k = 1; k <= j; k++) {
                    temp = 1 + Math.max(minTests[i - 1][k - 1], minTests[i][j - k]);
                    if(temp < minTests[i][j]) minTests[i][j] = temp;
                }
            }
        }

        //System.out.println(minTests[days-1][possibleSpeeds-1]);
        return minTests[days][possibleSpeeds];
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
