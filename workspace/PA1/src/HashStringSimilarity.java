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
	
	private float vls;
	private float vlt;
	
	private static float alpha = 31;
	private float[] alphas;
	
	// Basic constructor
	public HashStringSimilarity(String s1, String s2, int sLength) {
		this.alphas = new float[sLength];
		this.alphas[0] = alpha;
		for(int i = 1; i < alphas.length; i++) {
			this.alphas[i] = this.alphas[i-1] * alpha;
		}
		
		this.s_list = generateList(s1, sLength, alphas);
		this.s_short = generateShortList(this.s_list);
		this.s = new HashTable(s1.length()); // Constant time
		
		this.t_list = generateList(s2, sLength, alphas);
		this.t_short = generateShortList(this.t_list);
		this.t = new HashTable(s2.length()); // Constant time
		
		for(Tuple tup : this.s_list) { // Linear time
			this.s.add(tup);
		}
		
		for(Tuple tupl : this.t_list) { // Linear time
			this.t.add(tupl);
		}
		
		this.u_short = generateUnion(this.s_short, this.t_short);
		
		this.vls = lengthOfS1();
		this.vlt = lengthOfS2();
		
	}
	
	public static ArrayList<Tuple> generateList(String list, int sLength, float[] alphas) {
		ArrayList<Tuple> temporary_list = new ArrayList<>();
		for (int i = 0; i <= list.length() - sLength; i++) {
			String temp = list.substring(i, i + sLength);
			int sum = 0;
			for (int j = 0; j < sLength; j++) {
				sum += temp.charAt(j) * alphas[sLength - j - 1]; //Math.pow(alpha, sLength - j - 1);
			}
			Tuple t = new Tuple(Math.abs(sum), temp);
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
			ArrayList<Tuple> list = this.s.search(t.getKey());
			float sqrme = 0;
			for(Tuple tup : list) {
				if(t.equals(tup)) {
					sqrme++;
				}
			}
			running_sum += (sqrme * sqrme);
		}
		return ((float) Math.sqrt(running_sum));
	}
	
	// Returns VectorLength of T
	public float lengthOfS2() {
		float running_sum = 0;
		for(Tuple tup : this.t_short) {
			ArrayList<Tuple> list = this.t.search(tup.getKey());
			float sqrme = 0;
			for(Tuple t : list) {
				if(tup.equals(t)) {
					sqrme++;
				}
			}
			running_sum += (sqrme * sqrme);
		}
		return ((float) Math.sqrt(running_sum));
	}
	
	// Returns Similarity(S, T);
	public float similarity() {
		float running_sum = 0;
		for(Tuple tup : this.u_short) {
			ArrayList<Tuple> l = this.s.search(tup.getKey());
			ArrayList<Tuple> r = this.t.search(tup.getKey());
			float count_l = 0;
			float count_r = 0;
			for(Tuple t : l) {
				if(tup.equals(t)) {
					count_l++;
				}
			}
			for(Tuple t : r) {
				if(tup.equals(t)) {
					count_r++;
				}
			}
			running_sum += (count_l * count_r);
		}
		float sim = running_sum / (this.vls * this.vlt);
		return sim;
	}

	public void vectorCounts(String string) {
		// TODO Auto-generated method stub
	}	
}
