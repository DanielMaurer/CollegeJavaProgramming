import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Label {
	private int xpos;
	private int ypos;
	private String text;
	public int getXPos() {
		return xpos;
	}
	public void setXPos(int xpos) {
		this.xpos = xpos;
	}
	public int getYPos() {
		return ypos;
	}
	public void setYPos(int ypos) {
		this.ypos = ypos;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Label() {
		text = "";
		xpos = 0;
		ypos = 0;
	}
	public Label(String text, int x, int y) {
		setText(text);
		setXPos(x);
		setYPos(y);
	}
	@Override
	public String toString() {
		return String.format("%s %d %d", text, xpos, ypos);
	}
}
/*
 * LabelPicker generates a Label at random from a list of strings.
 * It also can randomize an existing label, changing its text
 * and its position.
 */
class LabelPicker {
	private ArrayList<String> strings;
	private Random rnd;
	public LabelPicker(ArrayList<String> strings) {
		this.strings = strings;
		rnd = new Random();
	}
	public Label createLabel() {
		Label result = new Label();
		randomizeLabel(result);
		return result;
	}
	public void randomizeLabel(Label lab) {
		int xpos = rnd.nextInt(500);
		int ypos = rnd.nextInt(500);
		int count = strings.size();
		int index = rnd.nextInt(count);
		String theText = strings.get(index);
		lab.setText(theText);
		lab.setXPos(xpos);
		lab.setYPos(ypos);
	}
}
/* The LabelPanel displays a single Label at a time. */
class LabelPanel extends JPanel {
	private Label label;
	private Font font;
	public LabelPanel(Label lab) {
		setLabel(lab);
		font = new Font("Arial",Font.BOLD,24);
	}
	public void setLabel(Label lab) {
		label = lab;
	}
	public Label getLabel() {
		return label;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		g.drawString(label.getText(),label.getXPos(),label.getYPos());
	}
}
class LabelFrame extends JFrame implements ActionListener {
	private Label label;
	private LabelPicker lp;
	private Timer tim;
	private JButton btnTimer;
	public void actionPerformed(ActionEvent e) {
		lp.randomizeLabel(label);
		repaint();
	}
	public LabelFrame(ArrayList<String> strings) {
		tim = new Timer(1000,this);
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		lp = new LabelPicker(strings);
		label = lp.createLabel();
		LabelPanel panLabel = new LabelPanel(label);
		c.add(panLabel, BorderLayout.CENTER);
		JPanel panSouth = new JPanel();
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lp.randomizeLabel(label);
				repaint();
			}
		});
		btnTimer = new JButton("Stop");
		btnTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tim.isRunning()) {
					tim.stop();
					btnTimer.setText("Start");
				} else {
					tim.start();
					btnTimer.setText("Stop");
				}
			}
		});
		panSouth.add(btnMove);
		panSouth.add(btnTimer);
		c.add(panSouth,BorderLayout.SOUTH);
		tim.start();
	}
}
public class LabelApp {
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("chorizo");
		strings.add("steak");
		strings.add("chicken");
		strings.add("sofritas");
		LabelPicker lp = new LabelPicker(strings);
		Label label;
		for (int i = 0; i < 10; i++) {
			label = lp.createLabel();
			System.out.println(label);
		}
		LabelFrame lf = new LabelFrame(strings);
		lf.setVisible(true);
	}
}
