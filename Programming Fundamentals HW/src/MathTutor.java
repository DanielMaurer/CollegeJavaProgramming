// Danny Maurer
// Homework 7
// This program will increase your basic math skills ten fold!

import java.util.Scanner;
import java.util.Random;

public class MathTutor {
	public static void main(String[] args) {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		int choice, problems, max;
		do { // First lets print the menu
			System.out.println();
			System.out.println("********************************");
			System.out.println("Struggling in Math, let me help!");
			System.out.println("********************************");
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1. Addition ");
			System.out.println("2. Multiplication ");
			System.out.println("3. Both Addition and Multiplication ");
			System.out.println("4. This is no use, I'm a lost cause. ");
			System.out.println();
			System.out.print("What is your selection? ");
			choice = sc.nextInt();
			if (choice != 4) {
				System.out.print("How many practice problems do you want? ");
				problems = sc.nextInt();
				System.out.print("What is the largest number you would like to work with? ");
				max = sc.nextInt();
				
			int add1, add2, mult1, mult2, sum, product, ans, correct, mixed;
			double average;
			correct = 0;
			if (choice == 1) { // Process of doing application
				for(int i = 1; i <= problems; i ++) {
					add1 = 1 + rnd.nextInt(max);
					add2 = 1 + rnd.nextInt(max);
					sum = add1 + add2;
					System.out.print(+add1+ "+" +add2+ " = ");
					ans = sc.nextInt();
					if(sum == ans) {
						System.out.println("Correct! Look at you go.");
						correct = correct + 1;	
					} else {
						System.out.println("Incorrect. The answer is " +sum+ ".");
					}				
				}
			System.out.println("You answered " +correct+ " out of " +problems+ " correctly.");
			average = (double) correct/problems*100;
			System.out.println("Your average was " +average+ "%.");
			
			
			} else if (choice == 2) { // Process to do multiplication
				for(int i = 1; i <= problems; i ++) {
					mult1 = 1 + rnd.nextInt(max);
					mult2 = 1 + rnd.nextInt(max);
					product = mult1 * mult2;
					System.out.print(+mult1+ "*" +mult2+ " = ");
					ans = sc.nextInt();
					if(product == ans) {
						System.out.println("Correct! Look at you go.");
						correct = correct + 1;
					} else {
						System.out.println("Incorrect. The answer is " +product+ ".");
					}
					
					}
				System.out.println("You answered " +correct+ " out of " +problems+ " correctly.");
				average = (double) correct/problems*100;
				System.out.println("Your average was " +average+ "%.");
			} else if (choice == 3) {
				for(int i = 1; i <= problems; i ++) {
					mixed = rnd.nextInt(2);
					if (mixed == 0 ) { // Do addition
						add1 = 1 + rnd.nextInt(max);
						add2 = 1 + rnd.nextInt(max);
						sum = add1 + add2;
						System.out.print(+add1+ "+" +add2+ " = ");
						ans = sc.nextInt();
						if(sum == ans) {
							System.out.println("Correct! Look at you go.");
							correct = correct + 1;	
						} else {
							System.out.println("Incorrect. The answer is " +sum+ ".");
						}
					} else { // do multiplication
						mult1 = 1 + rnd.nextInt(max);
						mult2 = 1 + rnd.nextInt(max);
						product = mult1 * mult2;
						System.out.print(+mult1+ "*" +mult2+ " = ");
						ans = sc.nextInt();
						if(product == ans) {
							System.out.println("Correct! Look at you go.");
							correct = correct + 1;
						} else {
							System.out.println("Incorrect. The answer is " +product+ ".");
						}
					}
				}
				System.out.println("You answered " +correct+ " out of " +problems+ " correctly.");
				average = (double) correct/problems*100;
				System.out.println("Your average was " +average+ "%.");
			} else {
				System.out.println("What are you thinking man, wrong choice?");
			}
			}
		} while (choice != 4);
		System.out.println("Thank you for using math tutor. Have a great day!");
		
	}	// quit out the public static void main
} // quit out the MathTutor
