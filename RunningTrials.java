/**
 * Running Trials
 * 
 * Author: Mei King Kwok (section 1:40-2:55) , Ding Lin (Section 3:10-4:25)  and Carolyn Yao
 * 
 * Does this compile or finish running within 5 seconds?  (Y)
 */
public class RunningTrials {

  public int runTrialsRecur(int possibleSpeeds, int weeks) 
  {
		if( weeks == 1 || possibleSpeeds == 0 || possibleSpeeds == 1  ) {
			return possibleSpeeds;       // Base Case : if only 1 week,  if 0 or only 1 speed , return 0 or 1 
		}
		
		int minTests = possibleSpeeds;    // set to worst case first 
		int Temp;
		
		for(int i = 1 ; i <= possibleSpeeds ; i++) {
			
			Temp = Math.max(runTrialsRecur(i-1,weeks-1), runTrialsRecur(possibleSpeeds-i,weeks));
			
			if(Temp < minTests) {        // if Temp Max < WorstCase 
				minTests = Temp;
			}
			
		}
		
    return minTests + 1;

  }

  public int runTrialsMemoized(int[][] memo_minTest,int possibleSpeeds,int weeks) 
  {
	  if ( memo_minTest[weeks][possibleSpeeds] != 0) {
		  return memo_minTest[weeks][possibleSpeeds];   // if it already has answer .. 
	  }
	  
	  if( weeks == 1 || possibleSpeeds == 1 || possibleSpeeds == 0 ) { // 3 Base Cases .. 
		  memo_minTest[weeks][possibleSpeeds] = possibleSpeeds;
		  return possibleSpeeds;
	  }
	  
	  int minTests = possibleSpeeds;    // set to worst case first 
	  int Temp;
	  
	  for(int i = 1 ; i <= possibleSpeeds ; i++) {
			
			Temp = Math.max(runTrialsMemoized(memo_minTest,i-1,weeks-1), runTrialsMemoized(memo_minTest,possibleSpeeds-i,weeks));
			
			if(Temp < minTests) {        // if Temp Max < WorstCase 
				minTests = Temp;
			}
		}
	  
	  memo_minTest[weeks][possibleSpeeds] = minTests + 1;  // saving answer .. 

	  return minTests + 1;
  }

  public int runTrialsBottomUp(int possibleSpeeds, int weeks) 
  {
	  int[][] minTests = new int[weeks+1][possibleSpeeds+1];  // row = week , col = # speeds
	  
	  for (int i = 1 ; i <= weeks ;i++) {
		
		  minTests[i][1] = 1;     // Base Case : if only 1 speed
		  minTests[i][0] = 0;     // Base Case : if 0 speed
		  
		  for (int j = 2 ; j <= possibleSpeeds ; j++) {
			  
			  minTests[1][j] = j;   // Base Case : if only 1 week
			  
			  minTests[i][j] = possibleSpeeds;   // set to worst Case first 
			  
			  int temp;
			  
			  for(int k = 1 ; k < j ; k++) {
				  
				  temp = 1 + Math.max(minTests[i-1][k-1], minTests[i][j-k]);
				  
				  minTests[i][j] = Math.min(temp, minTests[i][j]);
			  }
		  }
	  }
	  
    return minTests[weeks][possibleSpeeds];
  }

  public static void main(String args[])
  {
      RunningTrials running = new RunningTrials();
      
      int[][] memo_minTest = new int[6][13];   // for example 1 , 5 weeks, 12 speeds
      
      int minTrials1Recur = running.runTrialsRecur(12, 5);
      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
     
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);

      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}

