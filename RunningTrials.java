//Daniel Kopeloff CS323

/**
 * Running Trials
 * Author: Daniel Kopeloff and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    
    // Your code here
    System.out.println("Speeds " + possibleSpeeds);
    System.out.println("Days Remaining " + days);
    System.out.println();
    
    
    // checks to see if its hit base case
   if((days == 1 || days == 2) || possibleSpeeds <= 2 ) {
    	minTests++ ;
    	return minTests;
    }
    else {
    minTests++;
    minTests = minTests + runTrialsRecur(possibleSpeeds / 2 , days - 2);
    
    return minTests;
    }
   
    
  }
  

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds , int days) {
    int minTests = 0;
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    int CurrDay = 1;
    int CurrSpeed = 2 ;
    boolean checker = false;
    
    if (possibleSpeeds % 5 == 0) {
    		checker = true;   
}
   
    	while(CurrSpeed <= possibleSpeeds/2 || CurrDay <= days) {
    		System.out.println("Speeds " +  CurrSpeed);
    	    System.out.println("Current Day " + CurrDay);
    	    System.out.println();
    		minTests++;
    		if(CurrSpeed == 2 ) {
    			CurrSpeed++;
    		}
    		else if (CurrSpeed == 3 && checker) {
    			CurrSpeed = (CurrSpeed * 2) - 1;
    		}
    		else {
    			CurrSpeed*=2;
    		}
    		CurrDay+=2;
    	}
    		
    	    return minTests;
  }

  public static void main(String args[]){
      RunningTrials running = new RunningTrials();

      // Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(12,5); 
      System.out.println("---------");
      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
      System.out.println("------------------------");
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      System.out.println("---------");
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
      System.out.println("12 speeds, 5 days: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println();
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}

