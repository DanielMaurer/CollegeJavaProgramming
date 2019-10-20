
import java.util.Scanner;
public class Problem2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double num1, num2, sum, diff;
		String response;
		System.out.print("Enter 2 numbers seperated by a space: ");
		response = sc.nextLine();
		String[] parts = response.split(" ");
		num1 = Double.parseDouble(parts[0]);
		num2 = Double.parseDouble(parts[1]);
		sum = num1 + num2;
		diff = num1 - num2;
		System.out.println(sum);
		System.out.println(diff);
		sc.close();

	}

}
