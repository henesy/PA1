/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashTable {
		// Table state
		public Integer size;
	
		// Initialize hash table
		public HashTable(int size) {
			//TODO -- page 2/8
			
		}
		
		// Returns maximum load of the hash table
		public int maxLoad() {
			//TODO -- page 2/8
			
		}
		
		// Returns the average load of the hash table
		public float averageLoad() {
			//TODO -- page 2/8
			
		}
		
		// Returns the current size of the hash table
		public int size() {
			//TODO -- page 2/8
			
		}
		
		// Returns the number of distinct Tuples in the hash table
		public int numElements() {
			//TODO -- page 2/8
			
		}
		
		// Returns the load factor which is: numElements()/size()
		public float loadFactor() {
			//TODO -- page 2/8
			
		}
		
		// Adds t to the hash table (see page 2/8)
		public void add(Tuple t) {
			//TODO -- page 2/8 - 3/8
			
		}
		
		// Returns an array list of Tuples whose key == k
		public ArrayList<Tuple> search(int k) {
			//TODO -- page 3/8
			
		}
		
		// Returns the number of times t is in the hash table
		public int search(Tuple t) {
			//TODO -- page 3/8
			
		}
		
		// Removes one occurrence t from the hash table
		public void remove(Tuple t) {
			//TODO -- page 3/8
			
		}

		public static boolean isPrime(int n) {
			for(int i= 2; i<=Math.sqrt(n); i++)
				if (n%i==0)
					return false;
			return true;
		}

		// Generages the next largest prime given a limit
		public static int getNextPrime(int given_limit) {
			int limit, i, j;
			// Bertrand's Postulate states that when given_limit > 3 next prime exists within
			// given_limit < p < (given_limit * 2) - 2
			if (given_limit > 3) {
				limit = (given_limit * 2) - 2;
			} else {
				return Math.ceil()
			}

		}

}
