
/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashCodeSimilarity {
	private String s1;
	private String s2;
	private int len; 
	
	// Basic constructor
	public HashCodeSimilarity(String s1, String s2, int sLength) {
		//TODO -- verify no more calculation is required (page 6/8)
		len = sLength;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	// Returns the VectorLength of S
	public float lengthOfS1() {
		//TODO -- page 6/8
		return 0;
	}
	
	// Returns the VectorLength of T
	public float lengthOfS2() {
		//TODO -- page 6/8
		return 0;
	}
	
	// Returns Similarity(S, T);
	public float similarity() {
		//TODO -- page 6/8
		return 0;
	}
	
	// Calculate vector length for string
	private float vectorLength() {
		//TODO
		return 0;
	}

}
