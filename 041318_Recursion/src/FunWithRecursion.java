// This program will be able to calculate the factorial of a number using recursion

public class FunWithRecursion {
	
	// Function to calculate the factorial of a number n
	public static int fact(int n) {
		if(n == 0) {
			return 1;
		} else {
			return n * fact(n-1); // here is the decomposition
		}
	} // end of fact function
	
	// Function that calculates the fibonacci sequence with recursion
	public static int fib(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fib (n-2) + fib(n-1);
		}
	} // end of fib sequence
	
	public static void main(String[] args) {
		System.out.println(fact(5));
		System.out.println(fib(8));
	} // end of main
} // end of class