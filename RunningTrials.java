/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    minTests= Integer.MAX_VALUE;
    if (possibleSpeeds==1 ||possibleSpeeds ==0 || days == 1 ||days==0) 
    	return possibleSpeeds;
    else 
    	for (int i=1;i<possibleSpeeds; i++) {
    		minTests=Math.min(minTests,1+Math.max(runTrialsRecur(possibleSpeeds-i,days),runTrialsRecur(i-1,days-1)));
    	}
    
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your optional code here
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    
    int trialArray[][] = new int[days+1][possibleSpeeds+1];
    
    //trialArray[possibleSpeeds][days] = Integer.MAX_VALUE;
    for (int i=2;i<=days;i++) {
    	for (int j=2;j<=possibleSpeeds;j++) {
    	
    		trialArray[i][j]= Integer.MAX_VALUE;
    	}
    }
    
    for (int j=0; j<possibleSpeeds;j++) {
    	trialArray[0][j] =0;
    }
    
    for (int m=0;m<days;m++) {
    	trialArray[m][0]=0;
    }
    
		/*
		 * for (int i=1; i<possibleSpeeds;i++) { trialArray[i][1] =1; }
		 */
    for (int a =1;a<=possibleSpeeds;a++) {
    	trialArray[1][a]=a;
    }
    for (int a=2;a<=days;a++) {
    	for (int b=1;b<=possibleSpeeds;b++) {
    	
    		
    		//int max = 1+Math.max(trialArray[a-1][b], trialArray[a][b-1]) ;
				/*
				 * if ( max <trialArray[a][b] ) System.out.println(max); trialArray[a][b] = max;
				 */
    		//System.out.println(max);
    		int min = Math.min(trialArray[a-1][b], trialArray[a][b-1]);
    		System.out.println(min);
    		trialArray[a][b]=1+min;
    			//System.out.println(trialArray[a][b]);
    	}
    }
    System.out.println("--------------------------------"); //REMOVE AFTER TESTING!!!!!!!!!!!!
   
    
    minTests = trialArray[days][possibleSpeeds];
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
