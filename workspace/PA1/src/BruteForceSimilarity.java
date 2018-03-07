import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class BruteForceSimilarity {
	// REMINDER -- ONLY allowed Array and ArrayList -- NO SORTING
	private float vl1;
	private float vl2;
	private ArrayList<Tuple> us1;
	private ArrayList<Tuple> us2;
	private int len;
	
	// Basic constructor
	public BruteForceSimilarity(String s1, String s2, int sLength) {
		len = sLength;
		us1 = uniqShingles(Util.strToShingles(s1, len));
		us2 = uniqShingles(Util.strToShingles(s2, len));
		vl1 = vectorLength(us1);
		vl2 = vectorLength(us2);
	}
	
	// Returns the VectorLength of S
	public float lengthOfS1() {
		return vl1;
	}

	// Returns the VectorLength of T
	public float lengthOfS2() {
		return vl2;
	}
	
	// Returns Similarity(S, T)
	public float similarity() {
		float result = 0;
		
		for(Tuple t1 : us1)
			for(Tuple t2 : us2)
				if(t1.getValue().equals(t2.getValue()))
					result += t1.getKey() * t2.getKey();
		
		return result / (vl1 * vl2);
	}

	// Calculate vector length for string ;; us → UniqueShingles
	private float vectorLength(ArrayList<Tuple> us) {		
		float result = 0;
		for(Tuple t : us)
			result += Math.pow(t.getKey(), 2);

		// This truncates, this is bad, but this is in the spec
		return (float) Math.sqrt(result);
	}
	
	// Uniq the shingles and add a count element (useful in maths)	 ;; Value → shingle, Key → count ;; O(n^2)
	private static ArrayList<Tuple> uniqShingles(ArrayList<String> shingles) {
		ArrayList<Tuple> uniqs = new ArrayList<Tuple>();
		
		boolean add = true;
		for(String s : shingles) {
			for(Tuple t : uniqs)
				if(t.getValue().equals(s)) {
					// We have found an existing shingle entry already in uniqs ;; increment and don't add
					t.setKey(t.getKey() + 1);
					add = false;
					break;
				}
			if(add)
				uniqs.add(new Tuple(1, s));
		}
		
		return uniqs;
	}
}
