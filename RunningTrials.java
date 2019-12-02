import java.awt.Point;
import java.util.Map;
import java.util.Random;

/**
 * Running Trials Author: George Kaouris
  Does this compile or finish
 * running within 5 seconds? Yes
 */

public class RunningTrials {

	// random number generator for simulating chance of an injury
	private Random rand = new Random();

	// Do not change the parameters!
	public int runTrialsRecur(int possibleSpeeds, int days) {
		int minTests = 0;
		// base case:
		if(possibleSpeeds == 0 || days == 0) {
			return 0;
		}
		// get whether at current practice there is any injury
		boolean injury = rand.nextBoolean();
		// recursion call
		if(injury) {
			minTests = 1 + runTrialsRecur(possibleSpeeds, days - 1);
		}
		else {
			minTests = 1 + runTrialsRecur(possibleSpeeds - 1, days);
		}
		return minTests;
	}

	// Optional:
	// Pick whatever parameters you want to, just make sure to return an int.
	public int runTrialsMemoized(int possibleSpeeds, int days, Map<Point, Integer> memory) {
		int minTests = 0;
		// find solution if already there in memory
		Point pt = new Point(possibleSpeeds, days);
		if(memory.containsKey(pt)) {
			minTests = memory.get(pt);
		}
		else if (possibleSpeeds == 0 || days == 0) {
			memory.put(pt, 0);
			memory.put(pt, minTests);
		}
		else {
			// get whether at current practice there is any injury
			boolean injury = rand.nextBoolean();
			if (injury) {
				minTests = 1 + runTrialsMemoized(possibleSpeeds, days - 1, memory);
			} else {
				minTests = 1 + runTrialsMemoized(possibleSpeeds - 1, days, memory);
			}
			memory.put(pt, minTests);
		}
		return minTests;
	}

	// Do not change the parameters!
	public int runTrialsBottomUp(int possibleSpeeds, int days) {
		int minTests = 0;
		while(possibleSpeeds > 0 && days > 0) {
			minTests++;
			boolean injury = rand.nextBoolean();
			if(injury) {
				days--;
			}else {
				possibleSpeeds--;
			}
		}
		return minTests;
	}

	public static void main(String args[]) {
		RunningTrials running = new RunningTrials();

		// Do not touch the below lines of code, your output will automatically be
		// formatted
		int minTrials1Recur = running.runTrialsRecur(12, 5);
		int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
		int minTrials2Recur = running.runTrialsRecur(20, 8);
		int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
		System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
		System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
	}
}
