// 03-23-2018
// This program calculates BMI and catagorizes the user

import java.util.Scanner;

public class BMICalculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double height, weight, bmi;
		System.out.println("Welcome to the BMI Calculator");
		System.out.print("What is your weight in pounds? ");
		weight = sc.nextDouble();
		System.out.print("What is your height in inches? ");
		height = sc.nextDouble();
		weight = weight * 0.454;
		height = height * 0.0254;
		bmi = weight / Math.pow(height, 2);
		System.out.printf("Your BMI is %.2f.\n", bmi);
		if (bmi < 20) {
			System.out.println("Better go get a milkskhake");
		} else if (bmi >= 20) {
			System.out.print("You are perfect in every way/");
		} else if (bmi < 30) {
			System.out.print("Better get to the gym.");
		} else {
			System.out.print("Lay off the cheezy puffs.");
		}
		
		sc.close();
	}

}
