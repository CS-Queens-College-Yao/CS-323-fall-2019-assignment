/**
 * Running Trials
 * Author: Anthony Ramnarain and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {


    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int weeks) {
        int minTests = 0;
        if(weeks==0) minTests =0;
        else if(possibleSpeeds == 1) minTests= 1;
        else {
            int min1 = runTrialsRecur(possibleSpeeds/2, weeks);
            int min2 = runTrialsRecur(possibleSpeeds/2, weeks-1);
            if(min1 > min2)
                minTests = 1 + min1;
            else
                minTests = 1 + min2;
        }
        return minTests;
    }

    // Optional:
    // Pick whatever parameters you want to, just make sure to return an int.
    public int runTrialsMemoized(int possibleSpeeds, int weeks, int trials[][]) {
        // trials is the temporary storage used.
        if(trials[possibleSpeeds-1][weeks-1] >=0)
            return trials[possibleSpeeds-1][weeks-1];
        int minTests = 0;
        if(weeks==0) {
            minTests =0;
            trials[possibleSpeeds-1][weeks-1] = 0;
        }
        else if(possibleSpeeds == 1) {
            minTests= 1;
            trials[possibleSpeeds-1][weeks-1] = 1;
        }
        else {
            int min1 = runTrialsMemoized(possibleSpeeds/2, weeks, trials);
            int min2 = runTrialsMemoized(possibleSpeeds/2, weeks-1, trials);
            if(min1 > min2)
                minTests = 1 + min1;
            else
                minTests = 1 + min2;
            trials[possibleSpeeds-1][weeks-1] = minTests;
        }
        return minTests;
    }

    // Do not change the parameters!
    public int runTrialsBottomUp(int possibleSpeeds, int weeks) {
        int minTests = 0;
        int[][] trials = new int[possibleSpeeds][weeks];

        for(int i=0;i<possibleSpeeds;i++)
            trials[i][0] = 0;

        for(int j=0;j<weeks;j++)
            trials[0][j] = 1;

        for(int i=1;i<possibleSpeeds; i++) {
            for(int j=1;j<weeks;j++) {
                trials[i][j] = max(trials[i/2][j],trials[i/2][j-1]) + 1;
            }
        }
        minTests = trials[possibleSpeeds-1][weeks-1];

        // Your code here
        return minTests;
    }

    public int max(int a, int b) {
        return a>b ? a : b;
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

        // initialize trials and set values to -1 for each case (N,M)
        int trials[][] = new int[20][8];
        for(int i=0;i<20;i++)
            for(int j=0;j<8;j++)
                trials[i][j] = -1;
        int minTrialsMemoized = running.runTrialsMemoized(20, 8, trials);
        System.out.println("Memoized version: 20 speeds, 8 weeks: " + minTrialsMemoized );


    }
}