import java.util.Scanner;

public class AgeConverter {
	public static void main(String[] args){
		System.out.println("This program converts age in years to months.");
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your age in years: ");
		String response;
		response = sc.nextLine();
		int age = Integer.parseInt(response);
		int ageInMonths = age * 12;
		System.out.printf("At %d years you are %d months old. \n", age, ageInMonths);
		sc.close();
	}
}