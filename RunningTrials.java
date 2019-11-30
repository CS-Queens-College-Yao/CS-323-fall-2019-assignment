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
    
    //checks base case of if possibleSpeeds is 0 or 1 or if days is 0 or 1 since will have only 1 day to test up to that speed
    if (possibleSpeeds==1 ||possibleSpeeds ==0 || days ==1 || days ==0) 
    	return possibleSpeeds;
    else 
    	for (int i=1;i<=possibleSpeeds; i++) {
    		 //checks speeds up to that and if it lost a day and if got injured and lost a day and can't train at that speed
    		int max = Math.max(runTrialsRecur(possibleSpeeds-i,days),runTrialsRecur(i-1,days-1));
    		if (minTests < max )
    			minTests = max;
    	}
    
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your optional code here
    int trialArray [][] = new int [possibleSpeeds+1][days+1];
    
    //initializes trialArray
    for (int i=0;i<=possibleSpeeds;i++) {
    	for (int j=0;j<=days;j++) {
    		trialArray[i][j] =-1;
    	}
    }
    //checks base case of if possibleSpeeds is 0 or 1 or if days is 0 or 1 since will have only 1 day to test up to that speed
    if (possibleSpeeds==1 ||possibleSpeeds ==0 || days ==1 || days ==0)
    	return possibleSpeeds;
    //Checks if answer to subproblem already in array
    if (trialArray[possibleSpeeds][days] != -1) {
    	return trialArray[possibleSpeeds][days];
    }
    //checks speeds up to that and if it lost a day and if got injured and lost a day and can't train at that speed
    //and adds answer to subproblem into array if it's not already there
    for (int i=1;i<=possibleSpeeds;i++) {
    	for (int j=1;j<=days;j++) {
    		int max = Math.max(runTrialsRecur(possibleSpeeds-i,days),runTrialsRecur(i-1,days-1));
    		if (trialArray[i][j] < max )
    			trialArray[i][j] = max;
    	}
    }
    minTests = trialArray[possibleSpeeds][days];
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    
    //Creates trialArray which is used as a dp table
    int trialArray[][] = new int[days+1][possibleSpeeds+1];
    
    //Initialized most of the array to 1
    for (int i=2;i<=days;i++) {
    	for (int j=2;j<=possibleSpeeds;j++) {
    	
    		trialArray[i][j]= 1;
    	}
    }
    
    //initialized 0th row to 0s
    for (int j=0; j<possibleSpeeds;j++) {
    	trialArray[0][j] =0;
    }
    //initialized 0th column to 0s
    for (int m=0;m<days;m++) {
    	trialArray[m][0]=0;
    }
    
	//initialized 1st row to all possible speeds 	 
    for (int a =1;a<=possibleSpeeds;a++) {
    	trialArray[1][a]=a;
    }
    
    for (int a=2;a<=days;a++) {
    	for (int b=a+1;b<=possibleSpeeds;b++) {
    		//finds minimun of item that is above and item to the left in trialArray
    		int min = Math.min(trialArray[a-1][b], trialArray[a][b-1]);
    		trialArray[a][b]=1+min;
    	}
    }
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
