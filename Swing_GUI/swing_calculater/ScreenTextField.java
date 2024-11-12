package swing_calculater;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class ScreenTextField extends JTextField { // inheritance for create new ScreenTextField
	// public static JTextField jTextField;//wrong here composition best here

	// public JTextField getjTextField() {
	// return jTextField;
	// }//wrong

	public ScreenTextField(String text, int columns) {
		// super(text, columns);
		setEnabled(false);// readOnly no changing
		setDisabledTextColor(Color.BLUE);
		setFont(new Font("serif", Font.BOLD, 30));
	}

}
