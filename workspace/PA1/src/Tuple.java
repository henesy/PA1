
/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class Tuple {
	// Could be made private, but they aren't.
	private Integer key;
	private String value;
	
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
	
	// Sets key
	public void setKey(int k) {
		key = k;
	}
	
	// Sets value
	public void setValue(String v) {
		value = v;
	}
	
	// Basic comparator
	public boolean equals(Object o) {
		Tuple t = (Tuple) o;
		return this.key.equals(t.key) && this.value.equals(t.value);
	}
	
}
