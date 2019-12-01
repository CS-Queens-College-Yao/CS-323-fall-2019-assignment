/**
 * Running Trials
 * Author: Mohebullah Mir, Hishaam Ali, Akshar Patel and Carolyn Yao
 * Does this compile or finish running within 5 seconds? yes
 */

public class RunningTrials {
   
   public int injured(){ //determines if athlete will be injured 
     if (Math.random()<=0.10){ //10% chance at all speeds
       return 1; 
     }
     else return 0;
   }
  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = 0;
    if (possibleSpeeds==0) return 0;
    if (days==1 && injured()==1) return 0;
    if(days>1 && injured()==1) minTests= runTrialsRecur(possibleSpeeds-1, days-1)+0; //if injured and more days left to train, then runtrials for next day with less possible speeds
    minTests= runTrialsRecur( possibleSpeeds-1, days)+1; // if not injured, then add 1 to mintests
    
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int[][] grid = new int[20][10];
  public int runTrialsMemoized(int possibleSpeeds, int days) {
    int minTests = 0;
    
    // Your optional code here
    return minTests;
  }
  public int max(int a, int b){
    if (a > b) return a;
    else return b; 
  }
  
  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    int[][] table= new int[possibleSpeeds][days]; //2d table to store mintests at possible speeds and days
    for( int i=1;i<possibleSpeeds;i++){
      for(int j=1;j<days;j++){
        table[0][0]=0;
        table[0][j]=0; //boundary condition
        table[i][0] =0;
        if(injured() == 0){ table[i][j] = max(table[i-1][j],table[i-1][j-1])+1;} //if not injured, then add 1 test to max of previous speed on same day and previous speed on different day
        else table[i][j] = max(table[i-1][j],table[i-1][j-1]); 
        if (table[i][j] > minTests) minTests=table[i][j]; //mintests will be greatest value from last row
      }
    }
    
    // Your code here
    return minTests; // return maximum from last row
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