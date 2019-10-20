// Create a full suite of function for working with data

public class ListManager {
	
	private static int capacity;
	private static int[] numbers;
	private static int count; 
	
	public static void append(int num) {
		numbers[count] = num;
		count = count + 1;
	}
	
	public static void print() {
		for (int i = 0; i < count; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	public static void main(String[] args) {
		capacity = 5;
		numbers = new int[capacity];
		count = 0;
		// add values to the numbers array
		append(7);
		append(13);
		append(5);
		append(4);
		append(9);
		append(17); // this will be an issue because I only have 5 slots
		print();
	}

}
