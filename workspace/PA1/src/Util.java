import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

/* Utility functions for PA1 */
public class Util {
	// Convert a string into HashTable using strToShingles ;; HT is the most performant DS allowed
	public static HashTable strToHashTable(String s, int width) {
		// Stripping is necessary for college if not for the spec
		HashTable ht = new HashTable(s.length());
		ArrayList<String> shingles = strToShingles(s, width);
		ArrayList<Integer> vals = rollover(strip(s), width);
		
		// We put the values and shingles into the HashTable
		int i = 0;
		for(String shingle : shingles) {
			ht.add(new Tuple(vals.get(i), shingle));
			i++;
		}
		
		return ht;
	}
	
	// Rollover string into shingles ;; maybe merge into strToHashTable
	private static ArrayList<Integer> rollover(String s, int width) {
		int len = s.length();
		int a = 31;
		int diff = 96;
		int MAXSHINGLES = len - width + 1; // maximum number of shingles
		
		// First shingle must be calculated for performance 
		String shingle0 = s.substring(0, width);
		ArrayList<Integer> b = strToBases(shingle0);
		int MAXBASE = b.get(b.size() - 1);
		
		// Push the first value
		ArrayList<Integer> vals = new ArrayList<Integer>(MAXSHINGLES);
		vals.add(Util.mkFirstVal(shingle0, b));

		// Roll over dead
		int i;
		for (i = 0; i < MAXSHINGLES - 1; i++) {
			char c = s.charAt(i + width);
			int prevVal = (s.charAt(i) - diff) * MAXBASE;
			int prevRaw = (vals.get(i) - prevVal) * a;
		
		    vals.add(prevRaw + c - diff);
		}

		return vals;
	}
	
	// Extracts from a shingle the index of bases
	private static int mkFirstVal(String shingle, ArrayList<Integer> b) {
		int result = 0;
		int diff = 96;
		
		byte[] raw = shingle.getBytes();
		int i;
		for(i = 0; i < shingle.length(); i++)
			result += (raw[i] - diff) * b.get(shingle.length() - i - 1);
		
		return Math.abs(result);
	}

	// Convert a string into an AL of base values based on an α
	private static ArrayList<Integer> strToBases(String s) {
		int a = 31; // As per Piazza
		
		ArrayList<Integer> b = new ArrayList<Integer>(s.length());
		b.add(1);
		
		int i;
		for(i = 1; i < s.length(); i++)
			b.add(a * b.get(i - 1));
		
		return b;
	}
	
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
