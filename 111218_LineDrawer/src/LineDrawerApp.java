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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}
	
	@Override
	public String toString() {
		return String.format("%d %d", x, y);
	}
}

class LinePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	private ArrayList<Point> points;
	private String message;
	private Color color;
	private int pointSize;
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_R) {
			color = Color.RED;
		} else if(e.getKeyCode() == KeyEvent.VK_B){
			color = Color.BLUE;
		}
		repaint();
	}
	
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
	
	public void mouseEntered(MouseEvent e) {
		System.out.println("You entered the panel");
	}
	public void mouseExited(MouseEvent e) {
		System.out.println("Dont let the door hit ya");
	}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		points.add(new Point(x,y));
		requestFocusInWindow();
		repaint();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		message = String.format("Location : (%d, %d)", x, y);
		requestFocusInWindow();
		repaint();
	}
	public void mouseDragged(MouseEvent e) {
		points.add(new Point(e.getX(), e.getY()));
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
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point prevPoint = null; // variable that makes a line that connects one to the other
		g.setColor(color);
		for(Point p : points) {
			g.fillOval(p.getX(), p.getY(), pointSize, pointSize); // 10 will just be the size of the points
			if(prevPoint != null) {
				g.drawLine(prevPoint.getX() + pointSize/2, prevPoint.getY() + pointSize/2, p.getX() + pointSize/2, p.getY() + pointSize/2);
			}
			prevPoint = p;
		}
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

class PointIO { // controller class to "write points to file" use case
	public boolean writePoints(ArrayList<Point> points, File f) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for(Point p : points) {
				pw.println(p);
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
}

class LineFrame extends JFrame implements ActionListener, KeyListener{
	private ArrayList<Point> points;
	private PointRandomizer pr;
	private Timer tim;
	private LinePanel lpan;
	private JTextField txtPointSize;
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			lpan.setPointSize(Integer.parseInt(txtPointSize.getText()));
			lpan.repaint();
		}
	}
	
	public void configureMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miOpen = new JMenuItem("Open");
		miOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				File f;
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					f = jfc.getSelectedFile();
					points.clear(); // clear existing points from the array
					try {
						Scanner sc = new Scanner(f);
						String line;
						String[] parts;
						Point p;
						while (sc.hasNextLine()) {
							line = sc.nextLine();
							line = line.trim();
							if(!line.equals("")) {
								parts = line.split(" ");
								p = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
								points.add(p);
							}
						}
						sc.close();
						repaint();
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Document wasn't formatted correctly");
					}
					
				}
			}
		});
		mnuFile.add(miOpen);
		JMenuItem miSave = new JMenuItem("Save");
		miSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				File f;
				PointIO pointWriter = new PointIO();
				if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					f = jfc.getSelectedFile();
					pointWriter.writePoints(points, f);
				}
			}
		});
		mnuFile.add(miSave);
		
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile.add(miExit);
		bar.add(mnuFile);
		JMenu mnuEdit = new JMenu("Edit");
		JMenuItem miClear = new JMenuItem("Clear");
		miClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				points.clear(); // clears the array of points
				repaint(); 
			}
		});

		mnuEdit.add(miClear);
		bar.add(mnuEdit);
		JMenu mnuColor = new JMenu("Color");
		JMenuItem miRed = new JMenuItem("Red");
		miRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lpan.setColor(Color.RED);
			}
		});
		mnuColor.add(miRed);
		bar.add(mnuColor);
		JMenu mnuTimer = new JMenu("Timer");
		JMenuItem miStart = new JMenuItem("Start");
		miStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.start();
			}
		});
		mnuTimer.add(miStart);
		JMenuItem miStop = new JMenuItem("Stop");
		miStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim.stop();
			}
		});
		mnuTimer.add(miStop);
		bar.add(mnuTimer);
		setJMenuBar(bar);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		pr.randomize(points);
		repaint();
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

public class LineDrawerApp {
	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(17,21));
		points.add(new Point(58,120));
		points.add(new Point(123,82));
		points.add(new Point(79,200));
		points.add(new Point(108,75));
		for(Point p : points) {
			System.out.println(p);
		}
		LineFrame lf = new LineFrame(points);
		lf.setVisible(true);
	}
}