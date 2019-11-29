/**
 * Running Trials
 * Author: Kyle Tapang and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */
import java.lang.Math;

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
	int minTests = Integer.MAX_VALUE;
	
	//If there are 0 speeds, then you can't run a test. Therefore the minTests would be 0
	if(possibleSpeeds == 0){return 0;}
	//If there's only 1 speed, then you would only need to run one test. Therefore the minTests would be 1
	if(possibleSpeeds == 1){return 1;}
	//If there's only 1 day to test, you must test all possibleSpeeds safely.
	if(days == 1){return possibleSpeeds;}
	
	int temp; 
	
	for(int i = 1; i <= possibleSpeeds; i++){
		temp = 1 + Math.max(runTrialsRecur(i - 1, days - 1), runTrialsRecur(possibleSpeeds - i, days));
		if(temp < minTests) minTests = temp;
	}
	
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int possibleSpeeds, int days) {
	int[][] minTests = new int[days + 1][possibleSpeeds + 1];
	
	for(int i = 1; i <= days; i++){
		minTests[i][0] = 0;
		minTests[i][1] = 1;
	}
	
	for(int i = 1; i <= possibleSpeeds; i++){
		minTests[1][i] = i;
	}
	
	int temp;
	
	for(int i = 2; i <= days; i++){
		for(int j = 2; j <= possibleSpeeds; j++){
			minTests[i][j] = runTrialsMemoized(j, i, minTests);		
		}			
	}

    return minTests[days][possibleSpeeds];
  }
  
  public int runTrialsMemoized(int possibleSpeeds, int days, int[][] minTests){
	  int temp;
	  minTests[days][possibleSpeeds] = Integer.MAX_VALUE;
	  
	  for(int i = 1; i < possibleSpeeds; i++){
		temp = 1 + Math.max(minTests[days - 1][i - 1], minTests[days][possibleSpeeds - i]);
		if(temp < minTests[days][possibleSpeeds]) minTests[days][possibleSpeeds] = temp;
	}
	return minTests[days][possibleSpeeds];
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
	int[][] minTests = new int[days + 1][possibleSpeeds + 1];
	
	for(int i = 1; i <= days; i++){
		minTests[i][0] = 0;
		minTests[i][1] = 1;
	}
	
	for(int i = 1; i <= possibleSpeeds; i++){
		minTests[1][i] = i;
	}
	
	int temp;
	
	for(int i = 2; i <= days; i++){
		for(int j = 2; j <= possibleSpeeds; j++){
			
			minTests[i][j] = Integer.MAX_VALUE;
			
			for(int k = 1; k <= j; k++){
				temp = 1 + Math.max(minTests[i - 1][k - 1], minTests[i][j - k]);
				if(temp < minTests[i][j]) minTests[i][j] = temp;
			}
		}			
	}

    return minTests[days][possibleSpeeds];
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