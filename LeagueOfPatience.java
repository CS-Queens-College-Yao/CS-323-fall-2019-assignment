import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * LeagueOfPatience
 * Author: Joyce Tan and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y/N
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
    int[] times = new int[T+1];

    // processed[i] will true if vertex i's shortest time is already finalized
    Boolean[] processed = new Boolean[T+1];
    
    // previousLocation[v] will store the previous location of location v from the shortest path
    int[] previousLocation = new int[T+1];

    // Initialize all distances as INFINITE and processed[] as false and all previous locations to the location itself
    for (int v = 0; v <= T; v++) {
      times[v] = Integer.MAX_VALUE;
      previousLocation[v] = v;
      processed[v] = false;
    }

    // Distance of source vertex from itself is always 0
    times[S] = 0;

    // Find shortest path to all the vertices
    for (int count = 0; count <= T ; count++) {
      // Pick the minimum distance vertex from the set of vertices not yet processed.
      // u is always equal to source in first iteration.
      // Mark u as processed.
      int u = findNextToProcess(times, processed);
      processed[u] = true;

      // Initialize askingTime, nextTime as startTime and nextTimeMins, waitingTime as 0
      Date askingTime = startTime;
      Date nextTime = startTime;
      int nextTimeMins = 0;
      int waitingTime = 0;

      // Update time value of all the adjacent vertices of the picked vertex.
      for (int v = 0; v <= T; v++) {
        // Update time[v] only if is not processed yet, there is an edge from u to v, the waitingTime is  
        // greater than zero and total weight of path from source to v through u plus the waiting time for 
        // the nextQuestTime(in minutes) is smaller than current value of time[v]
        // calculate the next quest start time
        // calculate the difference in mins of the asking time and the next quest start time
        // the next quest start time becomes the next asking time 
        // waitingTime = (how much time you have to complete the game play before the next quest time) - (how long the game play takes)
        // waitingTime greater than 0 means you are able to complete the game time before the next 
        // quest time starts and you have to wait the additional time to start the next quest 
        // update previous location of v = u 
        if (!processed[v] && durations[u][v]!=0 && times[u] != Integer.MAX_VALUE){
        nextTime = getNextQuestTime(askingTime,u,v);
        nextTimeMins = minutesBetween(askingTime,nextTime);
        askingTime = nextTime;
        waitingTime = nextTimeMins - durations[u][v];
          if (waitingTime >= 0 && times[u] + nextTimeMins < times[v]) {
            times[v] = times[u] + nextTimeMins;
            previousLocation[v] = u;
            // code used to check calculations and path, every time shortest path is updated
            // System.out.println("times[] and path for count=" + count + " u=" + u + " v=" + v + " nextTimeMins=" + nextTimeMins + ":");
            // for (int i = 0; i < times.length; i++)
            // System.out.println(i + ": " + times[i] + " minutes, path to " + i + ": "+ previousLocation[i] + "-->" + i);  
            
          }
        }
      }
    }

    printShortestTimes(times);

    // Extra Credit: Code below to print the suggested play path i.e. "2, 4, 3, 5"
    
    // this array will store the suggested play path
    int[] playPath = new int[T+1];
    
    // playPath starts at location T and keeps storing the previous location of location i 
    // until it reaches location S to get the suggested play path
    playPath[0] = T;
    int p = previousLocation[T];
    int pathLength = 1;
    for(int i = 1; i <= T; i++){
        if(p == S) break;
        playPath[i] = p;
        p = previousLocation[p];
        pathLength++;
    }
    playPath[pathLength] = S;
    
    // Print suggested play path from location S to location T 
    System.out.println("Suggested play path online wait time accounted for:");
    for(int j = pathLength; j >= 1; j--)
        System.out.print(playPath[j] + ",");
    System.out.print(playPath[0]);
    
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
