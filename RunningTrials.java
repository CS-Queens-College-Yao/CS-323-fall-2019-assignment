/**
 * Running Trials
 * Author: Ismail Farouqe and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
	  	int minTests = 0;

	  	// code here
	  	
	  	if(possibleSpeeds <= 1)		// If there is less than 1 possible speeds, only 1 test required
	        return days;
	    
	    if(days == 1)	// If there is only 1 day, test all possible speeds
	        return days;
	    
	    if(days == 0)	// If there is 0 day, can't test and return 0
	    	return 0;
	    
	    minTests = Integer.MAX_VALUE;
	    int result = 0;
	    
	    // To find the converging point, comparison between possibleSpeeds = possibleSpeeds - 1 and days = days - 1 
	    for(int i = 1; i <= days; i++)
	    {
	    	result = Math.max(runTrialsRecur(possibleSpeeds - 1, i - 1), runTrialsRecur(possibleSpeeds, days - i));
	    	if(result < minTests)
	    		minTests = result;
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
    
    // Our code here
    
    int array[][] = new int[possibleSpeeds + 1][days + 1];
    
    // Initialization of our Dynamic programming table, 0 for 0th day and 1 for 1st day
    for(int i = 1; i <= possibleSpeeds; i++)
    {
    	array[i][0] = 0;
    	array[i][1] = 1;
    }
    // Filling up for the case when days = 1, and have to run full number of tests.
    for(int i = 1; i <= days; i++)
    {
    	array[1][i] = i;
    }
    
    int result = 0;	// temporary variable for max comparison
    // DP code. Two outer nested loops goes through the DP table of 2D array
    for (int i = 2; i <= possibleSpeeds; i++) 
    { 
        for (int j = 2; j <= days; j++) 
        { 
        	array[i][j] = Integer.MAX_VALUE;	// Max Integer to avoid problems with below comparison set up
        	
            // Same optimal substructure of the recursion code above
            for (int k = 1; k <= j; k++) 
            { 
                 result = 1 + Math.max(array[i - 1][k - 1], array[i][j - k]); 
                 if (result < array[i][j])
                	 array[i][j] = result; 
            } 
        } 
    } 
    minTests = array[possibleSpeeds][days];

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
