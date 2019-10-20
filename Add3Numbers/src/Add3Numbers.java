// 03-26-18 Programming Fundamentals
// This adds three numbers entered by the user, prints the maximum

import java.util.Scanner;

public class Add3Numbers {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num, total = 0, max = 0;
		for (int i = 0; i < 3; i++) {
			System.out.print("Enter a number: ");
			num = sc.nextInt();
			total = total + num;
			if (i == 0) {
				max = num;
			} else if ( num > max) {
				max = num;
			}
		}
		System.out.println("The total was " + total + ".");
		System.out.println("The max was " + max + ".");
		sc.close();
	}

}
