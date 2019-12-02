import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * LeagueOfPatience
 * Author:Chuhui Chen and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

/**
 * This class contains solutions to the League of Patience problem in the
 * myFastestPlay method. There is an existing implementation of a
 * shortest-paths algorithm. As it is, you can run this class and get the solutions
 * from the existing shortest-path algorithm.
 */

public class LeagueOfPatience {

  /**
   * The algorithm that could solve for shortest play time between location S
   * to T. It combines the given edge information about actual play time with the
   * time required to wait for each intermediary quest to become available.
   *
   * @param startTime the time you intend to start playing
   * @param S the s th location on the game map
   * @param T the t th location on the game map
   * @param durations durations[u][v] Table of how long game play between u and v takes in minutes
   */
  public void myFastestPlay(
    int S,
    int T,
    Date startTime,
    int[][] durations
  ) {
    // This is the array where we'll store all the final shortest times
    int[] times = new int[durations.length];
    // Your code along with comments here. Use the genericShortest function for reference.
    // You want to do similar things as the generic shortest function, except you want
    // to account for the time until the next quest time at each arrival at a location.
    // Feel free to borrow code from any of the existing methods.
    // You will find the getNextQuestTime method and the minutesBetween method helpful.
    // You can also make new helper methods.
    int end= T;
    int start = S;
    int numVertices = durations.length;
 
    // processed[i] will true if vertex i's shortest time is already finalized
    Boolean[] processed = new Boolean[numVertices];
    // we store our path in path
    String path="";
    //store value in our minimal path in pathBloack
    int[][] pathBlock = new int[numVertices][numVertices];

     //Update cost with wait time
     for(int i=0;i<numVertices;i++)
       for(int j=0;j<numVertices;j++){
         int wait = minutesBetween(startTime,getNextQuestTime(startTime,i,j))/60;
         if(durations[i][j]!=0)
           durations[i][j]=durations[i][j]+wait;
       }
      //using from variable as row to update pathBlock
     int from;

     for (int v = 0; v < numVertices; v++) {
       times[v] = Integer.MAX_VALUE;
       processed[v] = false;
      }
     // Distance of source vertex from itself is always 0
     times[S] = 0;
     // Find shortest path to all the vertices
     for (int count = 0; count < numVertices - 1 ; count++) {
     // Pick the minimum distance vertex from the set of vertices not yet processed.
     // u is always equal to source in first iteration.
     // Mark u as processed.
       S = findNextToProcess(times, processed);
       processed[S] = true;
       from = S;
     // Update time value of all the adjacent vertices of the picked vertex.
       for (int v = 0; v < numVertices; v++) {
        // Update time[v] only if is not processed yet, there is an edge from u to v,
        // and total weight of path from source to v through u is smaller than current value of time[v]
        // we are also going to update pathBlock in order to track actual path using the same logic.
         if (!processed[v] && durations[S][v]!=0 && times[S] != Integer.MAX_VALUE && times[S]+durations[S][v]< times[v]) {
           times[v] = times[S] + durations[S][v];
           pathBlock[from][v]=times[v];
         }
        }
      }
     // start from the destination, trackthe path backward until i get to the starting point
     // we also need a count variable in case there is no path from start to end.
     
     int count=0;
     //keep updating path until we reach end
     while(end != start){
     // If there is no path exists, count is going to be greater than numVertices
     // in that case, we are going to break the loop
       if(count>numVertices) {
         path="no path";
         break;
       }
     //inital possible to store the minimal value in pah
       int possible = Integer.MAX_VALUE;
     //append stage we visited to path
       path=" -> "+ end+path;	
     //find the minimal cost path
       for(int i=0;i<numVertices;i++) {
     // If there exist a path and cost is less than possible
     // we are going to store that value in possible
     // we are also going to update end for the next iteration
         if(pathBlock[i][end]!= 0&&pathBlock[i][end]<possible) {
           possible=pathBlock[i][end];
           end=i;
         }
       }
       count++;
      }
     // if no path exists, do not append starting point
     // append otherwise
     if(!path.equals("no path"))
       path = start+path;
     printShortestTimes(times);


    // Extra Credit: Code below to print the suggested play path i.e. "2, 4, 3, 5"
    System.out.println("Suggested play path : ");
    System.out.println(path);
    printShortestTimes(times);
  }

  /**
   * This function is simulating the game's API that you can request the closest
   * quest start time from. For example, if you enter that you will get to the
   * quest at 13:45, it could give back something like 16:02 (Date object)
   * as the next quest time.
   * @param askingTime the time at which the user needs to know when the next quest is
   * @param u Look up quest starting point
   * @param v Look up quest endpoint
   * @return the next quest time as a Date object
   */
  public Date getNextQuestTime(Date askingTime, int u, int v) {
    int minutesUntilNext = (int) (Math.random() * ((30) + 1) + (v-u));
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(askingTime);
    calendar.add(Calendar.MINUTE, minutesUntilNext);
    return calendar.getTime();
  }

  /**
   * This finds the minute difference between two time (Date) objects.
   */
  public int minutesBetween(Date time1, Date time2) {
    return (int) (time2.getTime() - time1.getTime()) / 1000;
  }

  /**
   * Finds the vertex with the minimum time from the source that has not been
   * processed yet.
   * @param times The shortest times from the source
   * @param processed boolean array tells you which vertices have been fully processed
   * @return the index of the vertex that is next vertex to process
   */
  public int findNextToProcess(int[] times, Boolean[] processed) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int i = 0; i < times.length; i++) {
      if (processed[i] == false && times[i] <= min) {
        min = times[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  public void printShortestTimes(int times[]) {
    System.out.println("Play time to advance to various locations");
    for (int i = 0; i < times.length; i++)
        System.out.println(i + ": " + times[i] + " minutes");
  }

  /**
   * Given an adjacency matrix of a graph, implements
   * @param graph The connected, directed graph in an adjacency matrix where
   *              if graph[i][j] != 0 there is an edge with the weight graph[i][j]
   * @param source The starting vertex
   */
  public void genericShortest(int graph[][], int source) {
    int numVertices = graph[0].length;

    // This is the array where we'll store all the final shortest times
    int[] times = new int[numVertices];

    // processed[i] will true if vertex i's shortest time is already finalized
    Boolean[] processed = new Boolean[numVertices];

    // Initialize all distances as INFINITE and processed[] as false
    for (int v = 0; v < numVertices; v++) {
      times[v] = Integer.MAX_VALUE;
      processed[v] = false;
    }

    // Distance of source vertex from itself is always 0
    times[source] = 0;

    // Find shortest path to all the vertices
    for (int count = 0; count < numVertices - 1 ; count++) {
      // Pick the minimum distance vertex from the set of vertices not yet processed.
      // u is always equal to source in first iteration.
      // Mark u as processed.
      int u = findNextToProcess(times, processed);
      processed[u] = true;

      // Update time value of all the adjacent vertices of the picked vertex.
      for (int v = 0; v < numVertices; v++) {
        // Update time[v] only if is not processed yet, there is an edge from u to v,
        // and total weight of path from source to v through u is smaller than current value of time[v]
        if (!processed[v] && graph[u][v]!=0 && times[u] != Integer.MAX_VALUE && times[u]+graph[u][v] < times[v]) {
          times[v] = times[u] + graph[u][v];
        }
      }
    }

    printShortestTimes(times);
  }

  public static void main (String[] args) {
    /* duration(e) */
    int playTimeGraph[][] = {
      {0, 10, 21, 0, 0, 0},
      {0, 0, 21, 10, 0, 0},
      {0, 0, 0, 25, 0, 78},
      {0, 0, 16, 0, 11, 0},
      {0, 0, 22, 16, 0, 28},
      {0, 0, 0, 0, 0, 0},
    };
    LeagueOfPatience t = new LeagueOfPatience();
    try {
      Date date = new SimpleDateFormat("hh:mm").parse("14:45");
      System.out.println("Online wait time NOT accounted for: ");
      t.genericShortest(playTimeGraph, 0);
      System.out.println("Online wait time accounted for: ");
      t.myFastestPlay(0, 5, date, playTimeGraph);
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    // You can create a test case for your implemented method for extra credit below
  }
}
