// This illustrates basic stack functions

import java.util.Scanner;

public class StackFun {
	
	private static int[] stack;
	private static int top;
	
	public static boolean isEmpty() {
		return top == -1; // return true if top is -1
	}
	
	public static void push(int val) { // places value at the top of the stack
		top = top + 1;
		stack[top] = val;
	}
	
	// precondition: stack isnt empty
	public static int peek() {
		return stack[top];
	}
	
	// precondition: stack isnt empty
	public static int pop() {
		int result = peek();
		top = top - 1; // returns the current top element from consideration
		return result;
	}
	
	public static boolean isOperator(String str) {
		if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int evaluate(int num1, int num2, String op) {
        if (op.equals("+")) {
            return num1 + num2;
        } else if (op.equals("-")) {
            return num1 - num2;
        } else if (op.equals("*")) {
            return num1 * num2;
        } else if (op.equals("/")) {
            return num1 / num2;
        } else if (op.equals("%")) {
            return num1 % num2;
        } else {
            return num1 + num2;
        }
   }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		stack = new int[10];
		top = -1; // stack is empty to begin with
		//push some values onto the stack
		push(1);
		push(2);
		push(3);
		while(!isEmpty()) {
			System.out.println(pop());
		}
		/* Implement a postfix calculator:
		 * 	
		 * the user will enter a space-separated expression in postfix
		 * split the string into its parts
		 * for each part:
		 * 	if it is a number
		 * 		push it onto the stack
		 *	otherwise its an operator
		 *		pop the top two values
		 *		combine them using the operator
		 *		push the result onto the stack
		 *pop the last remaining value from the stack --> result
		 */
		System.out.print("Enter a postfix expression: ");
		String expression = sc.nextLine();
		String[] parts = expression.split(" ");
		int num1, num2, result;
		for (String part : parts) {
			if(isOperator(part)) {
				num2 = pop();
				num1 = pop();
				result = evaluate(num1, num2, part);
				push(result);
			} else { // its a number
				push(Integer.parseInt(part));
			}	
		}
		System.out.println("The result is " + pop() + ".");
	}

}
