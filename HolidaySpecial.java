import java.awt.List;
import java.util.ArrayList;


/**
 * HolidaySpecial
 * Author: Daniel Kopeloff and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

/**
 * This class implements a scheduler to assign cooks to steps in a special
 * recipe. There are three test cases in the main method. Please read through
 * the whole file before attempting to code the solution.
 *
 * You will only be graded on code you add to the makeShifts method.
 * Do not mess with the existing formatting and identation.
 * You don't need to use the helper methods, but if they come in handy setting
 * up a custom test case, feel free to use them.
 */
public class HolidaySpecial {

  /**
   * The actual greedy scheduler you will be implementing!
   * @param numCooks The number of cooks who can participate, m
   * @param numSteps The number of steps in the recipe, n
   * @param signUpTable An easy lookup tool, signUpTable[x][Y] = cook X signed up or did not sign up for step Y.
   *      Example:
          signUpTable[1][3] = 1 if cook 1 signed up for Step 3
          signUpTable[1][3] = 0 if cook 1 didn't sign up for Step 3
   * @return scheduleTable: a table similar to the signUpTable where scheduleTable[X][Y] = 1 means
   *     cook X is assigned to step Y in an optimal schedule
   */

	public int[][] makeShifts(
    int numCooks,
    int numSteps,
    int[][] signUpTable
  ) {
    // Your scheduleTable is initialized as all 0's so far. Your code will put 1's
    // in the table in the right places based on the return description
    int[][] scheduleTable = new int[numCooks + 1][numSteps + 1];
    int [] steps = new int[numCooks];
    int temp = 0;
    int max = 0;
    int chefIndex = 0;
    
    boolean tester = false;
    boolean noStreak = false;
//    System.out.println(numSteps);
//    System.out.println("i is " + Integer.toString(signUpTable[0].length -1));
//    System.out.println("j is " + Integer.toString(signUpTable.length -1));
    
       
    
//    for(int i = 1 ; i <= signUpTable[0].length -1 ; i ++) 
//	{
//    	
//		for(int j = 1; j <= signUpTable.length - 1; j++) {
//			System.out.print(signUpTable[j][i]);
//			System.out.print(" ");
//			}
//		System.out.println();
//	}
//	
//   System.out.println();
   
   for( int x = 1 ; x <= (signUpTable[0].length -1) ; ){
	   
	   for(int y = 1 ; y <= signUpTable.length - 1; y++) {
		   
		   if(signUpTable[y][x] == 1) {
			  
			                         
			   if((x+1 <= (signUpTable[0].length -1)  &&  signUpTable[y][x+1] == 1) ){
				   temp = x;
					   while(temp <= (signUpTable[0].length -1)  &&  signUpTable[y][temp] == 1){
						   steps[y-1] = steps[y-1] + 1;
						   temp++;
						   
					   
				   }
				 
				   
			   }
			   else if(scheduleTable[y][x] == 0 && !noStreak) {
				 
				   scheduleTable[y][x] = 1;
				   noStreak = true;
			   }
			   if(y == signUpTable.length - 1 || x == (signUpTable[0].length -1) ) {
				   
			   }
			   else {
				
				 
				   for(int r = y ; r <= signUpTable.length - 1 ; r++ ) {
					   		if(tester) {
					   			r = signUpTable.length ;
					   		}
					   		else {
					   			if(r != y)
					   				if(signUpTable[r][x] == 1) {
					   					tester = true;
					   					
					   				}
					   			}
				   }
				   if(!tester) {
					   y = signUpTable.length;
					   tester = false;
				   }
			   }
			   
		   }
		   
	   }
	   
	   for(int z = 0 ; z <= steps.length -1 ; z++) {
		   
		   
		   
		   if(steps[z] > max) {
			   max = steps[z];
			   chefIndex = z;
		   }
	   } 
	   if(noStreak && max >= 1) {
		   for(int t = 0 ; t < signUpTable.length ; t++) {
			   scheduleTable[t][x] = 0;
		   }
	   }
	  
	  
	   for( int s = x  ; s < (max + x) && (max + x) <= (signUpTable[0].length -1) ; s++) {
		
		   scheduleTable[chefIndex+1][s] = 1;
	   } 
	   if(max + x > signUpTable[0].length - 1) {
		   while(x < (signUpTable[0].length -1) ) {
			   scheduleTable[chefIndex+1][x] = 1;
			   x++;
		   }
		 
	   }
	   else {
		   if (max == 0)
			   x = x + max +1  ;
		   else if (x+max >= signUpTable[0].length -1) {
			   x = signUpTable[0].length -1;
			  
		   }
		   else {
			   x = x + max;
			  
		   }
	   }
	 
	    max = 0;
	    
	   	steps = new int[numCooks];
	   	noStreak = false;
	   	
	   

	   }
	   		
	   
	   
   
  
   

   System.out.println();
   System.out.println();
   System.out.println();
    return scheduleTable;
  }

  /**
   * Makes the convenient lookup table based on the steps each cook says they can do
   * @param numSteps the number of steps in the recipe
   * @param cookSignUps cook sign ups ex: {{1, 2, 4}, {3, 5}, {6, 7}}
   * @return a lookup table so if we want to know if cook x can do step y,
      lookupTable[x][y] = 1 if cook x can do step y
      lookupTable[x][y] = 0 if cook x cannot do step y
   */
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

  /**
   * This validates the input data about the recipe step sign-ups.
   * @param numCooks the number of cooks
   * @param numSteps the number of steps
   * @param signUps the data given about which steps each cook can do
   * @return true or false whether the input sign-ups match the given number of
   *    cooks and steps, and whether all the steps are guaranteed at least
   *    one cook.
   */
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

  /**
   * Prints the optimal schedule by listing which steps each cook will do
   * Example output is cook 1: 1, 3, 4
   * @param schedule The table of 0's and 1's of the optimal schedule, where
   *   schedule[x][y] means whether in the optimal schedule cook x is doing step y
   */
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

  /**
   * This sets up the scheduling test case and calls the scheduling method.
   * @param recipeName the name of the recipe to print!
   * @param numCooks the number of cooks
   * @param numSteps the number of steps
   * @param signUps which steps each cook can do, in order of cooks and steps
   */
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

  /**
   * You can make additional test cases using the same format. In fact the helper functions
   * I've provided will even check your test case is set up correctly. Do not touch any of
   * of the existing lines. Just make sure to comment out or delete any of your own test cases
   * when you submit. The three recipe test cases existing in this main method should be
   * the only output when running this file.
   */
  public static void main(String args[]){
    HolidaySpecial sp = new HolidaySpecial();

//    // recipe 1: Example 1 from README, 4 cooks, 8 steps
    int[][] cookSignUps1 = {{2, 3, 4, 5, 6}, {5, 7, 8}, {1, 3, 4, 8}, {1, 5, 7, 8}};
    sp.signUpScheduleShifts("Homemade cranberry bread", 4, 8, cookSignUps1);

    // recipe 2: Example 2 from README, 3 cooks, 6 steps:
    int[][] cookSignUps2 = {{2, 3, 4, 5}, {1, 2, 3, 4}, {1, 2, 4, 5, 6}};
    sp.signUpScheduleShifts("Daal", 3, 6, cookSignUps2);

//    // recipe 3: 6 cooks, 11 steps
    int[][] cookSignUps3 = {{7, 10, 11}, {2, 3, 4, 5, 7}, {1, 5, 10}, {8, 9, 10}, {5, 6, 7, 8}, {1, 3, 4, 8}};
    sp.signUpScheduleShifts("Seafood Paella", 6, 11, cookSignUps3);
   }
}
