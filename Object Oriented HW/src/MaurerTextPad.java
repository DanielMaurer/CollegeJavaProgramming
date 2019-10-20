import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class TextFrame extends JFrame{
	JButton[] buttons = new JButton[30];

	public void configureUI(JButton[] buttons) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,500,500);
		setTitle("Danny's Typing Academy");
		Container c = getContentPane();
		// Border Layout for the Frame
		c.setLayout(new BorderLayout());
		
		// Text panel for the north
		JTextField text = new JTextField(40);
		JPanel panNorth = new JPanel();
		panNorth.add(text); // adds the text field to the north of the border layout
		
		//Grid Layout for the center
		JPanel panCenter = new JPanel();
		panCenter.setLayout(new GridLayout(5,6)); // sets the layout for all of the buttons
		
		// name buttons
		for(int i = 0; i < 26; i++) {
			buttons[i] = new JButton("" + (char)(i + 65)); // adds all of the buttons for letters based on their ASCII codes
		}
		
		buttons[26] = new JButton("" + (char)46); // button for the period
		buttons[27] = new JButton("" + (char)33); // button for the !
		buttons[28] = new JButton("Space");
		buttons[29] = new JButton("Enter");
		
		// action handlers
		for(int i = 0; i < 28; i++) {
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)(e.getSource()); // typecasts the source of each button to a btn variable
					text.setText(text.getText() + btn.getText()); // adds the text value of the button to the text field variable
					text.repaint(); // makes the needed change to the text
				}
			});
		}
		
		// action handler for space
		buttons[28].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText(text.getText() + " "); 
				text.repaint();
			}
		});
		// action handler for enter
		buttons[29].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, text);
			}
		});
		
		// add buttons to the grid layout
		for(int i = 0; i < 30; i++) {
			panCenter.add(buttons[i]);
		}
		
		// JButton for the south
		JPanel panSouth = new JPanel();
		JButton btnClear = new JButton("Clear");
		panSouth.add(btnClear);
		
		// action handler for the clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		
		// add panels to container
		c.add(panSouth, BorderLayout.SOUTH);
		c.add(panCenter, BorderLayout.CENTER);
		c.add(panNorth, BorderLayout.NORTH);
	}
	
	// constructor to configure the user interface (not needed but I like this process for more complicated work)
	public TextFrame(JButton[] buttons) {
		configureUI(buttons);
	}
}

public class MaurerTextPad {

	public static void main(String[] args) {
		JButton[] buttons = new JButton[30]; // creates an array for the buttons
		TextFrame frm = new TextFrame(buttons); // sends the array to the text frame
		frm.setVisible(true); // shows the frame
	}

}
