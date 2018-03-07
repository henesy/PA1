/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

/* Utility functions for PA1 */
public class Util {
	// Tests if an integer is prime
	public static boolean isPrime(int n) {
		for(int i= 2; i<=Math.sqrt(n); i++)
			if (n%i==0)
				return false;
		return true;
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
