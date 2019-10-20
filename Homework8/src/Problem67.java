// Problems 6 and 7

import java.util.Scanner;

public class Problem67 {
	
	public static int simpleHash(String response, int base) {
		int hash, sum = 0;
		char[] characters = response.toCharArray(); // converts phrase into array
		for(char ch : characters) {
			sum = sum + (int)ch; // gets the characters ascii codes
		}
		hash = sum % base;
		return hash;
	} // end of simpleHash
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a phrase: ");
		String response = sc.nextLine();
		System.out.print("Enter a base: ");
		int base = sc.nextInt();
		int hash = simpleHash(response, base);
		System.out.println("Get your hash: " +hash);
		sc.close();
	} // end of main
} // end of public class
