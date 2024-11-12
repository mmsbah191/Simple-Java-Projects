package tutorial;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Main
 */
public class swing1_Start{

	public static void main(String[] args) {
		JButton jb = new JButton("OK");
		JLabel jl = new JLabel("Enter Your Name:");
		JTextField jtf = new JTextField("type here");

		JCheckBox jcb = new JCheckBox("Bold");
		JRadioButton jrb = new JRadioButton("Red");
		JComboBox<String> jcbox = new JComboBox<>("red blue green".split("\\s"));

		// Creates a frame with the specified title.
		JFrame jFrame = new JFrame("My 191");

		// add compenents to frame
		jFrame.add(jb);
		jFrame.add(jl);
		jFrame.add(jtf);
		jFrame.add(jcb);
		jFrame.add(jrb);
		jFrame.add(jcbox);

		// configration frame
		jFrame.setLayout(new FlowLayout());// importnt for org true
		jFrame.setSize(1000, 600);
		jFrame.setVisible(true);// Sets true to display the frame.

		jFrame.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);// Specifies the operation when the frame is closed.
		jFrame.setLocationRelativeTo(null);// Sets the location of the frame relative
		// to the specified component. If the
		// // component is null, the frame is centered on the screen
		// jFrame.pack();// Automatically resizes the size frame to equavement the
		// components
		// +setLocation(x: int, y: int): void Specifies the upper-left corner location
		// of the frame
	}
}
