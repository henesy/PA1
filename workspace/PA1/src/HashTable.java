
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
		private MultiSet[] set;
		private PriorityQueue<Load> loads;
		private int size;
		private int lsize;
		HashFunction hash;
		
	
		// Initialize hash table
		public HashTable(int size) {
			int prime = getNextPrime(size);
			set = new MultiSet[prime];
			hash = new HashFunction(prime);
			loads = new PriorityQueue<Load>(Collections.reverseOrder());
		}
		
		// Returns maximum load of the hash table
		public int maxLoad() {
			if(loads.isEmpty())
				return 0;
			return loads.peek().getVal();
		}
		
		// Returns the average load of the hash table
		public float averageLoad() {
			if(lsize == 0)
				return 0;
			// Cast to float?
			return size / lsize;
		}

		// Returns the current size of the hash table
		public int size() {
			return set.length;
		}
		
		// Returns the number of distinct Tuples in the hash table
		public int numElements() {
			return size;
		}
		
		// Returns the load factor which is: numElements()/size()
		public float loadFactor() {
			// Cast to float?
			return size / set.length;
		}
		
		// Adds t to the hash table (see page 2/8)
		public HashTable add(Tuple t) {
			int h = hash.hash(t.key);
			MultiSet ms = set[h];
			if(ms == null)
				set[h] = new MultiSet();
			boolean dup = set[h].getElements().contains(t);
			if(!dup)
				size++;
			indexLoad(set[h].add(t), dup);
			if(this.needResize())
				resize();
			return this;
		}
		
		// Returns an array list of Tuples whose key == k
		public ArrayList<Tuple> search(int k) {
			//TODO -- page 3/8
			return null;
		}
		
		// Returns the number of times t is in the hash table
		public int search(Tuple t) {
			//TODO -- page 3/8
			return 0;
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
		
		// Index Loads
		private HashTable indexLoad(MultiSet e, boolean duplicated) {
			// TODO -- REFACTOR
	        return removeLoad(e.getLoad())
	                .addLoad(e.getLoad(), duplicated)
	                .incrementLoad(e.getLoad(), duplicated);
	    }
		
		private HashTable addLoad(Load l, boolean duplicated) {
			// TODO Auto-generated method stub
			if(l.getVal() == 0)
					lsize++;
	        loads.add(l);
	        return this;
		}

		public HashTable removeLoad(Load l) {
	        loads.remove(l);
	        return this;
	    }
		
		public HashTable incrementLoad(Load l, boolean duplicated) {
	        if(!duplicated)
	        	l.increment();
	        return this;
	    }
		
		private void resize()
	    {
	        LinkedList<Tuple> toAdd = new LinkedList<Tuple>();
	        for (MultiSet e : set)
	            if (e != null)
	                for (Tuple t : e.getElements())
	                    toAdd.add(t);
	        set = new MultiSet[getNextPrime(size() * 2)];
	        size = 0;
	        lsize = 0;
	        for (Tuple t : toAdd)
	            add(t);
	    }
		
		private boolean needResize() {
			return this.loadFactor() >= 0.7;
		}

		// Generates the next largest prime given a limit
		public static int getNextPrime(int given_limit) {
			int limit = 0;
			// Bertrand's Postulate states that when given_limit > 3 next prime exists within
			// given_limit < p < (given_limit * 2) - 2 (which is == limit).
			if (given_limit > 3) {
				limit = (given_limit * 2) - 2;
			} else if (given_limit == 2 || given_limit == 3) {
				return (int) Math.ceil(given_limit / 2) + given_limit;
			}
			for (int i = given_limit; i <= limit; i++) {
				if (isPrime(i)) {
					return i;
				}
			}
			return 3;
		}

}
