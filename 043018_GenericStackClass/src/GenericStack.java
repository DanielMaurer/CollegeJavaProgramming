// This class implements a stack
// Stack is a LIFO data structure - last in, first out
// Supports puch, pop, peek, isEmpty

public class GenericStack<T> {

	private int top;
	private T[] stack;
	
	public GenericStack() { // constructor that does the initialization, no return type and same name as class
		top = -1; // empty
		stack = (T[])(new Object[10]); // to make an array of T's
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public void push(T val) {
		top = top + 1;
		stack[top] = val;
	}
	
	public T peek() {
		return stack[top];
	}
	
	public T pop() {
		T val = peek();
		top = top - 1; // effectively removes the top element from the stack
		return val;
	}
	
}
