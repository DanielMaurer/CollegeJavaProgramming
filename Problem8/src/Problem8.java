// Danny Maurer
// Problem 8

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double sys, dia;
		String response;
		System.out.print("Enter your systolic and diastolic blood pressure seperated by a space: ");
		response = sc.nextLine();
		String[] parts = response.split(" ");
		sys = Double.parseDouble(parts[0]);
		dia = Double.parseDouble(parts[1]);
		if ((sys < 120) && (dia < 80)) {
			System.out.println("You have normal blood pressure.");
		} else if ((sys > 180) || (dia > 110)) {
			System.out.println("Get to the hospital now.");
		} else {
			System.out.println("You have prehypertension.");	
		}
		sc.close();
	}

}
