/**
 * Author: Brian Schlapp and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

/**
 * This class implements a scheduler to assign cooks to steps in a special
 * recipe. There are three test cases in the main method. Please read through
 * the whole file before attempting to code the solution.
 *
 * You will only be graded on code you add to the makeShifts method.
 * Do not mess with the existing formatting and indentation.
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
		// Your code goes here
		int cook = 1; 
		int currentStep = 1; 
		int currentCook = 1;
		int step = 1; // used to count how many times something is added to the schedule table
		int conStep;
		int maxConStep;
		int conCook;
		while (step <= numSteps) {

			if (signUpTable[cook][currentStep] == 1) //checks if the selected student can take the current step
			{
				scheduleTable[cook][currentStep] = 1; 
				currentStep++; 
				step++;
			} else 
			{
				if (currentStep != numSteps) {
					maxConStep = 0; 
					conCook = 0; 

					// finds the student that will do the most consecutive steps.
					while (++currentCook <= numCooks) {
						conStep = 0;
						int tempStep = currentStep;
						while (tempStep <= numSteps && signUpTable[currentCook][tempStep] != 0) {
							conStep++;
							if (conStep > maxConStep) { // checks if the temporary conStep is larger than max, if so, it replaces the max
								maxConStep = conStep;
								conCook = currentCook;
							}
							tempStep++;
						}
						
					}
					currentCook = 1;
					cook = conCook; // makes the global student variable take the temporary value
				} else {
					if (cook == numCooks)
						cook = 1; 
					else
						cook++; 
				}
			}
		}
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
	 * Prints the optimal schedule by listing which steps each student will do
	 * Example output is Student 1: 1, 3, 4
	 * 
	 * @param schedule The table of 0's and 1's of the optimal schedule, where
	 *                 schedule[x][y] means whether in the optimal schedule student
	 *                 x is doing step y
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
	 * You can make additional test cases using the same format. In fact the helper
	 * functions I've provided will even check your test case is set up correctly.
	 * Do not touch any of of the existing lines. Just make sure to comment out or
	 * delete any of your own test cases when you submit. The three experiment test
	 * cases existing in this main method should be the only output when running
	 * this file.
	 */
	public static void main(String args[]) {
		HolidaySpecial pe = new HolidaySpecial();

		// recipe 1: Example 1 from README, 4 cooks, 8 steps:
		int[][] cookSignUps1 = { {1, 3, 4, 8}, {2, 3, 4, 5, 6}, {5, 7, 8}, {1, 5, 7, 8} };
		pe.signUpScheduleShifts("Homemade cranberry bread", 4, 8, cookSignUps1);

		// recipe 2: Example 2 from README, 3 cooks, 6 steps
		int[][] cooksSignUps2 = { {2, 3, 4, 5}, {1, 2, 3, 4}, {1, 2, 4, 5, 6} };
		pe.signUpScheduleShifts("Daal", 3, 6, cooksSignUps2);

		// recipe 3: Another test case, 6 cooks, 11 steps
		int[][] cooksSignUps3 = { {7, 10, 11}, {2, 3, 4, 5, 7}, {1, 5, 10}, {8, 9, 10}, {5, 6, 7, 8}, {1, 3, 4, 8} };
		pe.signUpScheduleShifts("Seafood Paella", 6, 11, cooksSignUps3);
	}

}
