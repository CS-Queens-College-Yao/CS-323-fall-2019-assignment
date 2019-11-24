import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * LeagueOfPatience
 * Author: Your Name and Carolyn Yao
 * Does this compile or finish running within 5 seconds? Y
 */

/**
 * This class contains solutions to the League of Patience problem in the
 * myFastestPlay method. There is an existing implementation of a shortest-paths
 * algorithm. As it is, you can run this class and get the solutions from the
 * existing shortest-path algorithm.
 */

public class LeagueOfPatience {

	/**
	 * The algorithm that could solve for shortest play time between location S to
	 * T. It combines the given edge information about actual play time with the
	 * time required to wait for each intermediary quest to become available.
	 *
	 * @param startTime the time you intend to start playing
	 * @param S         the s th location on the game map
	 * @param T         the t th location on the game map
	 * @param durations durations[u][v] Table of how long game play between u and v
	 *                  takes in minutes
	 */
	public void myFastestPlay(int S, int T, Date startTime, int[][] durations) {
		int numVertices = durations.length;

		int[] times = new int[numVertices];
		int[] order = new int[numVertices];
		// order[] will keep track of the path order from S to T
		
		// processed[i] will true if vertex i's shortest time is already finalized
		Boolean[] processed = new Boolean[numVertices];

		// Initialize all distances as INFINITE and processed[] as false
		for (int v = 0; v < numVertices; v++) {
			times[v] = Integer.MAX_VALUE;
			processed[v] = false;
		}

		// Distance of source vertex from itself is always 0
		times[S] = 0;

		// Find shortest path to all the vertices
		for (int count = 0; count <= numVertices - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices not yet processed.
			// u is always equal to source in first iteration.
			// Mark u as processed.
			int u = findNextToProcess(times, processed);
			processed[u] = true;
			order[count] = u;
			// Adds the u to the order array to mark the path order
			if (u == T) // If u happens to be the destination node no more work needs to be done and breaks out of the loop
				break;
			

			// Update time value of all the adjacent vertices of the picked vertex.
			for (int v = 0; v < numVertices; v++) {
				// Update time[v] only if is not processed yet, there is an edge from u to v,
				// and total weight of path from source to v through u is smaller than current
				// value of time[v]
				if (!processed[v] && durations[u][v] != 0 && times[u] != Integer.MAX_VALUE
						&& times[u] + durations[u][v] < times[v]) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(startTime);
					calendar.add(Calendar.MINUTE, times[u]);
					Date timeAtNode = calendar.getTime();
					// Adds the time to get to the node to the start time to get the time at that node

					int timebetween = minutesBetween(timeAtNode, getNextQuestTime(timeAtNode, u, v));
					// Finds the minutes between the time at the node, and the next quest time between it and the adjacent node
					times[v] = times[u] + durations[u][v] + timebetween;
					// Changes the adjacent node to the time of the past node + duration to travel to it + time between now and when it appears
					
				} // end if
			} // end second for
		} // end first for

		
		modifiedPrintShortestTimes(times, startTime, order, S, T);
		printPathTaken(order, T);

	}

	public void modifiedPrintShortestTimes(int times[], Date startTime, int[] order, int S, int T) {
		System.out.println("Play time to advance from " + S + " to " + T); 
		int j = 0;
		do {
			int i = order[j]; // Gets the value which will be what order the path took place in
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);
			calendar.add(Calendar.MINUTE, times[i]);
			Date timeAtNode = calendar.getTime();
			// Adds the time to get to that node to the startTime

			SimpleDateFormat df = new SimpleDateFormat("MMMM dd, HH:mm");
			//format for Month day, Hour:minute for easier understanding

			System.out.println(i + ": " + df.format(timeAtNode)); // Prints the current time at that node
			System.out.println(i + ": " + times[i] + " minutes"); // Prints how many minutes to reach that node from the startingTime

			if (j == times.length - 1) // If j is the last index of the array, it breaks because it was the last one and to prevent OutOfBounds error
				break;
			j++; // increment j to reach next index in order[] array

		} while (order[j] != 0); 
		// do-while is used because the first item is always 0 and we always want it to run.
		// If the path ends early (not going through all the nodes), the next node will be a 0 because 
		// it hasn't changed from when it was initialized and thus the do-while ends
	}
	
	public void printPathTaken(int path[], int endNode) {
		System.out.println("Path taken");
		for (int i = 0; i < path.length; i++) {
			System.out.print(path[i] + " ");
			if (path[i] == endNode)
				break;
		}
		System.out.println();
	}

	/**
	 * This function is simulating the game's API that you can request the closest
	 * quest start time from. For example, if you enter that you will get to the
	 * quest at 13:45, it could give back something like 16:02 (Date object) as the
	 * next quest time.
	 * 
	 * @param askingTime the time at which the user needs to know when the next
	 *                   quest is
	 * @param u          Look up quest starting point
	 * @param v          Look up quest endpoint
	 * @return the next quest time as a Date object
	 */
	public Date getNextQuestTime(Date askingTime, int u, int v) {
		int minutesUntilNext = (int) (Math.random() * ((30) + 1) + (v - u));
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
	 * 
	 * @param times     The shortest times from the source
	 * @param processed boolean array tells you which vertices have been fully
	 *                  processed
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
	 * 
	 * @param graph  The connected, directed graph in an adjacency matrix where if
	 *               graph[i][j] != 0 there is an edge with the weight graph[i][j]
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
		for (int count = 0; count < numVertices - 1; count++) {
			// Pick the minimum distance vertex from the set of vertices not yet processed.
			// u is always equal to source in first iteration.
			// Mark u as processed.
			int u = findNextToProcess(times, processed);
			processed[u] = true;

			// Update time value of all the adjacent vertices of the picked vertex.
			for (int v = 0; v < numVertices; v++) {
				// Update time[v] only if is not processed yet, there is an edge from u to v,
				// and total weight of path from source to v through u is smaller than current
				// value of time[v]
				if (!processed[v] && graph[u][v] != 0 && times[u] != Integer.MAX_VALUE
						&& times[u] + graph[u][v] < times[v]) {
					times[v] = times[u] + graph[u][v];
				}
			}
		}

		printShortestTimes(times);
	}

	public static void main(String[] args) {
		/* duration(e) */
		int playTimeGraph[][] = { { 0, 10, 21, 0, 0, 0 }, { 0, 0, 21, 10, 0, 0 }, { 0, 0, 0, 25, 0, 78 },
				{ 0, 0, 16, 0, 11, 0 }, { 0, 0, 22, 16, 0, 28 }, { 0, 0, 0, 0, 0, 0 }, };
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
