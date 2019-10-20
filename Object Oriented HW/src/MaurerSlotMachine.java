import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;

class Slot{
	private int shape;
	private int color;
	
	public int getShape() {
		return shape;
	}
	public void setShape(int shape) {
		this.shape = shape;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color; // set the color to red
	}
	
	public Slot(int shape, int color) {
		setShape(shape);
		setColor(color);
	}
	
	public String toString() {
		return String.format("Shape: %d\nColor: %d", shape, color);
	}
}

class Randomizer{
	private ArrayList<Slot> slots;
	private Random rnd;
	
	public Randomizer(ArrayList<Slot> slots) { // takes in ArrayList from main
		this.slots = slots; // points to that address in memory
		rnd = new Random();
	}
	
	public void randomize() {
		for(Slot s : slots) {
			s.setShape(rnd.nextInt(2)); // sets the shape to either circle or square
			s.setColor(rnd.nextInt(3)); // sets the color to red blue or green
			System.out.println(s);
		}
	}
}

class SlotChecker{
	private ArrayList<Slot> slots;
	
	public SlotChecker(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	
	public boolean isWinner() { // checks if all of the shapes and colors are the same
		Slot s1; // create
		Slot s2; // multiple
		Slot s3; // variables
		s1 = slots.get(0); // set value
		s2 = slots.get(1); // to location
		s3 = slots.get(2); // in slots
		if(s1.getColor() == s2.getColor() && s1.getColor() == s3.getColor() && s1.getShape() == s2.getShape() && s1.getShape() == s3.getShape()) {
			return true; // if all the values are equal
		} else {
			return false;
		}
	}
}

class WinningsPanel extends JPanel{
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public WinningsPanel(String message) { // gets the current value
		setMessage(message);
		setPreferredSize(new Dimension(300,20));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(message, 15, 15); // print the message to the panel
	}

}

class SlotPanel extends JPanel{
	private ArrayList<Slot> slots;
	int xLoc; // for formatting of
	int yLoc = 75; // the slot panel
	public SlotPanel(ArrayList<Slot> slots) {
		this.slots = slots;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		xLoc = 50; // initialize x value for each repaint
		for(Slot s : slots) {
			if(s.getColor() == 0) { // find the color
				g.setColor(Color.RED);
			} else if(s.getColor() == 1) {
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.BLUE);
			}
			if(s.getShape() == 0) { // draws a circle
				g.fillOval(xLoc, yLoc, 100, 100);
			} else { // draws a rectangle
				g.fillRect(xLoc, yLoc, 100, 100);
			}
			xLoc = xLoc + 150; // adjusts the xLoc for each iteration
		}
	}
}

class SlotFrame extends JFrame{
	private Randomizer randy; // call the randomizer
	private double current = 1.00; // need to add money into the money event handlers and the winnings constructor
	private String message;
	private boolean winner;
	private double btnVal; // changes with each button
	
	public String buttonPress(double btnVal, boolean winner) {
		if(winner) {
			current = current + btnVal; // If you win add the value
			message = String.format("You won! You now have $%.2f!", current);
		}else {
			current = current - btnVal; // if you lose subtract the value
			message = String.format("Your current value: $%.2f",current);
		}
		return message; // return the message
	}
	
	public void configureUI(ArrayList<Slot> slots) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,300);
		setTitle("Slot Machine");
		Container c = getContentPane();
		c.setLayout(new BorderLayout()); // For the north, center and south layout of the container
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout()); // for the buttons
		JButton btnMax = new JButton("Bet Max"); 
		JButton btnMin = new JButton("Bet Min");
		JButton btnSpin = new JButton("Spin");
		message = "Welcome to Slot Machine!"; // Initial message for the program
		SlotChecker sc = new SlotChecker(slots); // initialize a slot in memory for the SlotChecker
		WinningsPanel wpan = new WinningsPanel(message); // and the WinningsPanel
		SlotPanel span = new SlotPanel(slots); // and the SlotPanel
		
		btnMax.addActionListener(
				new ActionListener() { // if the max button is pressed
					public void actionPerformed(ActionEvent e) {
						randy.randomize(); // randomize the slot values
						winner = sc.isWinner(); // check if you won
						btnVal = 0.5; 
						message = buttonPress(btnVal, winner); // call the button press function (D.R.Y)
						
						if(current <= 0) { // If you are all out of money
							message ="You're out of luck... and money.";
							wpan.setMessage(message);
							btnMax.setEnabled(false);
							btnMin.setEnabled(false);
							btnSpin.setEnabled(false);
						}
						wpan.setMessage(message); // send the message to WinningsPanel
						repaint(); // so that the new arrangement appears
					}
				}
		);
		btnMin.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						randy.randomize();
						winner = sc.isWinner();
						btnVal = 0.1;
						message = buttonPress(btnVal, winner);

						if(current <= 0) {
							message ="You're out of luck... and money.";
							wpan.setMessage(message);
							btnMax.setEnabled(false);
							btnMin.setEnabled(false);
							btnSpin.setEnabled(false);
						}
						wpan.setMessage(message);
						repaint();
					}
				}		
		);
		btnSpin.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						randy.randomize();
						winner = sc.isWinner();
						btnVal = 0.25;
						message = buttonPress(btnVal, winner);

						if(current <= 0) {
							message ="You're out of luck... and money.";
							wpan.setMessage(message);
							btnMax.setEnabled(false);
							btnMin.setEnabled(false);
							btnSpin.setEnabled(false);
						}
						wpan.setMessage(message);
						repaint();
					}
				}		
		);
		panSouth.add(btnMax); // add all of
		panSouth.add(btnMin); // the buttons to
		panSouth.add(btnSpin); // the south panel
		c.add(panSouth,BorderLayout.SOUTH); // add the south panel into the container
		c.add(span,BorderLayout.CENTER); // add the slot panel into the center of the container
		c.add(wpan, BorderLayout.NORTH); // add the winnings panel into the north section of the container
	}
	public SlotFrame(ArrayList<Slot> slots) {
		randy = new Randomizer(slots); // randomize the values in slot
		configureUI(slots); // call the configure function sending slots
	}
}


public class MaurerSlotMachine {

	public static void main(String[] args) {
		int shape;
		int color;
		Random rnd = new Random();
		ArrayList<Slot> slots = new ArrayList<Slot>();
		for(int i = 0; i < 3; i++) {
			shape = rnd.nextInt(2); // either 0 or 1
			color = rnd.nextInt(3); // either 0, 1 or 2
			slots.add(new Slot(shape, color)); // shoot these values to the slot constructor
		}
		
		SlotFrame frm = new SlotFrame(slots); // send the list of slots to the slot frame constructor
		frm.setVisible(true); // show the frame
	}
}
