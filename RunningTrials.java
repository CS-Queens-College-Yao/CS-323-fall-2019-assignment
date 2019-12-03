/**
 * Running Trials
 * Author: Md Hassan and Faruq Ahmed and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
    int minTests = possibleSpeeds * 2;
    // Your code here
    int day = days * 7;
    int repeatNum = possibleSpeeds * 2;
    float repeatDay = (float)day/(float)repeatNum;
    System.out.println(repeatDay);
    int repeatSpeed = 1;
    int currentDay = 1;
    boolean flag = false;
    int breakDay = 0;
    
    while (currentDay < day-repeatDay) {
        currentDay = (int)Math.floor(repeatDay * (repeatSpeed - 1)) + 1;
        if (repeatSpeed > possibleSpeeds) {
            repeatSpeed = 1;
            currentDay = (int)Math.ceil(repeatDay * possibleSpeeds);
            System.out.println(currentDay + " --> break");
            System.out.println(currentDay + " --> relax");
            breakDay = currentDay;
            currentDay++;
            flag = true;
        } else {
            if (flag == true) {
                currentDay = (int)Math.floor(repeatDay * (repeatSpeed - 1)) + breakDay + 1;
            }            
            int durDay = (int)(Math.ceil(repeatDay * repeatSpeed) - Math.floor(repeatDay * (repeatSpeed - 1)));            
            runTrialsMemoized(durDay, currentDay, repeatSpeed);
            repeatNum--;
            repeatSpeed++;
        }        
    }
    return minTests;
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized(int repeat, int day, int speed) {
    int minTests = 0;
    // Your optional code here
    for ( int i = 0; i < repeat; i ++) {
        System.out.println(day + " --> " + speed);
        day++;
    }
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = possibleSpeeds + 1;
    // Your code here
    return minTests;
  }

  public static void main(String args[]){
      RunningTrials running = new RunningTrials();

      // Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(6, 2);
      int minTrials1Bottom = running.runTrialsBottomUp(6, 2);
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
  }
}

