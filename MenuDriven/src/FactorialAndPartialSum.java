// 03-28-18 Programming Fundamentals
// This program will be like the homework

import java.util.Scanner;
import java.util.Random;

public class FactorialAndPartialSum {
	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		int choice;
		String doRandom;
		int num, result;
		do { // Show the menu
			System.out.println("Here are your choices: ");
			System.out.println("1. Factorial");
			System.out.println("2. Partial");
			System.out.println("3. Quit");
			System.out.println("Enter the number of your choice: ");
			choice = sc.nextInt();
			if ((choice == 1) || (choice == 2)) {
				System.out.println("Do you want the number generated randomly? ");
				doRandom = sc.next(); // use next to avoid the inputs after the sc.nextInt() due to eol marker
				if (doRandom.equalsIgnoreCase("y")) {
					num = 1 + rnd.nextInt(10);
					System.out.println("I will use the number " + num + ".");
				} else {
					System.out.println("What number should I use? ");
					num = sc.nextInt();
				}
				if (choice == 1) { // compute the factorial
					result = 1;
					for (int i = 1; i <= num; i++) {
						result *= i; // result = result * 1
					}
					System.out.println("The factorial is " + result + ".");
					
				} else { // compute the partial sum
					result = 0;
					for (int i = 1; i <= num; i++) {
						result = result + i;
					}
					System.out.println("The partial sum is " + result + ".");
				}
			} else if (choice != 3) {
				System.out.println("Doofus. Enter a valid choice.");
			}
		} while (choice != 3);
		System.out.println("Thank you for using my program.");
	}

}
