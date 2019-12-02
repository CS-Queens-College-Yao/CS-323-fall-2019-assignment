/**
 * Running Trials
 * Author: Xavier Madonado, Eric Munevar and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

public class RunningTrials {

    // Do not change the parameters!
    public int runTrialsRecur(int possibleSpeeds, int days) {
        int minTests = 0;
//    Your code here
        int maxTest = 0;
//    Base case 1
//    if we have one speed no matter how many days I can only test one speed
        if(possibleSpeeds == 1 || possibleSpeeds == 0)
            return possibleSpeeds;
//    Base Case 2
//    if we have only one day at worst we have to test all speeds
        if(days == 1)
            return possibleSpeeds;


        minTests = Integer.MAX_VALUE;
        for(int i = 1; i <= possibleSpeeds; i++){
//    Test for 2 possible scenarios, injury  and no injury
//    No injury, test the same amount of days and remaining speeds
//    injury, try prev speed and lost a day
//    add a 1 every call is one possible trial
           maxTest = 1 + Math.max(
                   runTrialsRecur(possibleSpeeds - i, days), runTrialsRecur(i- 1, days - 1));

            minTests = Math.min(maxTest,minTests);
        }
// days = eggs  speeds = floors
//      speed days
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
        int maxTest = 0;
        int [][] table = new int[days + 1][possibleSpeeds + 1];

// 		 In order to use bottom up we will use our base cases which are row and column 0 and 1, which are our tree leafs.
        setUpMemoTable(table, possibleSpeeds, days);
//  	Work/solve our way up, this is why we start at day/row 2 and speed/column 2.

        for(int day = 2; day <= days; day++){
            for(int speed = 2; speed <= possibleSpeeds; speed++) {
//    	set current cell to max int to provide a ceiling in order to compare with our new value maxTest for each call

                table[day][speed] = Integer.MAX_VALUE;

//    Test for 2 possible scenarios, injury  and no injury
//    which would be in our table already since we are looking a prev solved cells
//    This is why the third for loop
//    No injury, test the same amount of days and remaining speeds
//    injury, try prev speed and loose a day
//    add a 1 every call is one possible trial

                for(int testSpeed = 1; testSpeed <= speed; testSpeed++){

                    maxTest = 1 + Math.max
                            (table[day][ speed -testSpeed], table[day - 1] [testSpeed - 1]);

                    table[day][speed] = Math.min( table[day][speed], maxTest);
                }
            }
        }

        minTests = table[days][possibleSpeeds];
        
//			used to debug
//        if(table[0].length == 13) {
//            for(int i = 0; i < table.length;i++)
//            {
//                for(int j = 0; j < table[0].length; j++){
//                    System.out.print(table[i][j] + " ");
//                }
//                System.out.println("");
//
//            }
//
//        }


        return minTests;
    }

    private void setUpMemoTable(int[][] table, int possibleSpeeds, int days) {
        //        creat a table to store base cases since we are using bottom up we need the base cases filled first
//               Sppeds
//               0  1  2  3  4  5  6......n
//          d 0  0  0  0  0  0  0  0......0
//          a 1  0  1  2  3  4  5  6......n
//          y 2  0  1  max-int............n
//          s 3  0  1  max-int............n
//            4  0  1  max-int............n
//            5  0  1  max-int............n
//            6  0  1  max-int............n
//            n  0  1  max-int............n
//        the max int cells are set by the caller method.
//        otherwise it's 0 by default and we don't need that

//     Base case 1
//    if we have one speed or 0 speeds no matter how many days we can only test 1/0 speeds
        for (int i = 1; i <= days; i++) {
            table[i][0] = 0;
            table[i][1] = 1;
        }


//    Base Case 2
//    if we have only one day at worst we have to test all speeds
        for (int i = 1; i <= possibleSpeeds; i++) {
            table[1][i] = i;
        }
    }

    public static void main(String args[]){
        RunningTrials running = new RunningTrials();
       

//         Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(12, 5);
        int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
        int minTrials2Recur = running.runTrialsRecur(20, 8);
        int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
        System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
        System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
    }
}
