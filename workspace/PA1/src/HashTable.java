
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
			int prime = Util.getNextPrime(size);
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
		
		// Resizes the table (see PDF)
		private void resize() {
	        LinkedList<Tuple> toAdd = new LinkedList<Tuple>();
	        for(MultiSet e : sets)
	            if (e != null)
	                for (Tuple t : e.getElements())
	                    toAdd.add(t);
	        sets = new MultiSet[Util.getNextPrime(size() * 2)];
	        size = 0;
	        lsize = 0;
	        for(Tuple t : toAdd)
	            add(t);
	    }
}

