/**
 * Running Trials
 * Author: Mohanad Osman and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

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

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int days) {
        int minTests = Integer.MAX_VALUE;
        // Like the question says, we're assuming that days >= speeds
        int daysNeeded = 0;
        //Base Cases
        if (possibleSpeeds == 0 || possibleSpeeds == 1) return possibleSpeeds;
        if (days == 1) return possibleSpeeds;
        //Finding worst case of days needed.
        for (int speed = 1; speed <= possibleSpeeds; speed++) {
            daysNeeded = Math.max(runTrialsRecur(speed - 1, days - 1),
                    runTrialsRecur(possibleSpeeds - speed, days));
            if (daysNeeded < minTests) minTests = daysNeeded;
        }
        return minTests + 1;
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
        // Making the DP Table
        int[][] speedAndDays = new int[days+1][possibleSpeeds+1];

        //Putting up the base cases
        for (int s = 1; s <= possibleSpeeds; s++){
            speedAndDays[1][s] = s;
            speedAndDays[0][s] = 0;
        }
        for (int d = 1; d <= days; d++){
            speedAndDays[d][1] = 1;
            speedAndDays[d][0] = 0;
        }

        int solution = 0;

        //Filling the DP Table
        for (int day = 2; day <= days; day++){
            for (int speed = 2; speed <= possibleSpeeds; speed++){
                speedAndDays[day][speed] = Integer.MAX_VALUE;
                for (int check = 1; check <= speed; check++) {
                    solution = 1 + Math.max(speedAndDays[check-1][day-1], speedAndDays[speed-check][day]);
                    if (solution < speedAndDays[speed][day]) speedAndDays[speed][day] = solution;
                }
            }
        }
        minTests = speedAndDays[possibleSpeeds][days];
        return minTests;
    }
}
