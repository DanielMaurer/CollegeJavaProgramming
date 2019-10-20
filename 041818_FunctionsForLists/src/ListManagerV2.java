// Create a full suite of function for working with data

import java.util.Random;

public class ListManagerV2 {
	
	private static int capacity;
	private static int[] numbers;
	private static int count; 
	
	// this function returns true if the list if full and false if it isn't
	public static boolean isFull() {
		return(count == capacity);
	}
	
	// this function returns true if the list is empty and false if it isn't
	public static boolean isEmpty() {
		return(count == 0);
	}
	
	// this function will double the capacity of the list
	public static void resize() {
		capacity = capacity * 2;
		int[] newNumbers = new int[capacity];
		for(int i = 0; i < count; i++) { // O(n) for one nested for loop
			newNumbers[i] = numbers[i];
		}
		numbers = newNumbers;
	}
	
	// this function inserts val at location pos
	public static void insert(int val, int pos) {
		if (isFull()) {
			resize();
		}
		if(pos >= count) { // keep it with the cool kids
			append(val);
		}else {
			for (int i = count; i > pos; i--) {
				numbers[i] = numbers[i-1];
			}
			numbers[pos] = val;
			count = count + 1;
		}
	}
	
	
	// this function adds a value to the end of the list
	// if the list is full then it resizes it. Specifically it doubles its capacity
	public static void append(int num) {
		if(isFull()) {
			resize();
		}
		numbers[count] = num;
		count = count + 1;
	}
	
	// this function prints the values of the list
	public static void print() {
		for (int i = 0; i < count; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	// this function removes the last number
	public static void removeLast() {
		if (!isEmpty()) {
			count = count - 1;
		}
	}
	
	// this function removes all of the numbers
	public static void clear() {
		count = 0;
	}
	
	// this function removes a desired slot
	public static void remove(int pos) {
		if(pos < count) {
			for(int i = pos; i < count - 1; i++)
				numbers[i] = numbers[i+1];
		}
		count = count - 1;
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
		append(17);
		print();
		System.out.println("Count is " + count);
		System.out.println("Capacity is " + capacity);
		System.out.println("Now going to insert a 17 at location 1");
		System.out.println("and a 24 at location 4.");
		insert(17, 1);
		insert(24,4);
		insert(96, 12); // 12 would leave a blank but insert keeps it with the cool kids
		print();
		System.out.println("Now going to remove the last.");
		removeLast();
		print();
		System.out.println("Now going to decimate the list.");
		clear();
		print();
		System.out.println("Here it is.");
		System.out.println("Now going to remove the value at position 4.");
	    remove(4);
	    System.out.println("Here's the resulting list.");
	    print();
	}

}
