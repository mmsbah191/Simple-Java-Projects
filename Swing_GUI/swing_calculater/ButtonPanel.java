package swing_calculater;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ButtonPanel extends JPanel {
	static JButton jbt[], jAdd, jSub, jMul, jDiv, jDot, jDel, jC, jEqual;

	public ButtonPanel() {
		setLayout(new GridLayout(5, 4, 5, 5));

		JRadioButton on = new JRadioButton("ON");
		JRadioButton off = new JRadioButton("OFF");
		on.setSelected(true); // Set "ON" as the default selected button

		ButtonGroup bg = new ButtonGroup();
		bg.add(on);
		bg.add(off);// not container only join properties

		add(on);
		add(off);
		add(jC = new JButton("C"));
		add(jDel = new JButton("del"));

		jbt = new JButton[10];
		for (int i = 1; i < 10; i++) {
			jbt[i] = new JButton("" + i);
			add(jbt[i]);
			if (i == 3)
				add(jAdd = new JButton("+"));
			else if (i == 6)
				add(jSub = new JButton("-"));
			else if (i == 9)
				add(jMul = new JButton("*"));
		}
		add(jDot = new JButton("."));
		add(jbt[0] = new JButton("0"));
		add(jEqual = new JButton("="));
		add(jDiv = new JButton("/"));

		MyListener handle = new MyListener();
		// Add buttons 0-9 and assign listeners:
		for (int i = 0; i < 10; i++) {
			jbt[i].addActionListener(handle);
		}
		// Add remaining buttons and assign listeners:
		jDel.addActionListener(handle); // Attach listener to "del" button
		jC.addActionListener(handle); // Attach listener to "C" button
		jDot.addActionListener(handle); // Attach listener to "." button
		jAdd.addActionListener(handle); // Attach listener to "+" button
		jSub.addActionListener(handle); // Attach listener to "-" button
		jMul.addActionListener(handle); // Attach listener to "*" button
		jDiv.addActionListener(handle); // Attach listener to "/" button
		jEqual.addActionListener(handle); // Attach listener to "=" button

		// Add ItemListener for enabling/disabling buttons
		on.addItemListener(e -> toggleButtons(true)); // Enable buttons when "ON" is selected
		off.addItemListener(e -> toggleButtons(false)); // Disable buttons when "OFF" is selected
	}

	// In ButtonPanel class
	private void toggleButtons(boolean enabled) {
		// Toggle numeric buttons
		for (JButton button : jbt) {
			if (button != null) { // Ensure button is initialized
				button.setEnabled(enabled);
			}
		}
		// Toggle other buttons
		jAdd.setEnabled(enabled);
		jSub.setEnabled(enabled);
		jMul.setEnabled(enabled);
		jDiv.setEnabled(enabled);
		jDot.setEnabled(enabled);
		jDel.setEnabled(enabled);
		jC.setEnabled(enabled);
		jEqual.setEnabled(enabled);
	}

}