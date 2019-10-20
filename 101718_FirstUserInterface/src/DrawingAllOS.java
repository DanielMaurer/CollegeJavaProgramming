import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;
import java.awt.FontMetrics;

class ShapeFrame extends JFrame{
	private Random rnd;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); // properly renders background and borders. NEEDS TO BE IN HERE
		// order is left, top, width and height 
		g.drawOval(100, 100, 50, 50);
		g.drawRect(200, 200, 50, 100);
		g.drawLine(75, 100, 230, 200);
		g.drawArc(150, 150, 100, 100, -45, -90);
		int red = rnd.nextInt(256);
		int green = rnd.nextInt(256);
		int blue = rnd.nextInt(256);
		Color col = new Color(red, green, blue);
		g.setColor(col);
		g.drawOval(100, 150, 80, 160);
		Font f = new Font("Times New Roman", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("I like pizza and Beer", 50, 75);
		FontMetrics fm = g.getFontMetrics();
		System.out.println("Height: "+fm.getHeight());
		System.out.println("Descent: "+fm.getDescent());
		System.out.println("Ascent: "+fm.getAscent());
		System.out.println("Width: "+fm.stringWidth("I like pizza and Beer"));
	}
	public ShapeFrame() { // make a random object in the constructor
		rnd = new Random();
	}
}

public class DrawingAllOS {

	public static void main(String[] args) {
		// create the frame
		ShapeFrame sf = new ShapeFrame();
		sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sf.setTitle("An Oval");
		// configure it
		// order: left, top, width, height
		sf.setBounds(100, 100, 300, 300);
		// show it
		sf.setVisible(true);
	}

}
