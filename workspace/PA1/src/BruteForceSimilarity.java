import java.util.ArrayList;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class BruteForceSimilarity {
	// REMINDER -- ONLY allowed Array and ArrayList -- NO SORTING
	
	private ArrayList<Tuple> s_list; //multiset of all shingles in s1
	private ArrayList<Tuple> t_list; //multiset of all shingles in s2
	
	private ArrayList<Tuple> s_short; //non multiset of all shingles in s1
	private ArrayList<Tuple> t_short; //non multiset of all shingles in s2
	private ArrayList<Tuple> u_short; //union non multiset of all shingles in s1 and s2
	
	private float vls;
	private float vlt;
	
	private static float alpha = 31;
	
	// Basic constructor
	public BruteForceSimilarity(String s1, String s2, int sLength) {
		this.s_list = generateList(s1, sLength);
		this.s_short = generateShortList(this.s_list);
		
		this.t_list = generateList(s2, sLength);
		this.t_short = generateShortList(this.t_list);
		
		this.u_short = generateUnion(this.s_short, this.t_short);
		
		this.vls = lengthOfS1();
		this.vlt = lengthOfS2();
		
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
	
	// Returns the VectorLength of S
	public float lengthOfS1() {
		float running_sum = 0;
		for(Tuple tuple : this.s_short) {
			float count = 0;
			for(Tuple tupl : this.s_list) {
				if(tuple.equals(tupl)) {
					count++;
				}
			}
			running_sum += count * count;
		}
		return ((float) Math.sqrt(running_sum));
	}

	// Returns the VectorLength of T
	public float lengthOfS2() {
		float running_sum = 0;
		for(Tuple tuple : this.t_short) {
			float count = 0;
			for(Tuple tupl : this.t_list) {
				if(tuple.equals(tupl)) {
					count++;
				}
			}
			running_sum += count * count;
		}
		return ((float) Math.sqrt(running_sum));
	}
	
	// Returns Similarity(S, T)
	public float similarity() {
		float running_sum = 0;
		for(Tuple tuple : this.u_short) {
			float s_count = 0;
			float t_count = 0;
			for(Tuple s : this.s_list) {
				if(tuple.equals(s)) {
					s_count++;
				}
			}
			for(Tuple t : this.t_list) {
				if(tuple.equals(t)) {
					t_count++;
				}
			}
			running_sum += s_count * t_count;
		}
		float sim = running_sum / (this.vls * this.vlt);
		return sim;
	}
}

