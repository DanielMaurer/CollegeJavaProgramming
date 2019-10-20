// This program illustrates using the Stack class

public class UseGenericStack {

	public static void printStack(GenericStack stk) {
		while(!stk.isEmpty()) { // prints while the stack isn't empty
			System.out.println(stk.pop());
		}
	}
	
	public static void main(String[] args) {
		GenericStack<Integer> numbers = new GenericStack<Integer>();
		numbers.push(1);
		numbers.push(2);
		numbers.push(3);
		printStack(numbers);
		GenericStack<String> words = new GenericStack<String>();
		words.push("apple");
		words.push("banana");
		words.push("carrot");
		printStack(words);
		

	}

}
