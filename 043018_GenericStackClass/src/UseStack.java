// This program illustrates using the Stack class

public class UseStack {

	public static void printStack(Stack stk) {
		while(!stk.isEmpty()) { // prints while the stack isn't empty
			System.out.println(stk.pop());
		}
	}
	
	public static void main(String[] args) {
		Stack numbers = new Stack();
		numbers.push(1);
		numbers.push(2);
		numbers.push(3);
		printStack(numbers);

	}

}
