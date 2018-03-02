
public class Tuple {
	Integer key;
	String value;
	
	// Basic constructor
	Tuple(int keyP, String valueP){
		key = keyP;
		value = valueP;
	}
	
	// Returns key
	Integer getKey(){
		return key;
	}
	
	// Returns value
	String getValue(){
		return value;
	}
	
	// Basic comparator
	Boolean equals(Tuple t){
		return this.key.equals(t.key) && this.value.equals(t.value);
	}
	
}
