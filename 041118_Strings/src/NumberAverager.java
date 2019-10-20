// This program generates, prints, and averages random numbers

import java.util.Scanner;
import java.util.Random;

public class NumberAverager {
	public static void generateNumbers(int[] nums, int minNum, int maxNum) {
		Random rnd = new Random();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = minNum + rnd.nextInt(maxNum - minNum);
		}
	} // end of generateNumbers
	
	public static void printNumbers(int[] nums) {
		for (int num : nums) {
			System.out.println(num);
		}
	} // end of printNumbers
	
	public static double findAverage(int[] nums) {
		double total;
		total = 0;
		for (int num : nums) {
			total = total + num;
		}
		double result = total / nums.length;
		return result;
	} // end of findAverage
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of numbers: ");
		int numNums = sc.nextInt();
		System.out.print("Enter min and max: ");
		int minNum = sc.nextInt();
		int maxNum = sc.nextInt();
		int [] numbers = new int[numNums];
		generateNumbers(numbers, minNum, maxNum); // fill the slots of numbers with random values
		printNumbers(numbers);
		double average = findAverage(numbers);
		System.out.printf("The average is %.2f\n.", average);
		sc.close();
	}

}
