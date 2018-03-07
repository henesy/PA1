import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Sean Hinchee seh@iastate.edu
 * @author Tyler Fenton tjfenton@iastate.edu
 */

public class MultiSet {
	/* this is a list (copies are allowed) */
	private ArrayList<Tuple> list;
    private Load load;

    public MultiSet() {
    	list = new ArrayList<Tuple>();
    	load = new Load(this);
    }

	public Load getLoad() {
		return load;
	}
	
	public ArrayList<Tuple> getElements() {
        return list;
    }
	
	public void setElements(ArrayList<Tuple> elems) {
		this.list = elems;
	}

	public MultiSet add(Tuple t) {
		list.add(t);
		return this;
	}
	
	public MultiSet incrementLoad() {
		load.setVal(load.getVal() + 1);
		return this;
	}
	
	public MultiSet indexLoad() {
		return this;
	}
	
	public void setLoad(Load load) {
		this.load = load;
	}
	
}
