import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

//--------------------- Shape Class -------------------------------
abstract class Shape{
	// Variables
	private int x;
	private int y;
	private Color col;
	
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
	public Color getCol() {
		return col;
	}
	public void setCol(int red, int green, int blue) {
		this.col = new Color(red, green, blue); // create the color object from the values created in shape;
	}
	
	// Shape constructor
	public Shape(int x, int y, int red, int green, int blue) { // shape constructor that sets x, y, red, green, and blue variables
		setX(x);
		setY(y);
		setCol(red, green, blue);
	}
	
	// need to cover this in classes that extent shape class
	public abstract String getType();
	public abstract int getDrawingWidth();
	public abstract int getDrawingHeight();
	
}

class Circle extends Shape{
	private int radius;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) { // Makes sure that the face isn't too small
		if(radius > 40) {
			this.radius = radius;
		}else {
			this.radius = 40;
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
	
	public Circle(int x, int y, int red, int green, int blue, int radius) { // circle constructor to add teh radius
		super(x, y, red, green, blue);
		setRadius(radius);
	}
}

class Face extends Circle{
	private int smileType;

	public int getSmileType() {
		return smileType;
	}

	public void setSmileType(int smileType) {
		this.smileType = smileType;
	}

	public Face(int x, int y, int red, int green, int blue, int radius, int smileType) { // face constructor to add the smile type
		super(x, y, red, green, blue, radius);
		setSmileType(smileType);
	}
	
	public String toString() {
		if(smileType == 0) { // if the face is a smile
			return ("This face has a smile!");
		}else if(smileType == 1) { // if the face is neutral
			return ("This face is simply neutral.");
		}else { // if the face is sad
			return ("This face is very sad...");
		}
	}
}

class FaceFrame extends JFrame{
	private ArrayList<Face> faces; // create an array of faces
	public FaceFrame(ArrayList<Face> faces) {
		this.faces = faces;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Face f : faces) {
			g.setColor(f.getCol());
			g.drawOval(f.getX(), f.getY(), f.getDrawingWidth(), f.getDrawingHeight()); // main shape of the face
			//g.drawRect(f.getX(), f.getY(), f.getDrawingWidth(), f.getDrawingHeight()); // for the designing of the face
			g.drawOval(f.getX() + f.getDrawingWidth()*1/4, f.getY() + f.getDrawingHeight()/4, f.getDrawingWidth()/10, f.getDrawingHeight()/10); // draw the left eye
			g.drawOval(f.getX() + f.getDrawingWidth()*2/3, f.getY() + f.getDrawingHeight()/4, f.getDrawingWidth()/10, f.getDrawingHeight()/10); // draw the right eye
			if(f.getSmileType() == 0) {
				g.drawArc(f.getX() + f.getDrawingWidth()/6, f.getY() + f.getDrawingHeight()/5, f.getDrawingWidth()*2/3, f.getDrawingHeight()/2, -45, -90); // draw a smile
			}else if(f.getSmileType() == 1) {
				g.drawLine(f.getX() + f.getDrawingWidth()/6, f.getY() + f.getDrawingHeight()/(2) + f.getDrawingHeight()/(5), f.getX() + f.getDrawingWidth()/6 + f.getDrawingWidth()*2/3, f.getY() + f.getDrawingHeight()/(2) + f.getDrawingHeight()/(5)); // draw a neutral face
			}else {
				g.drawArc(f.getX() + f.getDrawingWidth()/6, f.getY() + f.getDrawingHeight()/2 + f.getDrawingHeight()/6, f.getDrawingWidth()*2/3, f.getDrawingHeight()/2, 45, 90); // draw a sad face
			}
			
		}
	}
}


public class FaceDrawMaurer {
	public static void main (String[] args) {
		ArrayList<Face> faces = new ArrayList<Face>();
		Random rnd = new Random();
		int numFaces = rnd.nextInt(4) + 2;
		int x;  
		int y;
		int r;
		int red;
		int green;
		int blue;
		int smileType;
		for(int i = 0; i <= numFaces; i++) {
			// generate random x, y, radius, color (r,g,b), smileType(0,1,2)
			x = rnd.nextInt(500);  
			y = rnd.nextInt(500);
			red = rnd.nextInt(256);
			green = rnd.nextInt(256);
			blue = rnd.nextInt(256);
			r = rnd.nextInt(75);
			smileType = rnd.nextInt(2);
			// create a face and  pass those to the Face constructor and add that new Face to the ArrayList<Face>
			faces.add(new Face(x, y, red, green, blue, r, smileType));
		}
		System.out.print(faces); // print description of the face from the Face toString
		FaceFrame ff = new FaceFrame(faces);
		ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ff.setTitle("Nightmare on AS-104 St.");
		ff.setBounds(100, 100, 750, 750);
		ff.setVisible(true);	
	}
}
