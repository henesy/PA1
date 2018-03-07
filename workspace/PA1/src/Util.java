import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

/* Utility functions for PA1 */
public class Util {
	// Strip strings (just in case)
	public static String strip(String s) {
		return s.replaceAll("[^A-Za-z0-9]+", "").toLowerCase();
	}
	
	// Fuzzy Arithmetic Match
	public static boolean fuzzyMatch(float a, float b, float t) {
		return Math.abs(a - b) < t;
	}
	
	// Tests if an integer is prime
	public static boolean isPrime(int n) {
		for(int i= 2; i<=Math.sqrt(n); i++)
			if (n%i==0)
				return false;
		return true;
	}
	
	// Converts a string into an ArrayList of shingles of size 'width'
	public static ArrayList<String> strToShingles(String str, int width) {
		// String the string, spec says assume, but shak? aren't processed
		String s = strip(str);
		
		ArrayList<String> a = new ArrayList<String>(s.length() - width + 1);
		
		int i;
		for(i = 0; i < s.length() - width + 1; i++)
			a.add(s.substring(i, i + width));
		
		return a;
	}
	
	// Generates the next largest prime given a limit
	public static int getNextPrime(int given_limit) {
		int limit = 0;
		// Bertrand's Postulate states that when given_limit > 3 next prime exists within
		// given_limit < p < (given_limit * 2) - 2 (which is == limit).
		if (given_limit > 3)
			limit = (given_limit * 2) - 2;
		else if (given_limit == 2 || given_limit == 3)
			return (int) Math.ceil(given_limit / 2) + given_limit;

		for (int i = given_limit; i <= limit; i++)
			if (isPrime(i))
				return i;

		return 3;
	}
}
