package formatTextFeildSwing;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Main
 */
public class NumberFormatt {
    public static void main(String[] args) {

        MYNumberFormatter myNumberFormatter = new MYNumberFormatter();
        JFormattedTextField field = new JFormattedTextField(myNumberFormatter);


        while (true) {
            JOptionPane.showMessageDialog(null, field, "MYNumberFormatter", 1);
            System.out.println(field.getValue());
        }

    }
}

/**
 * JFormattedTextField field = new JFormattedTextField(new MYNumberFormatter());
 * 
 * import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

 */
class MYNumberFormatter extends NumberFormatter {

    MYNumberFormatter() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // NumberFormatter formatter = new NumberFormatter(numberFormat);

        setFormat(numberFormat);

        // System.out.println(formatter.);
        setValueClass(Integer.class);// Optional, ensures you will always get a long value
        setMinimum(-40);// Optional -4440=>-4 take start
        setMaximum(Integer.MAX_VALUE);// Optional

        // take type class only if false,if true take start like 12.45zjd=>12 jff5=>null
        setAllowsInvalid(true);// default true is filter allow take chars
        // setValueClass(Integer.class)

        // If true (the default behavior), the commitEdit method
        // is called every time a valid edit is made to the text.
        // if false, commitEdit is only called when the text field loses focus
        // (e.g., you click elsewhere in the application).
        setCommitsOnValidEdit(true);// Optional
        // *Use true if you want real-time validation and updates as you type especially
        // for fields where data accuracy is crucial (e.g., credit card numbers).
        // * Use false if immediate updates aren't necessary, or if you want to prevent
        // accidental changes by requiring focus loss to confirm edits.
    }

    /*
     * if setAllowsInvalid(false); The field can also never be empty after you've
     * started typing something in it,the solution:
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text.isEmpty())
            return null;
        return super.stringToValue(text);
    }
}

