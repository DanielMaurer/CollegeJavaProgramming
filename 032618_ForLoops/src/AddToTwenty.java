// 03-26-18
// This program adds numbers until we get to 20

import java.util.Scanner;

public class AddToTwenty {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num;
		int total = 0; // sum of whats been entered
		int count = 0; // how many entered
		do {
			System.out.print("Enter a number: ");
			num = sc.nextInt();
			total += num;
			count = count + 1;
		} while (total < 20);
	System.out.println("The total is "+ total + ".");
	System.out.println("You entered " + count + " numbers.");
	}
}
