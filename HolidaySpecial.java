/**
 * HolidaySpecial
 * Author: Raymond Calapatia, Sukharam Gole and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 * Yes
 */

import java.util.Arrays;

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
    scheduleTable=signUpTable;
    int count=0; //Initialize count that counts the number of steps a cook can do
    int[] arrayOfSteps = new int [numCooks+1]; //an array that stores how many steps a cook can do
    int maxStep=0;
    int index=0;//intialize the index where the maximum step is in arrayOfSteps.

    for(int i =1; i<numCooks+1;i++){      //loops through all the cooks
      for(int j = 1; j<numSteps+1;j++){// loops through all the steps.
        if(scheduleTable[i][j]==1){ // if scheduleTable[i][j]==1 a cook can do a step
          count++;                //increment count if a cook can do a step
          arrayOfSteps[i]=count;// store the total amount of count into the arrayOfSteps.
          maxStep=arrayOfSteps[0]; // initilize the maximum step a cook can do to index 0 of arrayOfSteps.
        }
      }
      count=0; //reset count to 0 for another new recipe.
    }

    System.out.println(Arrays.toString(arrayOfSteps)); //this is helpful to see which index which cook can do the most steps.


    for (int x = 0; x < arrayOfSteps.length; x++)// this loop is looking for the index on which cook can do the most step.
    {
      if (maxStep< arrayOfSteps[x])
      {
        maxStep = arrayOfSteps[x];
        index = x;
      }
    }

    if(index==1){//condition if the most step is in index 1 of arrayOfSteps.
      for(int i =1; i<numCooks+1;i++){
        for(int j = 1; j<numSteps+1;j++){
          for(int f = i; f<numCooks;f++) {
            if (scheduleTable[i][j] == scheduleTable[f + 1][j]) {
              scheduleTable[f + 1][j] = 0;
            }
          }
        }
      }
    }
    if(index==numCooks){ //condition if the most step is in the last index of arrayOfSteps.
      for(int i =index; i>0;i--){
        for(int j = 1; j<numSteps+1;j++){
          for(int f = i; f>0;f--) {

            if (scheduleTable[i][j] == scheduleTable[f - 1][j]) {
              scheduleTable[f - 1][j] = 0;
            }

          }
        }
      }

    }
    else{ //condition if the most step is in the middle of the arrayOfStep. At this point we realized finding the most step a cook can do IS NOT the most optimal way.
      for(int i =index; i<numCooks+1;i++){
        for(int j = 1; j<numSteps+1;j++){
          for(int f = i; f<numCooks;f++) {
            if (scheduleTable[i][j] == scheduleTable[f + 1][j]) {
              scheduleTable[f + 1][j] = 0;
            }
            if(scheduleTable[index-1][j]==1 && scheduleTable[f][j]==1){
              scheduleTable[f][j]=0;
            }
          }

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
