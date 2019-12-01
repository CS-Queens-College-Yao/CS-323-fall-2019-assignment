/**
 * Running Trials
 * Author: Jose Rivas and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N 
 * YES: but incorrect output
 *
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
   
    
   //cases
    if(days == 1) {
    	possibleSpeeds--;
    	return ++minTests;
    }
    else { 
     ++minTests;
     return runTrialsRecur(possibleSpeeds - 1, days - 1);
    }
   
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
    
 
    
    // define the range 
    int max = 10; 
    int min = 1; 
    int range = max - min + 1;
    
    while(days >= 1) {
    //generate random number to see if athlete injure at a particular speed.
    //while athlete stills have days left to practice.
    int rand = (int)(Math.random() * range) + min;
    if(rand <= 8) {
    	minTests++;
    	possibleSpeeds--;
    }
    else
    	break;
    days--;	
    }
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
