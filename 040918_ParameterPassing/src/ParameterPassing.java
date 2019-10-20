// 04-09-18 Programming Fundamentals
// This code will perminately change the value of the variable because it is a class type variable

// define a new data type that define the x and y coordinates of a point
class Point{
	public int x;
	public int y;
	
}

public class ParameterPassing { //1
	public static void doSomething(Point p) {
		p.x = 2 * p.x;
		p.y = 2 * p.y;
	}
	public static void main(String[] args) { //2
		Point p = new Point();
		p.x = 7;
		p.y = 5;
		System.out.printf("The value of p's x is %d, and y is %d.\n",p.x,p.y);
		doSomething(p);
		System.out.printf("The value of p's x is %d, and y is %d.\n",p.x,p.y);
	} //2
} //1
