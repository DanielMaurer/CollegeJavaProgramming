// Danny Maurer Homework 10

import java.util.Scanner;

public class MaurerKHash {
	
	private static int[] stack;
	private static int top;
	
	// makes my life easier
	public static void space() {
		System.out.println(); 
	}

	public static void printWelcome() {
		String seperator = "";
		for (int i = 0; i < 50; i++) {
			seperator = seperator + "*";
		}
		System.out.println(seperator);
		System.out.println("          KHASH - The kewlest of hash");
		System.out.println(seperator);
		space();
		System.out.println("Welcome to the most foolproof encrypting");
		System.out.println(" software this side of the mississippi.");
		System.out.println("Even you (the supplier of the key) will");
		System.out.println("  have no chance in cracking the code.");
		space();	
	}
	
	public static void printClosing(){
		String seperator = "";
		for (int i = 0; i < 50; i++) {
			seperator = seperator + "*";
		}
		space();
		System.out.println(seperator);
		System.out.println("             LAST HOMEWORK IS DONE!!!!");
		System.out.println(seperator);
		space();
	}
	
	// If the list is empty, then a -1 is returned
	public static boolean isEmpty() {
		return top == -1;
	}
	
	// places value at the top of the list
	public static void push(int val) {
		top = top + 1;
		stack[top] = val;
	}
	
	// returns the value at the top of the stack
	public static int peek() {
		return stack[top];
	}
	
	// returns and removes the top value on the stack
	public static int pop() {
		int result = peek();
		top = top - 1; 
		return result;
	}
	
	// determines if object in the stack is an operator
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
	
	public static int postfix(String expression, int[] averageAsciis) {
		String[] parts = expression.split("");
		int num1, num2, result = 0;
		for (String part : parts) {
			if(isOperator(part)) {
				num2 = pop();
				num1 = pop();
				result = evaluate(num1, num2, part);
				push(result);
			} else { // its a number
				push(averageAsciis[Integer.parseInt(part)]);
			}	
		}
		return result;
	}
	
	public static int findMax(int num, int max) {
		if(num > max) {
			max = num;
		}
		return max;
	}
	
	public static void main(String[] args) {
		printWelcome();
		stack = new int[10];
		String doAgain = "y";
		String tempPhrase, phrase = "", tempKey, key = "";
		int max;
		top = -1;
		int hoff = 1; // The controller of all things
		Scanner sc = new Scanner(System.in);
		while(doAgain.equalsIgnoreCase("y")) {
			max = -1;
			if (hoff == 1) {
				System.out.print("Enter the phrase: ");
				phrase = sc.nextLine();
				System.out.print("Enter the key: ");
				key = sc.nextLine();
			} else {
				sc.nextLine(); // Put this so that phrase and key are not on the same line
				System.out.print("Enter the phrase (<enter> to keep the current): ");
				tempPhrase = sc.nextLine();
				if(!tempPhrase.equals("")) {
					phrase = tempPhrase;
				}
				System.out.print("Enter the key (<enter> to keep the current): ");
				tempKey = sc.nextLine();
				if(!tempKey.equals("")) {
					key = tempKey;
				}
			}
			
			// 1. identify the largest digit in the key
			String[] parts = key.split("");
			int spot = 0;
			for(String part : parts) { // converts the key into workable data
				if(!isOperator(part)) {
					spot = Integer.parseInt(part); 
					max = findMax(spot, max);
				}
			}
			
			// 2. Identify the amount of sets that need to be made
			int setCount = max + 1;
			
			// 3. Create array to store the average ascii codes
			int[]averageAsciis = new int[setCount];
			
			// 4. Add ascii code of each character to respective slot
			int count;
			int slot; // this allows for the values to get appended into the respective spots because it is in a for loop
			for(int i = 0; i < setCount; i++) {
				count = 0;
				slot = i;
				while(slot < phrase.length()) {
					averageAsciis[i] = averageAsciis [i] + (int)(phrase.charAt(slot));
					count = count + 1;
					slot = slot + setCount; 
				}
				averageAsciis[i] = averageAsciis[i]/count;
			}
		
			// 5. Evaluate the postfix expression
			postfix(key, averageAsciis);
			
			// 6. Print the hash to the screen
			System.out.println("Your khash of " + phrase + " with key " + key +" is " + pop() + ".");
			
			// 7. Ask if the user wants to do it again
			System.out.print("Would you like to try again: ");
			doAgain = sc.next();	
			hoff = hoff + 1;
			
		} // end of while loop
		printClosing();
		
	} // end of main
} // end of class
