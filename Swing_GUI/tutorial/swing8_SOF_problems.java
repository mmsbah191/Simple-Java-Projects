package tutorial;

/*
 * https://stackoverflow.com/questions/64304509/how-to-make-more-buttons-in-java-gui-swing
 * I would like to use the java swing to make two buttons, one that add +25 to 100, the initial value, and one other that adds +10 to 100, always the initial value:
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class GUI {
    private int button1 = 100;
    private int button2 = 100;
    private JLabel label1, label2;// here important

    GUI() {
        JPanel panel1 = new JPanel(new GridLayout(1, 2));// here seperate 1

        JButton button1 = new JButton("add 25");
        button1.addActionListener(new BtnListener1());
        label1 = new JLabel("initial value: 100");

        panel1.add(button1);
        panel1.add(label1);

        JPanel panel2 = new JPanel(new GridLayout(1, 2));// here seperate 2
        
        JButton button2 = new JButton("add 10");
        button2.addActionListener(new BtnListener2());
        label2 = new JLabel("initial value: 100");

        panel2.add(button2);
        panel2.add(label2);


        panel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));//aligns
        // panel2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));//aligns


        JFrame frame = new JFrame();// here seperate
        
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setTitle("GUI");
        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new GUI();
    }

    class BtnListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            button1 = button1 + 25;
            label1.setText("Value: " + button1);
        }
    }//inner class

    class BtnListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            button2 = button2 + 10;
            label2.setText("Value: " + button2);
        }
    }//inner class

}//Main class