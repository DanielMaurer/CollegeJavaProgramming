import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;

class DrawingPanel extends JPanel{
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(100,100,100,100);
	}
}

class UIExampleFrame extends JFrame{
	public void configureUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("UI Demonstration");
		setBounds(100,100,300,300);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		DrawingPanel panContent = new DrawingPanel();
		c.add(panContent, BorderLayout.CENTER);
		JPanel panButton = new JPanel();
		panButton.setLayout(new FlowLayout());
		JButton btnOK = new JButton("OK");
		panButton.add(btnOK);
		c.add(panButton, BorderLayout.SOUTH);	
	}
	
	public UIExampleFrame() {
		configureUI();
	}
}

public class FirstUserInterfaceApp {

	public static void main(String[] args) {
		UIExampleFrame uie = new UIExampleFrame();
		uie.setVisible(true);

	}

}
