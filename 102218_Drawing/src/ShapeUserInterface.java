import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Random;


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
	public abstract int getDrawingWidth();
	public abstract int getDrawingHeight();
	
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
	
	@Override
	public int getDrawingWidth() {
		return 2 * radius;
	}
	
	@Override
	public int getDrawingHeight() {
		return 2 * radius;
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
	@Override
	public int getDrawingWidth() {
		return width;
	}
	
	@Override
	public int getDrawingHeight() {
		return height;
	}
	
}

/*class PrintShapeController{ // used toString to draw rather than the paint class
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
*/

class ShapeFrame extends JFrame{
	private ArrayList<Shape> shapes;
	public ShapeFrame(ArrayList<Shape> shapes){
		this.shapes = shapes;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape s : shapes) {
			if(s.getType().equals("circle")) {
				g.drawOval(s.getX(), s.getY(), s.getDrawingWidth(), s.getDrawingHeight());
			}else {
				g.drawRect(s.getX(), s.getY(), s.getDrawingWidth(), s.getDrawingHeight());
			}
		}
	}
}

public class ShapeUserInterface {
	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(100, 150, 40));
		shapes.add(new Rectangle(25, 22, 17, 21));
		shapes.add(new Circle(200, 100, 20));
		Random rnd = new Random();
		int shapeType = rnd.nextInt(2);
		if(shapeType == 0) {
			shapes.add(new Circle(rnd.nextInt(300), rnd.nextInt(300), rnd.nextInt(100)));
		} else {
			shapes.add(new Rectangle(rnd.nextInt(300),rnd.nextInt(300),rnd.nextInt(100),rnd.nextInt(100)));
		}
		//PrintShapeController psc = new PrintShapeController(shapes);
		//psc.toConsole();
		ShapeFrame frm = new ShapeFrame(shapes);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setBounds(100,100,300,300);
		frm.setVisible(true);
	}
}
