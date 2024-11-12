package swing_calculater;

//import static java.lang.StringTemplate.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

//function for calculater
public class MyListener implements ActionListener {
	JTextField jTextField = Calculator.jTextField;// For access, you have two solutions: either leave the listener and
													// the text field in the same class, or leave the text field static.
	String x = "", y = "";// must be none local for keep values, As for static, it is not necessary
							// because it is one common object that is used for all of them and we do not
							// need anything else in this program.
	char op;
	private boolean flag;

	@Override
	public void actionPerformed(ActionEvent e) {

		// Handle button clicks for all buttons:
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == ButtonPanel.jbt[i]) {
				if (jTextField.getText().equals("0.0") || flag) {
					jTextField.setText(i + "");
					flag = false;
				} else
					jTextField.setText(jTextField.getText() + i);
			}
		}

		if (e.getSource() == ButtonPanel.jDel) {
			String text = jTextField.getText();
			text = text.substring(0, text.length() - 1);
			jTextField.setText(text);
		} else if (e.getSource() == ButtonPanel.jC) {
			jTextField.setText("0.0");
			x = "";
			y = "";

		} else if (e.getSource() == ButtonPanel.jDot) {
			String text = jTextField.getText();
			if (!text.contains("."))
				jTextField.setText(text + '.');

		} else if (e.getSource() == ButtonPanel.jAdd) {
			if (!x.isEmpty()) {
				y = jTextField.getText();
				equal();
			} else if (!jTextField.getText().equals("0.0")) {
				x = jTextField.getText();
				jTextField.setText("0.0");
			}
			op = '+';
		} else if (e.getSource() == ButtonPanel.jSub) {
			if (jTextField.getText().equals("0.0")) {
				jTextField.setText("-");
			} else {
				if (!x.isEmpty()) {
					y = jTextField.getText();
					equal();
				} else {
					x = jTextField.getText();
					jTextField.setText("0.0");
				}
				op = '-';
			}
		} else if (e.getSource() == ButtonPanel.jMul) {
			if (!x.isEmpty()) {
				y = jTextField.getText();
				equal();
			} else {
				x = jTextField.getText();
				jTextField.setText("0.0");
			}
			op = '*';
		} else if (e.getSource() == ButtonPanel.jDiv) {
			if (!x.isEmpty()) {
				y = jTextField.getText();
				equal();
			} else {
				x = jTextField.getText();
				jTextField.setText("0.0");
			}
			op = '/';
		} else if (e.getSource() == ButtonPanel.jEqual) {
			y = jTextField.getText();
			equal();
		} else if (op == ' ') {
			x = jTextField.getText();
			x = y;
		}
	}

	private void equal() {
		if (!x.isEmpty() && !y.isEmpty()) {
			System.out.println(String.format("%s %c %s = %s", x, op, y, (x = "" + cal(x, y, op))));
			y = "";
			op = ' ';
			flag = true;
		}
		if (!x.isEmpty())
			jTextField.setText(x);
		else
			jTextField.setText("");
		// x=jTextField.getText();
	}

	private float cal(String op1, String op2, char charAt) {
		// System.out.println(x + " " + y); for testing
		try {
			return calculate(Double.valueOf(op1), Double.valueOf(op2), charAt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0.0f;
		}
	}

	private float calculate(Double op1, Double op2, char op) {
		double result = 0.0;
		try {
			switch (op) {
				case '+':
					result = op1 + op2;
					break;
				case '-':
					result = op1 - op2;
					break;
				case '*':
					result = op1 * op2;
					break;
				case '/':// if by zero get Infinity
					result = op1 / op2;
					break;
				default:// invalid operator ' '
					result = Double.parseDouble(x);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid number format: " +
					e.getMessage());
		} catch (ArithmeticException e) {
			throw new ArithmeticException(e.getMessage());
		}
		return (float) result;
	}
}
