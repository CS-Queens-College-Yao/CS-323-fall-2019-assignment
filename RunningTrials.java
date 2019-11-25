/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
	boolean hurt = false;
    if(days == 0) return minTests = 1; 	// trying all speeds in one day
    if(possibleSpeeds == 1) return minTests = 1;  		//base case
    minTests++;
    if(hurt == true)
    	runTrialsRecur(possibleSpeeds-1, days-1);
    else runTrialsRecur(possibleSpeeds-1, days);
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  /*
  public int runTrialsMemoized() {
    int minTests = 0;
	boolean hurt = false;                                           // boolean determining whether or not athlete is hurt
	int[][] testArray = new int[possibleSpeeds][days];				
	testArray[0][0] = 0;
	testArray[1][0] = 1;
	testArray[0][1] = 1;  //initialize base cases								
    if(days == 0) return testArray[possibleSpeeds][0];				// base case for days
    if(possibleSpeeds == 0) return minTests = 1; 	 				// base case for possibleSpeeds
	if(hurt == true)
		runTrialsRecur(possibleSpeeds-1, days-1);   				    // if athelete is hurt decrement speed and day
	else runTrialsRecur(possibleSpeeds-1, days); 					// if athelete is unhurt decrement speed
    minTests++;     												// test complete, increment count
    return minTests; 
  }
  **/

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    int[] testSpeed = new int[possibleSpeeds+1];
    testSpeed[0] = 1;
    for(int i = 1; i < possibleSpeeds; i++)
    	testSpeed[i] = testSpeed[i-1] + 1;
    minTests = testSpeed[possibleSpeeds-1];
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
