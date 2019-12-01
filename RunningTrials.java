/**
 * Running Trials
 * Author: Marlon Amedee and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N (Yes)
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    int temp = 0;
    
    for (int i=0; i<days; i++)
    	for (int j=0; j<possibleSpeeds; j++) 
    	{
    		if (j!=i) 
    		{
    			temp = Math.min( runTrialsRecur(j, i), runTrialsRecur(j-1, i-1) ) + 1;
    			minTests = temp;
    		}
    		else {
    			
    			temp = Math.min( runTrialsRecur(j-1, i), runTrialsRecur(j, i-1) ) + 1;
    			minTests = temp;
    		}
    	}

    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  // 
  public int runTrialsMemoized() {
    int minTests = 0;
    // Your optional code here
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    // Not working properly, getting an overflow error
//    int Arr[][] = new int[possibleSpeeds+1][days+1];
//    
//    // When possibleSpeeds==0 we can't run trials so that entire row is 0s. Ex (0,0)=0, (0,1)=0,.....
//    for(int i=0;i<days;i++) {
//    	Arr[0][i] = 0;
//    }
//    // When days==0 we can't run trials so that entire column is 0s. Ex (0,0)=0, (1,0)=0,.....
//    for(int i=0;i<possibleSpeeds;i++) {
//    	Arr[i][0] = 0;
//    }
//    
//    // When days==1 and/or possiblSpeeds==1 we can run at least 1 trial (we get hurt on speed 1 and can't continue). Ex (1,1)=1, (1,2)=1, (2,1)=1,....
//    for(int i=1;i<possibleSpeeds;i++) {
//    	Arr[i][1] = 1;
//    }
//    for(int i=1;i<days;i++) {
//    	Arr[1][i] = 1;
//    }
//    //
//    
//    for(int i=2; i<=days; i++)
//    	for(int j=2; j<=possibleSpeeds; j++) 
//    	{   
//    		if(i!=j) 
//    		{
//    			Arr[i][j] = Math.min( runTrialsBottomUp(j,i) , runTrialsBottomUp(j-1,i-1) ) + 1;
//    			minTests = Arr[i][j];
//    			}
//    		else {
//    			Arr[i][j] = Math.min( runTrialsBottomUp(j-1,i), runTrialsBottomUp(j,i-1) ) + 1;
//    			minTests = Arr[i][j];
//    			}
//    	}

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
