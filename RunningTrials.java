/**
 * Running Trials
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */
/**
 * edit by: HangBin Zheng
 * Date: 11/14/19
 *
*/
public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    if (days== 0 || days == 1) {
    	return days;
    	}
   
    if(possibleSpeeds==1) {
    	return days; 
    }
    minTests = Integer.MAX_VALUE;
    int temp;
    for (int i =1;i<=days;i++) {
    	temp =Math.max(runTrialsRecur(possibleSpeeds-1,i-1),runTrialsRecur(possibleSpeeds,days-i));
    	if(temp<minTests)
    		minTests =temp;
    }
    

    return minTests+1;
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
    int table [][]= new int [possibleSpeeds+1][days+1];
    int temp;
    for(int i=1;i<=days;i++) {
    	table[i][1]=1;
    	table[i][0]=0;
    }
    for(int j=1;j<=days;j++){
    	table[1][j]=j;
    	
    }
    for(int i =2;i<=possibleSpeeds;i++) {
    	for(int j =2;j<=days;j++) 
    	{
    		table[i][j]= Integer.MAX_VALUE;
    		for(int k =1;k<=j;k++)
    		{
    			 temp = 1+ Math.max(table[i-1][k-1],table[i][j-k]);
    			 if(temp<table[i][j])
    				 table[i][j]=temp;
    			 minTests=table[i][j];

    		}
    	}
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
