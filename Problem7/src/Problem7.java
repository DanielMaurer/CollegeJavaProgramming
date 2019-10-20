// Danny Maurer
// Problem 7

import java.util.Scanner;

public class Problem7 {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("How many years have you been alive? ");
	int age = sc.nextInt();
	if (age > 65) {
		System.out.println("Wow, you are barely alive. Hope you have a retirement plan.");
	} else if (age > 40) {
		System.out.print("Have fun living through the mid-life crisis. You are middle aged.");
	} else {
		System.out.print("Look at you, so young and adgile. Enjoy it while it lasts");
	}
	sc.close();
	}
}
