package formatTextFeildSwing;

/*Do not use a KeyListener for this as you'll miss much including pasting of text. Also a KeyListener is a very low-level construct and as such, should be avoided in Swing applications.

DocumentFilter: Swing provides a more appropriate and higher-level mechanism for input validation called DocumentFilter. DocumentFilter intercepts changes to the document content at a higher level, allowing for more comprehensive and reliable input validation. It covers a wider range of input methods, including pasting and drag-and-drop operations.

Using DocumentFilter instead of KeyListener for input validation is generally recommended in Swing applications because it provides a more robust and flexible solution while avoiding the drawbacks associated with KeyListeners. */

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class InputValidate {

    public static void main(String[] args) {
        JTextField textField = new JTextField(10);

        // PlainDocument doc = (PlainDocument) textField.getDocument();
        // doc.setDocumentFilter(new MyIntRangeFilterClass(10));

        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new MyIntRangeFilter(100));

        while (true) {
            JOptionPane.showMessageDialog(null, textField, "Enter a number (0-100):", 0);
            System.out.println(textField.getText());
        }
    }
}

class MYDocumentFilter extends DocumentFilter {
    // class allow validate before print
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        super.replace(fb, offset, length, text, attrs);
    }
}

class MyIntRangeFilter extends DocumentFilter {
    /*
     * range numbers
     * for use class:
     * PlainDocument doc = (PlainDocument) textField.getDocument();
     * doc.setDocumentFilter(new MyIntRangeFilter(10));
     * 
     * or
     * ((AbstractDocument) textField.getDocument()).setDocumentFilter(new
     * MyIntRangeFilter(10));
     * 
     * * import javax.swing.text.Document;
     * import javax.swing.text.AttributeSet;
     * import javax.swing.text.BadLocationException;
     */
    private final int MIN;
    private final int MAX;

    public MyIntRangeFilter(int max) {
        this(0, max);
    }

    public MyIntRangeFilter(int min, int max) {
        this.MIN = min;
        this.MAX = max;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        if (isIntRange(currentText.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    private boolean isIntRange(String text) {
        try {
            int number = Integer.parseInt(text);//check if int
            return number >= MIN && number <= MAX;//check if range
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class MypercentFilter extends DocumentFilter {
    /*
     * range percentage
     * if added a percentage, you cannot add numbers after it.
     * Note that I want the range of numbers between min and max.
     * 
     * 
     * for use class:
     * PlainDocument doc = (PlainDocument) textField.getDocument();
     * doc.setDocumentFilter(new MyIntRangeFilter(10));
     * 
     * or
     * ((AbstractDocument) textField.getDocument()).setDocumentFilter(new
     * MyIntRangeFilter(10));
     * 
     */

    private final int MIN;
    private final int MAX;

    public MypercentFilter(int max) {
        this(0, max);
    }

    public MypercentFilter(int min, int max) {
        this.MIN = min;
        this.MAX = max;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        int count = count(currentText, '%');
        boolean isContainsPercent = count == 0 || count == 1 && currentText.charAt(currentText.length() - 1) == '%';
        currentText = currentText.replace("%", "");
        if (isContainsPercent && isDecimal(currentText) && isInRange(currentText)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    private int count(String currentText, char c) {
        int count = 0;
        for (int i = 0; i < currentText.length(); i++) {
            if (currentText.charAt(i) == c)
                count++;
        }
        return count;
    }

    boolean isDecimal(String percent) {
        percent = percent.replace(".", "");
        return percent.matches("\\d*");
    }

    private boolean isInRange(String text) {
        try {
            double number = Double.parseDouble(text);
            return number >= MIN && number <= MAX;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class MyFloatFilter extends DocumentFilter {
    /*
     * range numbers
     * for use class:
     * PlainDocument doc = (PlainDocument) textField.getDocument();
     * doc.setDocumentFilter(new MyIntRangeFilter(10));
     * 
     * or
     * ((AbstractDocument) textField.getDocument()).setDocumentFilter(new
     * MyIntRangeFilter(10));
     * 
     * * import javax.swing.text.Document;
     * import javax.swing.text.AttributeSet;
     * import javax.swing.text.BadLocationException;
     */

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        currentText = currentText.replace("k", "");
        if (isNumeric(currentText) && isDecimal(currentText)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    boolean isNumeric(String percent) {
        percent = percent.replace(".", "");//isNumeric
        return percent.matches("\\d*");
    }

    private boolean isDecimal(String text) {
        try {
            Double.parseDouble(text);// isDecimal
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


class MyIntRangeFilterClass extends DocumentFilter {
    /*
     * range Integers
     * for use class:
     * PlainDocument doc = (PlainDocument) textField.getDocument();
     * doc.setDocumentFilter(new MyIntRangeFilter(10));
     * 
     * or
     * ((AbstractDocument) textField.getDocument()).setDocumentFilter(new
     * MyIntRangeFilter(10));
     * 
     * * import javax.swing.text.Document;
     * import javax.swing.text.AttributeSet;
     * import javax.swing.text.BadLocationException;
     */
    private final int MIN;
    private final int MAX;

    public MyIntRangeFilterClass(int max) {
        this(0, max);
    }

    public MyIntRangeFilterClass(int min, int max) {
        this.MIN = min;
        this.MAX = max;
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder currentText = new StringBuilder();
        currentText.append(doc.getText(0, doc.getLength()));
        currentText.replace(offset, offset + length, text);

        if (isNumeric(currentText.toString()) && isIntRange(currentText.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    public void replace1(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        if (isNumeric(currentText) && isIntRange(currentText.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (sb.toString().length() == 0) {
            super.replace(fb, offset, length, "", null);// here text empty at remove all
        } else if (isNumeric(sb.toString())) {
            super.remove(fb, offset, length);
        } else {
            // warn the user and don't allow the insert
        }
    }

    private boolean isNumeric(String text) {
        /*
         * This approach is concise and efficient for checking if a string is a number.
         */
        if (text.trim().isEmpty())
            return true;
        return text.matches("\\d*");
    }

    private boolean isNumeric1(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isIntRange(String text) {
        try {
            int number = Integer.parseInt(text);//check if int
            return number >= MIN && number <= MAX;//check if range
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



