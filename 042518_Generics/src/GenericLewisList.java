// This data structure holds a list of integers
 
public class GenericLewisList<T> {
    // data fields
    private int count;   // the number of values I care about
    private T[] values; // the numbers that are stored
     
    // functions
    public GenericLewisList() {
        count = 0;
        values = (T[])(new Object[5]); 
    }
    // returns the capacity of this list
    public int size() {
        return values.length;
    }
    // return true if the list is full, false otherwise
    public boolean isFull() {
        return count == size();
    }
    // return true if the list is empty; false otherwise
    public boolean isEmpty() {
        return count == 0;
    }
    // doubles the capacity of the values list
    public void resize() {
        T[] bigger = (T[])(new Object[size() * 2]);
        for (int i = 0; i < count; i++) {
            bigger[i] = values[i];
        }
        values = bigger;
    }
    // append adds val to the end of values, resizing if necessary
    public void append(T val) {
        if (isFull()) {
            resize();
        }
        values[count] = val;
        count = count + 1;
    }
    // returns the value at location pos
    public T get(int pos) throws Exception {
        if (pos < count) {
            return values[pos];
        } else {
            throw new Exception();
        }
    }
    // returns a string of values' contents separated by spaces
    public String toString() { // called with list
        String result = "";
        for (int i = 0; i < count; i++) {
            result = result + values[i] + " ";
        }
        return result.trim();   
    }
}