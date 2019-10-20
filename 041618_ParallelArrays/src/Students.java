// The user will input up to 10 students - id, first, last

import java.util.Scanner;

public class Students {
	
	public static void printStudents(int[] ids, String[] firsts, String[] lasts, int count) {
		for (int i = 0; i < count; i++) {
			System.out.println(ids[i] + " " + firsts[i] + " " + lasts[i]);
		} // end of for loop
	} // end of printStudents
	
	public static void main(String[] args) {
		int[] ids = new int[10];
		String[] firsts = new String[10];
		String[] lasts = new String[10];
		int count = 0;
		String doAgain = "y";
		Scanner sc = new Scanner(System.in);
		int id;
		String last, first;
		
		while (doAgain.equalsIgnoreCase("y")) {
			System.out.print("Enter id, first, and last name: ");
			id = sc.nextInt();
			first = sc.next();
			last = sc.next();
			ids[count] = id;
			firsts[count] = first;
			lasts[count] = last;
			count = count + 1;
			System.out.print("Another? ");
			doAgain = sc.next();
		} // end of while loop
		
		printStudents(ids, firsts, lasts, count);
		
	}

}
