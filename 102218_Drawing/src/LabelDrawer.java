import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JFrame;

class LabelFrame extends JFrame{
	private String text;
	private int x;
	private int y;
	
	public LabelFrame(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); // delegating to ancestor for background and borders
		Font f = new Font("Times New Roman", Font.BOLD, 24);
		g.setFont(f);
		g.drawString(text, x, y); // draw the string that is input in main
		FontMetrics fm = g.getFontMetrics();
		int xrect = x-10;
		int yrect = y - fm.getAscent();
		int width = fm.stringWidth(text)+20;
		int height = fm.getAscent() + fm.getDescent();
		g.drawRect(xrect, yrect, width, height);
		
		
	}
}

public class LabelDrawer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the text to display: ");
		String text = sc.nextLine();
		System.out.print("Enter the x and y coordinates: ");
		int x = sc.nextInt();
		int y = sc.nextInt();
		LabelFrame frm = new LabelFrame(text,x,y);
		frm.setBounds(100,100,300,300);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		

	}

}
