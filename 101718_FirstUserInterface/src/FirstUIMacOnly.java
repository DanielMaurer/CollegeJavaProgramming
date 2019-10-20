import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

public class FirstUIMacOnly {

	public static void main(String[] args) {
		javax.swing.JFrame frame = new JFrame();
		frame.setTitle("My First User Interface");
		frame.setBounds(100,50,300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Graphics g = frame.getGraphics();
		g.setColor(Color.BLACK);
		g.drawOval(100, 50, 70, 150);
		
	}

}
