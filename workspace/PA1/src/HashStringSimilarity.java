import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashStringSimilarity {
	private HashTable s; //multiset of all shingles in s1
	private HashTable t; //multiset of all shingles in s2
	
	private ArrayList<Tuple> s_list; //multiset of all shingles in s1
	private ArrayList<Tuple> t_list; //multiset of all shingles in s2
	
	private ArrayList<Tuple> s_short; //non multiset of all shingles in s1
	private ArrayList<Tuple> t_short; //non multiset of all shingles in s2
	private ArrayList<Tuple> u_short; //union non multiset of all shingles in s1 and s2
	
	private static float alpha = 31;
	
	// Basic constructor
	public HashStringSimilarity(String s1, String s2, int sLength) {
		this.s_list = generateList(s1, sLength);
		this.s_short = generateShortList(this.s_list);
		this.s = new HashTable(this.s_list.size()); // Constant time
		
		this.t_list = generateList(s2, sLength);
		this.t_short = generateShortList(this.t_list);
		this.t = new HashTable(this.t_list.size()); // Constant time
		
		for(Tuple tup : this.s_list) { // Linear time
			System.out.println(tup.value);
			this.s.add(tup);
		}
		System.out.println();
		for(Tuple tupl : this.t_list) { // Linear time
			System.out.println(tupl.value);
			this.t.add(tupl);
		}
		
		this.u_short = generateUnion(this.s_short, this.t_short);
		
	}
	
	public static ArrayList<Tuple> generateList(String list, int sLength) {
		ArrayList<Tuple> temporary_list = new ArrayList<>();
		for (int i = 0; i <= list.length() - sLength; i++) {
			String temp = list.substring(i, i + sLength);
			int sum = 0;
			for (int j = 0; j < sLength; j++) {
				sum += temp.charAt(j) * Math.pow(alpha, sLength - j - 1);
			}
			Tuple t = new Tuple(sum, temp);
			temporary_list.add(t);
		}
		return temporary_list;
	}
	
	public static ArrayList<Tuple> generateShortList(ArrayList<Tuple> list) {
		ArrayList<Tuple> ret = new ArrayList<>();
		for(Tuple t : list) {
			if(!ret.contains(t)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	public static ArrayList<Tuple> generateUnion(ArrayList<Tuple> s_short, ArrayList<Tuple> t_short) {
		ArrayList<Tuple> u_short = new ArrayList<>();
		for(Tuple tupl : s_short) {
			if(!u_short.contains(tupl)) {
				u_short.add(tupl);
			}
		}
		for(Tuple tup : t_short) {
			if(!u_short.contains(tup)) {
				u_short.add(tup);
			}
		}
		return u_short;
	}
	
	// Returns VectorLength of S
	public float lengthOfS1() {
		float running_sum = 0;
		for(Tuple t : this.s_short) {
			running_sum += Math.pow(this.s.search(t), 2);
		}
		return ((float) Math.sqrt(running_sum));
	}
	
	// Returns VectorLength of T
	public float lengthOfS2() {
		float running_sum = 0;
		for(Tuple tup : this.t_short) {
			running_sum += Math.pow(this.t.search(tup), 2);
		}
		return ((float) Math.sqrt(running_sum));
	}
	
	// Returns Similarity(S, T);
	public float similarity() {
		float running_sum = 0;
		for(Tuple tup : this.u_short) {
			float l = this.s.search(tup);
			float r = this.t.search(tup);
			running_sum += (l * r);
		}
		float sim = running_sum / (this.lengthOfS1() * this.lengthOfS2());
		return sim;
	}

	public void vectorCounts(String string) {
		// TODO Auto-generated method stub
	}	
}
