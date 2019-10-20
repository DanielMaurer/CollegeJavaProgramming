// This data structure holds a list of integers

public class LewisList {
	// data fields
	private int count; // number of values that I care about
	private int[] values; // the numbers that are stored
	
	// functions
	public LewisList() {
		count = 0;
		values = new int[5];
	}
	
	// returns the capacity of the list
	public int size() {
		return values.length;
	}
	
	// return true of full, false if otherwise
	public boolean isFull() {
		return count == size();
	}
	
	// return true if list is empty, otherwise false
	public boolean isEmpty() {
		return count == 0;
	}
	
	// doubles the capacity of the values list
	public void resize() {
		int[] bigger = new int[size() * 2];
		for(int i = 0; i < count; i++) {
			bigger[i] = values[i];
		}
		values = bigger;
	}
	
	// append adds val to the end of values, resizing if necessary
	public void append(int val) {
		if(isFull()) {
			resize();
		}
		values[count] = val;
		count = count + 1;
	}
	
	// returns the value at location pos
	public int get(int pos) throws Exception{
		if(pos < count) {
		return values[pos];
		} else {
			throw new Exception();
		}
	}
	
	// returns a string of values contents separated by spaces
	public String toString() { // called by list
		String result = "";
		for(int i = 0; i < count; i++) {
			result = result + values[i] + " ";
		}
		return result.trim();
	}
}









