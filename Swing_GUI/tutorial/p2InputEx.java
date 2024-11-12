package tutorial;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class SwingInputExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        createAndShowGUI();
        }
        });

    }

    private static void createAndShowGUI() {
        // Create and configure the JFrame
        JFrame frame = new JFrame("Swing Input Example");

        // Create a JTextField for user input
        JTextField jtf = new JTextField(10);
        frame.add(new JLabel("Enter your name:"));
        frame.add(jtf);

        // Create a JButton to submit the input
        JButton jb = new JButton("Submit");
        frame.add(jb);

        // Create a JLabel to display the result
        JLabel resultLabel = new JLabel();
        frame.add(resultLabel);

        // Add an ActionListener to the submitButton
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the input from the textField
                String userInput = jtf.getText();

                // Update the resultLabel with the user input
                resultLabel.setText("Your name is: " + userInput);
            }
        });

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());// وهنا في الكود مشكلة غريبة الزوز نفس الفكرة واحد يطلع والتاني لا
        // Set the size and visibility of the JFrame
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

class myCOdeInputEx {
    public static void main(String[] args) {
        createInputGui();
    }

    static void createInputGui() {
        JFrame frame = new JFrame("Input ex");

        JTextField jtf = new JTextField(10);
        frame.add(new Label("Enter your name:"));//here diff
        frame.add(jtf);

        JButton jb = new JButton("Submit");
        frame.add(jb);

        JLabel resultLabel = new JLabel();
        frame.add(resultLabel);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myName = jtf.getText();
                resultLabel.setText("my name:" + myName);
            }

        });

        frame.setLayout(new FlowLayout());
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
/*
 لو مضفتش فلو لاي اوت الديفولت يكون بوردر وبياخد اول عنصر بس، لهذا يفضب اضيف بانيل او تحط موقع البوردر

 */

/*
JButton Constructors
The following are JButton constructors:
JButton()
JButton(String text)
JButton(String text, Icon icon)
JButton(Icon icon)

JLabel Constructors
The constructors for labels are as follows:
JLabel()
JLabel(String text, int horizontalAlignment)
JLabel(String text)
JLabel(Icon icon)
JLabel(Icon icon, int horizontalAlignment)
JLabel(String text, Icon icon, int
horizontalAlignment)

JTextField Methods
 getText()
Returns the string from the text field.
 setText(String text)
Puts the given string in the text field.
 setEditable(boolean editable)
Enables or disables the text field to be edited. By
default, editable is true.
 setColumns(int)
Sets the number of columns in this text field.
The length of the text field is changeable.

 */
