// Danny Maurer

import java.util.Random;
import java.util.Scanner;



public class Problem8910 {
	
	public static int findRange(int[] numbers, int length){
		int max, min, range;
		max = numbers[0];
		min = numbers[0];
		for (int i = 0; i < length; i++) 
			if (numbers[i] > max) { // compares max to location, replaces if larger
				max = numbers[i];
		} else if (numbers[i] < min) { // compares min to location, replaces if smaller
			min = numbers[i];
		}
		range = max - min;
		return range;
	} // end of findRange
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("How many numbers would you like to find the range of: ");
		int length = sc.nextInt();
		int [] numbers = new int[length];
		
		Random rnd = new Random();
		for (int i = 0; i < length; i++) { // generates the list with max of 25
			numbers[i] = rnd.nextInt(25);
			System.out.println(numbers[i]);
		} // end of for loop
		
		int range = findRange(numbers, length); // finds the max and min to calculate range
		System.out.println("The range of the list is "+ range + ".");
		sc.close();
	} // end of main

} // end of public class
