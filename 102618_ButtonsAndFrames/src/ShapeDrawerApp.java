import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

abstract class Shape {
	private int x;
	private int y;
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
	public abstract String getType();
	public abstract int getDrawingWidth();
	public abstract int getDrawingHeight();
	@Override
	public String toString() {
		return String.format("Type=%s, x=%d, y=%d",getType(),x,y);
	}
}
class Circle extends Shape {
	private int radius;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			this.radius = 1;
		}
	}
	public Circle(int x, int y, int r) {
		super(x,y);
		setRadius(r);
	}
	@Override
	public String getType() {
		return "circle";
	}
	@Override
	public String toString() {
		return String.format("%s, rad=%d", super.toString(),radius);
	}
	@Override
	public int getDrawingWidth() {
		return 2 * radius;
	}
	@Override
	public int getDrawingHeight() {
		return 2 * radius;
	}
}
class Rectangle extends Shape {
	private int width;
	private int height;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		if (width >= 0) {
			this.width = width;
		} else {
			this.width = 1;
		}
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		if (height >= 0) {
			this.height = height;
		} else {
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
		return String.format("%s, width=%d, height=%d", super.toString(), width, height);
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
class ShapePanel extends JPanel{
	private ArrayList<Shape> shapes;
	public ShapePanel(ArrayList<Shape> shapes) { // replicates the 
		this.shapes = shapes;			   // ArrayList of shapes
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : shapes) {
			if(s.getType().equals("circle")) {
				g.drawOval(s.getX(), s.getY(), s.getDrawingWidth(), s.getDrawingHeight());
			} else {
				g.drawRect(s.getX(), s.getY(), s.getDrawingWidth(), s.getDrawingHeight());
			}
		}
	}
}
class Randomizer{
	private ArrayList<Shape> shapes;
	private Random rnd;
	public Randomizer(ArrayList<Shape> shapes) {
		this.shapes = shapes;
		rnd = new Random();
	}
	public void randomize() {
		for(Shape s : shapes) {
			s.setX(rnd.nextInt(250));
			s.setY(rnd.nextInt(250));
		}
	}
}


/*class ButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Hey, you clicked me!");
	}
}*/

class MessagePanel extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Howdy", 10, 10);
	}
	public MessagePanel() {
		setPreferredSize(new Dimension(300, 100));
	}
}

class ShapeFrame extends JFrame {
	private Randomizer randy;
	public void configureUI(ArrayList<Shape> shapes) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,300,500);
		setTitle("Shapes");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						randy.randomize();
						repaint(); // so that the new arrangement appears
					}
				}
		);
		panSouth.add(btnOK);
		c.add(panSouth,BorderLayout.SOUTH);
		ShapePanel span = new ShapePanel(shapes);
		c.add(span,BorderLayout.CENTER);
		MessagePanel mpan = new MessagePanel();
		c.add(mpan, BorderLayout.NORTH);
	}

	public ShapeFrame(ArrayList<Shape> shapes) {
		randy = new Randomizer(shapes); // call the constructor of randomizer
		configureUI(shapes);
	}
}

public class ShapeDrawerApp{
	public static void main(String[] args) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		Random rnd = new Random();
		int shapeCount = rnd.nextInt(5) + 2;
		int shapeType;
		for(int i = 0; i < shapeCount; i++) {
			shapeType = rnd.nextInt(2); // 0 = circle, 1 = rectangle
			if (shapeType == 0) {
				shapes.add(new Circle(rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(50)));
			} else {
				shapes.add(new Rectangle(rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(75), rnd.nextInt(75)));
			}
		}
		for(Shape s : shapes) {
			System.out.println(s);
		}
		ShapeFrame frm = new ShapeFrame(shapes);
		frm.setVisible(true);
	}
}