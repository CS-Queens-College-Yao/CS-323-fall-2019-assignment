/**

 * Running Trials

 * Author: Senhai Qu, JianWei Kang and Carolyn Yao

 * Does this compile or finish running within 5 seconds? Y/N

 */



public class LeagueOfPatience {



  // Do not change the parameters!

  public int runTrialsRecur(int possibleSpeeds, int days) {

      int minTests = 0;
      //if only one day
      if(days==1) return possibleSpeeds;
      //if  possibleSpeeds<=2 
      else if(possibleSpeeds==2||possibleSpeeds==1) return possibleSpeeds;
      //otherwise call function recursively 
      else {
     	return runTrialsRecur(possibleSpeeds-2,days-1)+1;

    // Your code here

    	}

  }



  // Optional:

  // Pick whatever parameters you want to, just make sure to return an int.

  public int runTrialsMemoized(int possibleSpeeds,int days) {

    int minTests = 0;
    //create a array store result
    int a[][]=new int[possibleSpeeds+1][days+1];
    for(int i=1;i<possibleSpeeds+1;i++)
    	for(int j=1;j<days+1;j++)
    		a[i][j]=-1;
    
    //if array exit a request result where days=1, return it
    if(days==1) return a[possibleSpeeds][1]=possibleSpeeds;
    //if possibleSpeeds<=2 return it and update array value
    else if(possibleSpeeds==2||possibleSpeeds==1) return a[possibleSpeeds][days]=possibleSpeeds;
    
    else {
    	//if a request exit, return it
    	if( a[possibleSpeeds][days]!=-1)
    		return  a[possibleSpeeds][days];
    	//if a request doesn't exit , update array, then return it
    	else a[possibleSpeeds][days]=runTrialsRecur(possibleSpeeds-2,days-1)+1;
    	    return a[possibleSpeeds][days];
    }


    // Your optional code here


    
  }



  // Do not change the parameters!

  public int runTrialsBottomUp(int possibleSpeeds, int days) {

    int minTests = 0;
    //if days==1 return possibleSpeeds
    if(days==1)   minTests=possibleSpeeds;
    else {
    	//count down days and speeds
    	while(days>1&&possibleSpeeds>0) {
    		possibleSpeeds=possibleSpeeds-2;
    		minTests++;
    		days--;
       }
    	//if possibleSpeed=0 need one more test after minTests
       if(possibleSpeeds==0) return minTests+1;
       // if possibleSpeed=-1 return minTests
       else if(possibleSpeeds==-1) return minTests;
       // if day==1 
       else return minTests+possibleSpeeds;
       	
    }	
    // Your code here

    return minTests;

  }



  public static void main(String args[]){

      t2 running = new t2();



      // Do not touch the below lines of code, your output will automatically be formatted

      int minTrials1Recur = running.runTrialsRecur(12, 5);

      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);

      int minTrials2Recur = running.runTrialsRecur(20, 8);

      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
      

      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);

      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom); 
      
      System.out.println("20 speeds, 8 weeks: " + running.runTrialsMemoized(12, 5) + " " + running.runTrialsMemoized(20, 8)); 
      

  }

}