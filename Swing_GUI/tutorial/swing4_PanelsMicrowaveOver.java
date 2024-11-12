package tutorial;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class swing4_PanelsMicrowaveOver {

	private JTextField jtf;

	swing4_PanelsMicrowaveOver() {

		JPanel p1 = new JPanel(new GridLayout(4, 3,5,5));
		for (int i = 1; i < 10; i++)
			p1.add(new JButton(String.valueOf(i)));
		p1.add(new JButton(String.valueOf("0")));
		p1.add(new JButton(String.valueOf("Start")));
		p1.add(new JButton(String.valueOf("Stop")));

		

		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(jtf=new JTextField("Time here"),BorderLayout.NORTH);
		p2.add(p1,BorderLayout.CENTER);

		// p2.setLayout(new GridLayout(2, 1,10,5));
		// p2.add(new JPanel(new FlowLayout()).add(new JTextField("Time here",10)));
		// p2.add(p1);

		JFrame f = new JFrame();
		// f.setLayout(new BorderLayout());//default new BorderLayout()
		f.add(new JButton("Here put food"),BorderLayout.CENTER);
		f.add(p2,BorderLayout.EAST);
		//or
		// f.setLayout(new GridLayout(1,2,5,5));
		swing3_LayoutManagers.runGui(f);
	}

	public static void main(String[] args) {
		new swing4_PanelsMicrowaveOver();
	}
}
