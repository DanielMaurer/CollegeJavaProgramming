import java.awt.Graphics;

import javax.swing.JPanel;

/* This app will let the user test their reaction times. This will include two games; a typing reaction test and a mouse reaction test.
 * 
 * In the typing game, the user will type the letters that are displayed on the screen. Each letter will remain on the screen
 * until the user is able to type the letter and the reaction time will be added up at the end to determine a score.
 * 
 * In the mouse reaction game, the user will have to press the buttons that display on the screen. Each button will remain on the 
 * screen until the user is able to press the location. The reaction time will be added up and a score will be given. 
 * 
 * The model classes in this game will be Button, Letter, Time, and Checker. 
 * 
 * The view classes will be a MessagePanel, a ScorePanel, the GamePanel, and an OptionPanel.
 * 
 * The controller class will be GameFrame. 
 * 
 * 
 */

// ================================ The Model ========================================

class Button{
	private int xLoc;
	private int yLoc;
	private int size;
	private int shape;
	
	public int getXLoc() {
		return xLoc;
	}
	public void setXLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
	public void setYLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getShape() {
		return shape;
	}
	public void setShape(int shape) {
		this.shape = shape;
	}
	
	public Button(int xLoc, int yLoc, int size, int shape) {
		setXLoc(xLoc);
		setYLoc(yLoc);
		setSize(size);
		setShape(shape);
	}
}

class Letter{
	private int xLoc;
	private int yLoc;
	private String letter;
	private int size;
	
	public int getXLoc() {
		return xLoc;
	}
	public void setXLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	public int getYLoc() {
		return yLoc;
	}
	public void setYLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public Letter(int xLoc, int yLoc, String letter, int size) {
		setXLoc(xLoc);
		setYLoc(yLoc);
		setLetter(letter);
		setSize(size);
	}
}

class Time{
	private int startTime;
	private int endTime;
	private int totalTime;
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public Time(int startTime, int endTime) {
		setStartTime(startTime);
		setEndTime(endTime);
	}
	
	public int calculateTime() {
		return totalTime = endTime - startTime;
	}
}

class Checher{
	// class to check if the button is pressed or the correct letter was typed. Will need to work with both game variants
}

//================================ The View ========================================

class MessagePanel extends JPanel{
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public MessagePanel(String message) {
		setMessage(message);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}

class ScorePanel extends JPanel{
	
}

//================================ The Controller ========================================

public class ReactionTest {
	public static void main(String[] args) {
		
	}
}
