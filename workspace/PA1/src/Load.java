/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class Load implements Comparable<Load> {
	private int val;
	private MultiSet ms;
	
	public Load(MultiSet m) {
		ms = m;
	}
	
	public int getVal() {
		return val;
	}

	public Load increment() {
		// TODO Auto-generated method stub
		val++;
		return this;
	}
	
	public Load decrement() {
		val--;
		return this;
	}
	
	public void setElement(MultiSet m) {
		ms = m;
	}

	public void setVal(int i) {
		val = i;
	}
	
	@Override
	public int compareTo(Load l) {
		return Integer.compare(val, l.getVal());
	}
}
