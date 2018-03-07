
/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class Tuple {
	// Could be made private, but they aren't.
	public Integer key;
	public String value;
	
	// Basic constructor
	public Tuple(int keyP, String valueP) {
		key = keyP;
		value = valueP;
	}
	
	// Returns key
	public Integer getKey() {
		return key;
	}
	
	// Returns value
	public String getValue() {
		return value;
	}
	
	// Basic comparator
	public boolean equals(Object o) {
		Tuple t = (Tuple) o;
		return this.key.equals(t.key) && this.value.equals(t.value);
	}
	
}
