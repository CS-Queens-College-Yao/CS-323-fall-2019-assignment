/**
 * Running Trials
 * Author: Arhum Khan and Carolyn Yao
 * Does this compile or finish running within 5 seconds?
 */

public class RunningTrials {

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int days) {
        int minTests = 0;
        //should be big number

        //one day (base case) return all the speeds

        //more than one day
        //go through all possibleSpeeds
            //newMinTest is 1 + the greatest of either no injury, or injury
            //compare with minTests and replace with newMinTest if it's, well, less tests

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
