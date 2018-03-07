
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class HashTable {
		// Table state
		private MultiSet[] sets;
		private PriorityQueue<Load> loads;
		private int size;
		private int lsize;
		HashFunction hashfunc;
		
	
		// Required -- Initialize hash table
		public HashTable(int size) {
			int prime = getNextPrime(size);
			sets = new MultiSet[prime];
			hashfunc = new HashFunction(prime);
			loads = new PriorityQueue<Load>(Collections.reverseOrder());
		}
		
		// Required -- Returns maximum load of the hash table
		public int maxLoad() {
			if(loads.isEmpty())
				return 0;
			return loads.peek().getVal();
		}
		
		// Required -- Returns the average load of the hash table
		public float averageLoad() {
			if(lsize == 0)
				return 0;
			return (float) size / lsize;
		}

		// Required -- Returns the current size of the hash table
		public int size() {
			return sets.length;
		}
		
		// Required -- Returns the number of distinct Tuples in the hash table
		public int numElements() {
			return size;
		}
		
		// Required -- Returns the load factor which is: numElements()/size()
		public float loadFactor() {
			return (float) size / sets.length;
		}
		
		// Required -- Adds t to the hash table (see page 2/8)
		public HashTable add(Tuple t) {
			int h = hashfunc.hash(t.key);
			MultiSet ms = sets[h];
			if(ms == null)
				sets[h] = new MultiSet();
			boolean dup = sets[h].getElements().contains(t);
			if(!dup)
				size++;
			
			// Remove → Add → Increment load deterministically ;; Don't care about adding duplicates to the set
			sets[h].add(t);
			Load l = sets[h].getLoad();
			
			// Remove from queue
			loads.remove(l);
			
			// Add to queue
			if(l.getVal() == 0)
				lsize++;
			loads.add(l);
			
			// Increment load if needed
			if(!dup)
	        	l.increment();
			
			// Resize table if needed
			if(loadFactor() >= 0.7)
				resize();
			return this;
		}
		
		// Required -- Returns an array list of Tuples whose key == k
		public ArrayList<Tuple> search(int k) {
			MultiSet s = sets[hashfunc.hash(k)];
			if(s == null)
				return new ArrayList<Tuple>();
			return s.getElements();
		}
		
		/* SLATED FOR REMOVAL
		public MultiSet searchElems(int k) {
			return sets[hashfunc.hash(k)];
		}
		*/

		// Required -- Returns the number of times t is in the hash table
		public int search(Tuple t) {
			return search(t.key).size();
		}
		
		// Required -- Removes one occurrence t from the hash table
		public void remove(Tuple t) {
			MultiSet ms = sets[hashfunc.hash(t.key)];
			if(ms == null)
				return;
			ms.getElements().remove(t);
			if(!ms.getElements().contains(t)) {
				size--;
				if(ms.getLoad().decrement().getVal() == 0)
					lsize--;
			}
		}

		// Tests if an integer is prime
		private static boolean isPrime(int n) {
			for(int i= 2; i<=Math.sqrt(n); i++)
				if (n%i==0)
					return false;
			return true;
		}	
		
		// Resizes the table (see PDF)
		private void resize() {
	        LinkedList<Tuple> toAdd = new LinkedList<Tuple>();
	        for(MultiSet e : sets)
	            if (e != null)
	                for (Tuple t : e.getElements())
	                    toAdd.add(t);
	        sets = new MultiSet[getNextPrime(size() * 2)];
	        size = 0;
	        lsize = 0;
	        for(Tuple t : toAdd)
	            add(t);
	    }
		
		/* SLATED FOR REMOVAL
		private MultiSet[] getElements() {
			return sets;
		}
		*/
		
		// Generates the next largest prime given a limit
		private static int getNextPrime(int given_limit) {
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

