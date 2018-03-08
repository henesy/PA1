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
		HashTable ht = rollover(strToShingles(s, width), strip(s), width);
		
		return ht;
	}
	
	// Rollover string into shingles ;; maybe merge into strToHashTable
	private static HashTable rollover(ArrayList<String> shingles, String s, int width) {
		int len = s.length();
		HashTable ht = new HashTable(len);
		int α = 31; // As per Piazza
		int MAXSHINGLE = len - width + 1; // number of shingles in string
		int hti = 0; // ht iterator
		int i = 0; // shingle iterator
		int diff = 96; // shift distance ;; as per Piazza
		
		String shingle0 = s.substring(0, width); // first shingle must be calculated for perf.
		ArrayList<Integer> b = strToBases(s);
		int highB = b.get(b.size() - 1); // highest base value
		
		// push the first raw value
		ArrayList<Integer> vals = new ArrayList<Integer>(len);
		vals.add(mkFirstVal(shingle0, b));
		
		// Roll over dead
		for(i = 0; i < MAXSHINGLE - 1; i++) {
			// maybe make a char?
			int c = s.charAt(i + width); // current character
			int prevValue = (s.charAt(i) - diff) * highB; // previous integer value
			int prevRaw = (vals.get(i) - prevValue) * α;
			
			vals.add(prevRaw + c - diff);
		}
		
		// Add to HashTable
		for(i = 0; i < shingles.size(); i++)
			ht.add(new Tuple(vals.get(i), shingles.get(i)));
		
		return ht;
	}
	
	// Should consolidate this into rollover/strToHashTable
	private static int mkFirstVal(String shingle, ArrayList<Integer> b) {
		int result = 0;
		int diff = 96;
		
		byte[] raw = shingle.getBytes();
		int i;
		for(i = 0; i < shingle.length(); i++)
			result += (raw[i] - diff) * b.get(shingle.length() - i - 1);
		
		return result;
	}

	// Convert a string into an AL of base values based on an α
	private static ArrayList<Integer> strToBases(String s) {
		int α = 31; // As per Piazza
		
		ArrayList<Integer> b = new ArrayList<Integer>(s.length());
		b.add(1);
		
		int i;
		for(i = 1; i < s.length(); i++)
			b.add(α * b.get(i - 1));
		
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
