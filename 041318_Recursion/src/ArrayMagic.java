// This demonstrates basic activities with arrays

import java.util.Scanner;

public class ArrayMagic {

	private static int[] numbers;
	private static int count;
	private static int capacity;
	
	// this function will build the array and set it to be empty
	public static void initialize() {
		numbers = new int[capacity]; // create the space
		capacity = 0; // sets it to be empty
	}
	
	// append function
	public static void append(int num) {
		numbers[count] = num;
		count = count + 1;
	} // end of append function
	
	// print numbers function
	public static void printNumbers() {
		for (int i = 0; i < count; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Total capacity? ");
		capacity = sc.nextInt();
		initialize();
		System.out.println("Now lets add some numbers to it... ");
		int num;
		do {
			System.out.print("Add what number (negative to quit)?" );
			num = sc.nextInt();
			if (num >= 0) {
				append(num);
			}
		}while(num >= 0 && numbers.length < capacity -1);
		printNumbers();
	}

}
