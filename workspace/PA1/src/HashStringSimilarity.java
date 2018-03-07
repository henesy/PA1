import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashStringSimilarity {
	HashTable s; //multiset of all shingles in s1
	HashTable t; //multiset of all shingles in s2
	
	ArrayList<Tuple> s_list; //multiset of all shingles in s1
	ArrayList<Tuple> t_list; //multiset of all shingles in s2
	
	// Basic constructor
	public HashStringSimilarity(String s1, String s2, int sLength) {
		int i;
		String temp;
		for (i = 0; i < s1.length() - sLength; i++) {
			temp = s1.substring(i, i + sLength);
			
		}
	}

	// Returns VectorLength of S
	public float lengthOfS1() {
		//TODO -- page 6/8
		return 0;
	}
	
	// Returns VectorLength of T
	public float lengthOfS2() {
		//TODO -- page 6/8
		return 0;
	}
	
	// Returns Similarity(S, T);
	public float similarity() {
		//TODO -- page 6/8
		return 0;
	}

	public void vectorCounts(String string) {
		// TODO Auto-generated method stub
		
	}
	
}
