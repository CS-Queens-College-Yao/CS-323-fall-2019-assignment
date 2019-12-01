/**
 * HolidaySpecial
 * Author: Hector Suazo and Carolyn Yao
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

    // Your code here
    
    /*
    //Prints signUpTable array for debug
    System.out.println("signUpTable");
    for(int i = 1; i < signUpTable.length; i++) {
    	System.out.print("[");
    	for(int j = 1; j < signUpTable[0].length; j++) {
    		System.out.print(" " + signUpTable[i][j] + " ");
    	}
    	System.out.println("]");
    }
    */
    
    
    
    //Non-optimal greedy method
    int numSwitches = 0;	//Probably not needed
    int currCook = 0;
    
    //Assigns an initial cook for step 1
	for(int i = 1; i < numCooks; i++) {
		if(signUpTable[i][1] == 1) {
			currCook = i;
			scheduleTable[currCook][1] = 1;
			//System.out.println("Assigning step 1 to cook " + currCook);	//DEBUG
			break;
		}
		else {
			//NOTHING
		}
	}
	
	//Assigns all remaining steps to all remaining cooks
	for(int i = 2; i <= numSteps; i++) {	//Iterates through steps
		//Checks to see if current cook can do following step, i.
		if(signUpTable[currCook][i] == 1) {
			scheduleTable[currCook][i] = 1;
			//System.out.println("Assigning step " + i + " to cook " + currCook);	//Debug
		}
		else {
			//Checks to see which cook, j, can do the following step, i
			for(int j = 1; j <= numCooks; j++) {	//Iterates through cooks
				if(signUpTable[j][i] == 1) {
					currCook = j;
					scheduleTable[currCook][i] = 1;
					numSwitches++;
					break;
				}
			}
			//System.out.println("Assigning step " + i + " to cook " + currCook);	//Debug
		}
	}
	
	
	/*
	//Prints scheduleTable array for debug
	System.out.println("scheduleTable");
    for(int i = 1; i < scheduleTable.length; i++) {
    	System.out.print("[");
    	for(int j = 1; j < scheduleTable[0].length; j++) {
    		System.out.print(" " + scheduleTable[i][j] + " ");
    	}
    	System.out.println("]");
    	
    }
    System.out.println();
	*/
    
	/*
    //Optimal Greedy Algorithm
    int currStep = 1;
    int countStep = 0;
    int maxStep = 0;
    int maxCook = 0;
    int[] maxStepArr = new int[numCooks+1];
    for(int i = 0; i <= numCooks; i++) {
    	maxStepArr[i] = 0;
    }
    boolean scheduleDone = false;
    
    while(scheduleDone != true) {
    	//Searches to see which cook can do most consecutive steps by filling maxStepArr
    	//System.out.println("Search starting at currStep: " + currStep);		//Debug
    	for(int cook = 1; cook <= numCooks; cook++) {
    		for(int step = currStep; step <= numSteps; step++) {
    			if(signUpTable[cook][step] == 1) {
    				countStep++;
    				//System.out.println("Cook " + cook + " can do step " + step + " (" + countStep + ")");		//Debug
    			}
    			else {
    				//System.out.println("maximum steps for cook " + cook + " is " + countStep);	//Debug
    				//System.out.println("test- currStep: " + currStep + ", countStep: " + countStep + ", step: " + step);	//Debug
    				maxStepArr[cook] = countStep;
    				countStep = 0;
    				break;
    			}
    		}
    	}
    	
    	/*	//Debug prints maxStepArr
    	for(int i = 1; i < maxStepArr.length; i++) {
    		System.out.println("maxStepArr[" + i + "]: " + maxStepArr[i]);
    	}
    	*/
    	
		/*
    	//Finds maxCook and maxSteps from maxStepArr[]
    	System.out.println("Finding maxCook and maxSteps");
    	for(int cook = 1; cook <= numCooks; cook++) {
    		if(maxStepArr[cook] > maxStep) {
    			//System.out.println("Cook " + cook + " is current max cook");		//Debug
    			maxCook = cook;
    			maxStep = maxStepArr[cook];
    			
    		}
    	}
    	//System.out.println("maxCook is: " + maxCook + " with " + maxStep + " steps");	//Debug
    	
    	//Debug - prints variables
    	//System.out.println("currStep: " + currStep);
    	//System.out.println("maxStep: " + maxStep);
    	//System.out.println("scheduleTable filled from steps " + currStep + 
    					   " to " + (currStep+maxStep-1) + 
    					   " for cook " + maxCook);
		
		
		
    	//Fills scheduleTable with the cook that can do the max number of consecutive steps
    	for(int step = currStep; step < (currStep + maxStep); step++) {
    		scheduleTable[maxCook][step] = 1;
    		//System.out.println("scheduleTable filled");	Debug
    	}
    	
    	//Resets some variables and gets them ready for next while loop
    	currStep = currStep + maxStep;
    	maxCook = 0;
    	maxStep = 0;
    	countStep = 0;
    	for(int i = 0; i <= numCooks; i++) {
        	maxStepArr[i] = 0;
        }
    	//System.out.println("currStep: " + currStep);	Debug
    	//System.out.println("numSteps: " + numSteps);	Debug
    	
    	//Checks if all steps were checked to exit while loop
    	if(currStep >= numSteps) {
    		scheduleDone = true;
    		//System.out.println("scheduleTable done");	//Debug
    	}
    }
    */
    
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
