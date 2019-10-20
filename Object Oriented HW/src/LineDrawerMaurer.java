import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * Danny Maurer
 * 
 * This app draws a diagram made of line segments. 
 * The user will add line segments by clicking on a panel. 
 * This will illustrates how to respond to mouse events
 * 
 * Process:
 *  1. Create the model - Point (x, y)
 *  2. Test the Point class in main - just display a text printout
 *  3. Create a LineFrame that contains a LinePanel
 *  4. Equip the LinePanel with the ability to draw interconnected points
 *  5. Add a MouseListener so that a new point is added whenever the user clicks the panel
 */

class Point{
	private int x;
	private int y;
	private boolean connect;
	private Color color;
	
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
	public boolean isConnect() {
		return connect;
	}
	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Point(int x, int y, Color color, boolean connect) {
		setX(x);
		setY(y);
		setColor(color);
		setConnect(connect);
	}
	
	@Override
	public String toString() {
		return String.format("x = %d, y = %d", x, y);
	}
}

class LinePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	private ArrayList<Point> points;
	private String message;
	private Color color;
	private int pointSize;
	private Point prevPoint;
	private boolean connect = false;
	private int currentX;
	private int currentY;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getPointSize() {
		return pointSize;
	}
	public void setPointSize(int pointSize) {
		if(pointSize < 0) {
			pointSize = 5;
		}else {
			this.pointSize = pointSize;
		}
	}
	public Point getPrevPoint() {
		return prevPoint;
	}
	public void setPrevPoint(Point prevPoint) {
		this.prevPoint = prevPoint;
	}
	public boolean isConnect() {
		return connect;
	}
	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	
	// -----------Key Events------------------
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R) {
			color = Color.RED;          					// the color variable is passed to the point constructor
		} else if(e.getKeyCode() == KeyEvent.VK_B){
			color = Color.BLUE;
		} else if(e.getKeyCode() == KeyEvent.VK_G){
			color = Color.GREEN;
		} else if(e.getKeyCode() == KeyEvent.VK_L){
			color = Color.BLACK;
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			points.add(new Point(currentX,currentY,color.BLACK, false)); // create a new series of points at the current x and y location of the mouse when the escape key is pressed
			connect = true;
			
		}
		repaint();
	}
	
	//------------Mouse Events-----------------
	public void mouseEntered(MouseEvent e) {
		System.out.println("You entered the panel");
	}
	public void mouseExited(MouseEvent e) {
		System.out.println("Don't let the door hit ya");
	}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		points.add(new Point(x,y,color,connect)); // that way each point and connection has its own unique color
		requestFocusInWindow(); // takes the focus away from the SOUTH of the border layout
		repaint();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		currentX = e.getX();
		currentY = e.getY();
		message = String.format("Location : (%d, %d)", currentX, currentY);
		requestFocusInWindow();
		repaint();
	}
	public void mouseDragged(MouseEvent e) {
		points.add(new Point(e.getX(), e.getY(), color, connect)); 
		repaint();
	}
	
	public LinePanel(ArrayList<Point> points) {
		this.points = points;
		setFocusable(true);
		addMouseListener(this); // listen to your own mouse events
		addMouseMotionListener(this); // DONT FORGET TO ADD THE MOUSE LISTENERS TO THE CONSTRUCTOR
		addKeyListener(this);
		message = "Hey there loser";
		color = color.BLACK;
		setFocusable(true); // allows the focus to be accounted for
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		prevPoint = null; // variable that makes a line that connects one to the other
		g.setColor(color);
		for(Point p : points) {
			g.setColor(p.getColor());
			g.fillOval(p.getX(), p.getY(), pointSize, pointSize); 
			if(p.isConnect() == false) { // if the point shouldn't be connected
				prevPoint = null; // set the prevPoint value to null
			}
			if(prevPoint != null) { // if prevPoint isn't null then draw the connecting line
				g.drawLine(prevPoint.getX() + pointSize/2, prevPoint.getY() + pointSize/2, p.getX() + pointSize/2, p.getY() + pointSize/2);
			}
			prevPoint = p;
		}
		g.drawLine(prevPoint.getX() + pointSize/2, prevPoint.getY() + pointSize/2, currentX + pointSize/2, currentY + pointSize/2); // shows a preview line for the next point that will be drawn at the end of the list of points
		g.drawString(message, 200, 400);
	}
}

class PointRandomizer{
	private Random rnd;
	public void randomize(ArrayList<Point> points) {
		for(Point p : points) {
			p.setX(p.getX() + -10 + rnd.nextInt(20));
		}
	}
	public PointRandomizer() {
		rnd = new Random();
	}
}

class LineFrame extends JFrame implements ActionListener, KeyListener{
	private ArrayList<Point> points; // private variable for the points
	private PointRandomizer pr; // variable for point randomizer object
	private Timer tim; // variable for time object
	private LinePanel lpan; // variable for Line Panel
	private JTextField txtPointSize; // variable for JTextField
	private int time;
	
	public void keyTyped(KeyEvent e) {} // Taking care of the abstract functions for KeyListener
	public void keyReleased(KeyEvent e) {} // Taking care of the abstract functions for KeyListener
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) { // if the enter key is pressed
			lpan.setPointSize(Integer.parseInt(txtPointSize.getText())); // then the point size will be set to the value of the txtPointSize
			lpan.repaint(); // repaint the LinePanel
		}
	}
	
	public void configureMenu() { // creating and adding menu items to the menu
		JMenuBar bar = new JMenuBar(); // creates the menu bar
		
		//--------File Menu------------
		JMenu mnuFile = new JMenu("File"); // file menu
		JMenuItem miExit = new JMenuItem("Exit"); // exit menu item
		miExit.addActionListener(new ActionListener() { // if the exit button is pressed
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // exit the program
			}
		});
		mnuFile.add(miExit); // add the exit item to the file menu
		bar.add(mnuFile); // add the file menu to the menu bar
		
		//--------Edit Menu------------
		JMenu mnuEdit = new JMenu("Edit"); // edit menu
		JMenuItem miClear = new JMenuItem("Clear"); // clear menu item
		miClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				points.clear(); // clears the array of points
				repaint(); // repaints the frame, not the panel
				// MAY NEED TO ADD A NEW POINT SO THAT THERE ARE NO NULL POINTER EXCEPTIONS
			}
		});
		mnuEdit.add(miClear); // adds the clear item to the edit
		JMenuItem miUndo = new JMenuItem("Undo");
		miUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				points.remove(points.size()-1); // removes the last point on the list
				repaint();
			}
		});
		mnuEdit.add(miUndo);
		bar.add(mnuEdit); // adds edit menu to the menu bar
		
		//---------- Color Menu ---------------------
		JMenu mnuColor = new JMenu("Color"); // color menu
		JMenuItem miRed = new JMenuItem("Red"); // red color item
		miRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setColor(Color.RED);
			}
		});
		mnuColor.add(miRed);
		bar.add(mnuColor);
		
		// --------------Timer Menu -----------------
		JMenu mnuTimer = new JMenu("Timer");
		JMenuItem miStart = new JMenuItem("Start");
		miStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.start();
			}
		});
		mnuTimer.add(miStart); // add the start item to the time menu
		JMenuItem miStop = new JMenuItem("Stop");
		miStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.stop();
			}
		});
		mnuTimer.add(miStop); // add the stop item to the time menu
		bar.add(mnuTimer); // add the timer to the menu bar
		
		// ------- Size Menu ------------------
		JMenu mnuSize = new JMenu("Size");
		JMenuItem miSmall = new JMenuItem("Small");
		miSmall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setPointSize(5); // small size of the dot
				lpan.repaint();
			}
		});
		mnuSize.add(miSmall);
		JMenuItem miMediumSize = new JMenuItem("Medium");
		miMediumSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setPointSize(25); // medium size of dot
				lpan.repaint();
			}
		});
		mnuSize.add(miMediumSize);
		JMenuItem miLarge = new JMenuItem("Large");
		miLarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setPointSize(50); // large size of dot
				lpan.repaint();
			}
		});
		mnuSize.add(miLarge);
		bar.add(mnuSize);
		
		//------------ Connection Menu -------------------
		JMenu mnuConnection = new JMenu("Connection"); // the issue with the connection menu is that it will set prevPoint to null but when the panel repaints all of the points then the prevpoint is set to p each time
		JMenuItem miConnect = new JMenuItem("Connect"); // need to find out if I can just repaint a single point at a time
		miConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setConnect(true); // variable for point if there needs to be a connection
			}
		});
		mnuConnection.add(miConnect);
		JMenuItem miDisconnect = new JMenuItem("Disconnect");
		miDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setConnect(false); // false so that there is no connection in variables
			}
		});
		mnuConnection.add(miDisconnect);
		bar.add(mnuConnection);
		
		// ------------ Animation Menu ------------
		JMenu mnuAnimation = new JMenu("Animation");
		JMenuItem miSlow = new JMenuItem("Slow");
		miSlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.stop();
				tim.setDelay(1000); // set new delay as slow
				tim.start();
			}
		});
		mnuAnimation.add(miSlow);
		JMenuItem miMediumSpeed = new JMenuItem("Meduim");
		miMediumSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.stop();
				tim.setDelay(500); // set new delay as medium
				tim.start();
			}
		});
		mnuAnimation.add(miMediumSpeed);
		JMenuItem miFast = new JMenuItem("Fast");
		miFast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.stop();
				tim.setDelay(100); // set new delay to fast
				tim.start();
			}
		});
		mnuAnimation.add(miFast);
		bar.add(mnuAnimation);
		
		setJMenuBar(bar); // set the JMenuBar to the bar that was created
	}
	
	public void actionPerformed(ActionEvent e) {
		pr.randomize(points); // randomize the location of the points
		repaint(); // repaint the frame
	}
	
	public void configureUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		setTitle("Line Drawer v 0.1");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		lpan = new LinePanel(points);
		c.add(lpan, BorderLayout.CENTER);
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		JLabel lblPointSize = new JLabel("Point Size");
		txtPointSize = new JTextField(5);
		txtPointSize.addKeyListener(this); // this refers to the JFrame
		panSouth.add(lblPointSize);
		panSouth.add(txtPointSize);
		c.add(panSouth, BorderLayout.SOUTH);
		configureMenu();
	}
	
	public LineFrame(ArrayList<Point> points) { // constructor to LineFrame
		this.points = points;
		configureUI();
		pr = new PointRandomizer();
		tim = new Timer(1000, this); // makes the frame be the action listener for the timer
		//tim.start();
	}
}

public class LineDrawerMaurer {
	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<Point>();
		Color color = new Color(0);
		points.add(new Point(17,21,color.BLACK,false)); // get rid of all the boolean variables from the point constructor
		points.add(new Point(58,120, color.BLUE, true));
		points.add(new Point(123,82, color.RED, false));
		points.add(new Point(79,200, color.GREEN, true));
		points.add(new Point(108,75, color.BLACK, true));
		for(Point p : points) { // print out the points that were defined in main
			System.out.println(p);
		}
		LineFrame lf = new LineFrame(points); // send the array list of points to the frame
		lf.setVisible(true); // set the frame to visible
	}
}