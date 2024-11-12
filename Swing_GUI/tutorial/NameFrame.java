package tutorial;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NameFrame extends JFrame {

	// first: your fields
	private JLabel nameLabel;
	private JTextField nameTextField;

	// second: constructor
	NameFrame() {
		// here fill feilds and call func
		start();
	}

	// third: main method
	public static void main(String[] args) {
		new NameFrame();
	}

	// fourth part: your methods
	private void start() {
		// your design here & fill your fields
		runGui();
	}

	private void runGui() {
		setTitle("Title");
		setLayout(new FlowLayout());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		// setSize(300,300);
	}

	// fifth part: here your function and logic code
	void fun() {

	}

	// sexth part: here your action if is inner class
	// in java classes is NameListener not NameListenerClass
	private class NameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// call your function
		}
	}// inner class

}// end Main class

class NameListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
	}

}// end class
