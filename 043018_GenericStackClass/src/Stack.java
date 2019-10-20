// This class implements a stack
// Stack is a LIFO data structure - last in, first out
// Supports puch, pop, peek, isEmpty

public class Stack {

	private int top;
	private int[] stack;
	
	public Stack() { // constructor that does the initialization, no return type
		top = -1; // empty
		stack = new int[10];
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public void push(int val) {
		top = top + 1;
		stack[top] = val;
	}
	
	public int peek() {
		return stack[top];
	}
	
	public int pop() {
		int val = peek();
		top = top - 1; // effectively removes the top element from the stack
		return val;
	}
	
}
