
/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashCodeSimilarity {
	private HashTable ht1;
	private HashTable ht2;
	private float vl1;
	private float vl2;
	private int len; 
	
	// Basic constructor
	public HashCodeSimilarity(String s1, String s2, int sLength) {
		len = sLength;
		ht1 = Util.strToHashTable(s1, len);
		ht2 = Util.strToHashTable(s2, len);
		vl1 = vectorLength(ht1);
		vl2 = vectorLength(ht2);
	}
	
	// Returns the VectorLength of S
	public float lengthOfS1() {
		return vl1;
	}
	
	// Returns the VectorLength of T
	public float lengthOfS2() {
		return vl2;
	}
	
	// Returns Similarity(S, T);
	public float similarity() {
		int result = 0;
		
		for(MultiSet ms : ht1.getSets())
			if(ms == null)
				continue;
			else
				result += ms.getElements().size() * ht2.search(ms.getElements().get(0));
		
		return result / (vl1 * vl2);
	}
	
	// Calculate vector length for string
	private float vectorLength(HashTable ht) {
		int result = 0;
		
		for(MultiSet ms : ht.getSets())
			if(ms == null)
				continue;
			else
				result += Math.pow(ms.getElements().size(), 2);
		
		return (float) Math.sqrt(result);
	}
}

