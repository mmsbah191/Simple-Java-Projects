package formatTextFeildSwing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class KeyListener {

    public static void main(String[] args) {

        // NumberFormat numberFormat = NumberFormat.getInstance();
        // NumberFormatter formatter = new NumberFormatter(numberFormat);
        
        // formatter.setValueClass(Integer.class);// Optional, ensures you will always get a long value
        // formatter.setMinimum(40);// Optional -4440=>-4 take start
        // formatter.setMaximum(Integer.MAX_VALUE);// Optional
    
        // Create a text field for single character input
        JTextField textField = new JFormattedTextField();

        // Filter input to allow only numbers and backspace
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Prevent non-numeric characters from being entered
                }
            }
        });


          // Filter input to allow only numbers and backspace
          textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c!='-' && !Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Prevent non-numeric characters from being entered
                }
            }
        });

         // Filter input to allow only numbers and backspace
         textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c+"").matches("\\d*")) {
                    e.consume(); // Prevent non-numeric characters from being entered
                }

            }
        });

        while (true) {
            JOptionPane.showMessageDialog(null, textField);
                System.out.println(textField.getText());
        }
    }
}
