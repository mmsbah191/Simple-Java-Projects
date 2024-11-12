package swing_calculater;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Main
public class Calculator extends JFrame {
	JMenuBar jMenuBar;
	public static JTextField jTextField;// composition best here
	JPanel btns;

	public Calculator() {
		jMenuBar = new NaviMenuBar();
		jTextField = new ScreenTextField("0.0", 30);
		btns = new ButtonPanel();

		// add to frame
		setJMenuBar(jMenuBar);// not add or setMenuBar
		add(jTextField, BorderLayout.NORTH);
		add(btns, BorderLayout.CENTER);// not here btns not extend Jpanel
		runGui();
	}

	private void runGui() {
		setTitle("Calculator");
		// setLayout(new BorderLayout());//here hide all feilds
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		// setSize(300, 500);
	}

	public static void main(String[] args) {
		new Calculator();// start call cunstructer
	}
}
