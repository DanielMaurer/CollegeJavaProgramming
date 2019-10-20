import java.util.ArrayList;


abstract class Shape{
	// Variables
	private int x;
	private int y;
	
	// Get and set functions
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Shape(int x, int y) {
		setX(x);
		setY(y);
	}
	// need to cover this in classes that extent shape class
	public abstract String getType();
	
	// toString function
	@Override // shows that you are overriding the inherited functions
	public String toString() {
		return String.format("Type = %s, x = %d, y = %d", getType(), x, y);
	}
}

class Circle extends Shape{
	private int radius;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if(radius > 0) {
			this.radius = radius;
		}else {
			this.radius = 1;
		}
	}
	
	@Override // the abstract getType function
	public String getType() {
		return "circle";
	}
	
	public Circle(int x, int y, int r) {
		super(x,y);
		setRadius(r);
	}
	@Override
	public String toString() {
		return String.format("%s, rad = %d", super.toString(), radius);
	}
}

class Rectangle extends Shape{
	private int width;
	private int height;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		}else {
			this.width = 1;
		}
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if(height > 0) {
			this.height = height;
		}else {
			this.height = 1;
		}
	}

	public Rectangle(int x, int y, int w, int h) {
		super(x,y);
		setWidth(w);
		setHeight(h);
	}
	@Override
	public String getType() {
		return "rectangle";
	}
	@Override
	public String toString() {
		return String.format("%s, wid = %d, height = %d", super.toString(), width, height);
	}
}

class PrintShapeController{
	private ArrayList<Shape> shapes;
	public PrintShapeController(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public void toConsole() {
		for(Shape s : shapes) {
			System.out.println(s);
		}
	}
}

public class ShapeUserInterface {
	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(10, 15, 8));
		shapes.add(new Rectangle(25, 22, 17, 21));
		shapes.add(new Circle(17, 12, 11));
		PrintShapeController psc = new PrintShapeController(shapes);
		psc.toConsole();
	}
}
