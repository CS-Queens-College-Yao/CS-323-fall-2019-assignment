/**

 * HolidaySpecial

 * Author: Mei King Kwok (section 1:40-2:55) , Ding Lin (Section 3:10-4:25)  and Carolyn Yao

 * Does this compile or finish running within 5 seconds?  (Y)

 */

public class HolidaySpecial {

  public int[][] makeShifts( int numCooks, int numSteps, int[][] signUpTable) 
  {

    int[][] scheduleTable = new int[numCooks + 1][numSteps + 1];
    
    int RecipesStep = 1;
    int start = 1;
    int end = 0;
    
    while(RecipesStep <= numSteps) {   // current recipes step does not exceeds max number of steps
    	
    	int cook = 0 ;                // keep track of Cook # 
    	int Max_continue = 0;           // keep track of most continue # of steps 
    	
    	for(int i = 1 ; i <= numCooks ; i++) {
    		
    		int current_continue = 0;
    		
    		for(int j = start ; j <= numSteps ; j++ ) {
    			if(signUpTable[i][j] == 1)        // if cook i signed up for step j
    				current_continue++;
    			else
    				break;
    		}
    		
    		if(current_continue > Max_continue) {
    			Max_continue = current_continue;
    			cook = i;                 // cook # = [i] in signUpTable
    		}
    	}
    	
    	RecipesStep = RecipesStep + Max_continue;
    	end = end + Max_continue;
    	
    	for(int s = start ; s <= end; s++) {
    		scheduleTable[cook][s] = signUpTable[cook][s];   // update the cook # does steps S to scheduleTable  
    	}
    	
    	start = end + 1;
    }
    
    return scheduleTable;
  }

  public int[][] makeSignUpLookup(int numSteps, int[][] cookSignUps) {

    int numCooks = cookSignUps.length;

    int[][] lookupTable = new int[numCooks + 1][numSteps + 1];

    for (int cook = 1; cook <= numCooks; cook++) {

      int[] signedUpSteps = cookSignUps[cook - 1];

      for (int i = 0; i < signedUpSteps.length; i++) {

        lookupTable[cook][signedUpSteps[i]] = 1;

      }

    }

    return lookupTable;

  }

  public boolean inputsValid(int numCooks, int numSteps, int signUps[][]) {

    int cookSignUps = signUps.length;

    // Check if there are any cooks or signups

    if (numCooks < 1 || cookSignUps < 1) {

      System.out.println("You either did not put in any cook or any signups");

      return false;

    }

    // Check if the number of cooks and sign-up rows matches

    if (numCooks != cookSignUps) {

      System.out.println("You input " + numCooks + " cooks but your signup suggests " + signUps.length);

      return false;

    }


    // Check that all steps are guaranteed in the signups

    int[] stepsGuaranteed = new int[numSteps + 1];

    for (int i = 0; i < cookSignUps; i++) {

      for (int j = 0; j < signUps[i].length; j++) {

        stepsGuaranteed[signUps[i][j]] = 1;

      }

    }

    for (int step = 1; step <= numSteps; step++) {

      if (stepsGuaranteed[step] != 1) {

        System.out.println("Your signup is incomplete because not all steps are guaranteed.");

        return false;

      }

    }

    return true;

  }
  
  public void printFinalSchedule(int[][] schedule) {

    for (int cook = 1; cook < schedule.length; cook++) {

      int[] curcookSchedule = schedule[cook];

      System.out.print("cook " + cook + ": ");

      for (int step = 1; step < curcookSchedule.length; step++) {

        if (curcookSchedule[step] == 1) {

          System.out.print(step + " ");

        }

      }

      System.out.println("");

    }

  }

  public void signUpScheduleShifts(String recipeName, int numCooks, int numSteps, int[][] signUps) {

    System.out.println("-----" + recipeName + "-----");

    if (!inputsValid(numCooks, numSteps, signUps)) {

      System.out.println("recipe signup info is invalid");

      return;

    }

    int[][] signUpsLookup = makeSignUpLookup(numSteps, signUps);

    int[][] schedule = makeShifts(numCooks, numSteps, signUpsLookup);

    printFinalSchedule(schedule);

    System.out.println("");

  }

  public static void main(String args[]){

    HolidaySpecial sp = new HolidaySpecial();

    // recipe 1: Example 1 from README, 4 cooks, 8 steps
    
    int[][] cookSignUps1 = {{2, 3, 4, 5, 6}, {5, 7, 8}, {1, 3, 4, 8}, {1, 5, 7, 8}};

    sp.signUpScheduleShifts("Homemade cranberry bread", 4, 8, cookSignUps1);

    // recipe 2: Example 2 from README, 3 cooks, 6 steps:

    int[][] cookSignUps2 = {{2, 3, 4, 5}, {1, 2, 3, 4}, {1, 2, 4, 5, 6}};

    sp.signUpScheduleShifts("Daal", 3, 6, cookSignUps2);

    // recipe 3: 6 cooks, 11 steps

    int[][] cookSignUps3 = {{7, 10, 11}, {2, 3, 4, 5, 7}, {1, 5, 10}, {8, 9, 10}, {5, 6, 7, 8}, {1, 3, 4, 8}};

    sp.signUpScheduleShifts("Seafood Paella", 6, 11, cookSignUps3);

  }

}