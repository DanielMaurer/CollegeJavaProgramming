// 03-21-18

// This program takes two numbers taken by the user and computes their
// sum as well as print the users name

import java.util.Scanner;
public class Add2Numbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1, num2, total;
		String response;
		String name;
		System.out.print("Enter your name: ");
		name = sc.nextLine();
		System.out.print("Enter 2 numbers seperated by a space: ");
		response = sc.nextLine();
		String[] parts = response.split(" ");
		num1 = Integer.parseInt(parts[0]);
		num2 = Integer.parseInt(parts[1]);
		total = num1 + num2;
		System.out.println(name);
		System.out.println(total);
		sc.close();
		
	}

}
