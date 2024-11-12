
package Swing_GUI.AllInterestRate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

class InterestRateCalculator {
    private static JFrame frame;
    private final JPanel mainPanel, resultPanel, interestTotalPanel, monthlyPaymentPanel, totalPaymentsPanel,
            buttonsPanel;
    private static JTextField loanAmountTextField, numberCompoundInterestTextField, interestRateTextField,
            yearsTextField, monthsTextField;
    private static JCheckBox simpleInterestCheckBox;
    private static JCheckBox compoundInterestCheckBox;
    private final JCheckBox totalCheckBox;
    private static JRadioButton yearlyRadioButton, semiYearlyRadioButton, quarterlyRadioButton, monthlyRadioButton,
            dailyRadioButton;
    private static JLabel resultLabel, interestTotalLabel, monthlyPaymentLabel, totalPaymentsLabel;
    private final JMenuBar jMenuBar;
    GridLayout gridMainPaneLayout;
    private PlainDocument doc;

    InterestRateCalculator() {

        gridMainPaneLayout = new GridLayout(9, 1);
        mainPanel = new JPanel(gridMainPaneLayout);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));// aligns
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        titlePanel.add(new JLabel("Enter  loan Amount, Interst rate and duration:"));

        // Loan amount panel
        JPanel loanAmountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        loanAmountPanel.add(new JLabel("Loan Amount (k):"));
        loanAmountPanel.add(loanAmountTextField = new JTextField("12k", 10));

        doc = (PlainDocument) loanAmountTextField.getDocument();
        doc.setDocumentFilter(new MyFloatFilter());

        // Type Of Interest
        JPanel typeOfInterestCheckBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        typeOfInterestCheckBoxPanel.add(new JLabel("Type of Interest:"));
        typeOfInterestCheckBoxPanel.add(simpleInterestCheckBox = new JCheckBox("Simple", true));
        typeOfInterestCheckBoxPanel.add(totalCheckBox = new JCheckBox("Total"));
        typeOfInterestCheckBoxPanel.add(compoundInterestCheckBox = new JCheckBox("Compound n:"));
        typeOfInterestCheckBoxPanel.add(numberCompoundInterestTextField = new JTextField("", 2));
        numberCompoundInterestTextField.setEnabled(false);

        simpleInterestCheckBox.setBackground(Color.white);
        totalCheckBox.setBackground(Color.white);
        compoundInterestCheckBox.setBackground(Color.white);

        doc = (PlainDocument) numberCompoundInterestTextField.getDocument();
        doc.setDocumentFilter(new MyIntRangeFilter(1, 12));

        ButtonGroup typeOfInterestGroup = new ButtonGroup();
        typeOfInterestGroup.add(simpleInterestCheckBox);
        typeOfInterestGroup.add(compoundInterestCheckBox);
        typeOfInterestGroup.add(totalCheckBox);

        simpleInterestCheckBox.addActionListener(new simpleInterestCheckBoxListener());
        compoundInterestCheckBox.addActionListener(new compoundInterestCheckBoxListener());
        totalCheckBox.addActionListener(new totalCheckBoxListener());

        // Interest rate panel
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        interestPanel.add(new JLabel("Interest rate (%):"));
        interestPanel.add(interestRateTextField = new JTextField("10%", 5));
        interestPanel.add(typeOfInterestCheckBoxPanel);

        doc = (PlainDocument) interestRateTextField.getDocument();
        doc.setDocumentFilter(new MypercentFilter(0, 1000));

        // interest RadioButton Panel
        JPanel interestRadioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        interestRadioButtonPanel.add(new JLabel("interest rate Frequency:"));
        interestRadioButtonPanel.add(yearlyRadioButton = new JRadioButton("Annually", true));
        interestRadioButtonPanel.add(semiYearlyRadioButton = new JRadioButton("Semiannually"));
        interestRadioButtonPanel.add(quarterlyRadioButton = new JRadioButton("Quarterly"));
        interestRadioButtonPanel.add(monthlyRadioButton = new JRadioButton("Monthly"));
        interestRadioButtonPanel.add(dailyRadioButton = new JRadioButton("Daily"));

        yearlyRadioButton.setBackground(Color.white);
        semiYearlyRadioButton.setBackground(Color.white);
        quarterlyRadioButton.setBackground(Color.white);
        monthlyRadioButton.setBackground(Color.white);
        dailyRadioButton.setBackground(Color.white);

        ButtonGroup interestRadioButtonGroup = new ButtonGroup();
        interestRadioButtonGroup.add(yearlyRadioButton);
        interestRadioButtonGroup.add(semiYearlyRadioButton);
        interestRadioButtonGroup.add(quarterlyRadioButton);
        interestRadioButtonGroup.add(monthlyRadioButton);
        interestRadioButtonGroup.add(dailyRadioButton);

        // Duration panel
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        durationPanel.add(new JLabel("Loan duration "));
        durationPanel.add(new JLabel("Years:"));
        durationPanel.add(yearsTextField = new JTextField("2", 5));
        durationPanel.add(new JLabel("Months:"));
        durationPanel.add(monthsTextField = new JTextField(5));

        doc = (PlainDocument) yearsTextField.getDocument();
        doc.setDocumentFilter(new MyIntRangeFilter(0, 10000));
        doc = (PlainDocument) monthsTextField.getDocument();
        doc.setDocumentFilter(new MyIntRangeFilter(0, 10000));

        // interestResPanel
        resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        resultPanel.add(resultLabel = new JLabel(""));

        // interestResPanel
        interestTotalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        interestTotalPanel.add(interestTotalLabel = new JLabel("Interest total:"));

        // payMonthlyPaymentPanel
        monthlyPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        monthlyPaymentPanel.add(monthlyPaymentLabel = new JLabel("Monthly payment:"));

        // TotalPaymentPanel
        totalPaymentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        totalPaymentsPanel.add(totalPaymentsLabel = new JLabel("Payments total:"));

        mainPanel.add(titlePanel);
        mainPanel.add(loanAmountPanel);
        mainPanel.add(interestPanel);
        mainPanel.add(interestRadioButtonPanel);
        mainPanel.add(durationPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(interestTotalPanel);
        mainPanel.add(monthlyPaymentPanel);
        mainPanel.add(totalPaymentsPanel);
        // mainPanel.add(InterestTotalLabel=new JLabel("Interest total:")); here ihv
        // problem left only must add panel first to move center

        // create buttons:compute,clear & add it to buttonsPanel
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton computeJButton = new JButton("Compute Payment");
        buttonsPanel.add(computeJButton);
        JButton clearJButton = new JButton("Clear");
        buttonsPanel.add(clearJButton);

        // add Action Listener for buttons
        computeJButton.addActionListener(new ComputeButtonListenerClass());
        clearJButton.addActionListener(new ClearButtonListenerClass());

        // customization
        loanAmountTextField.setToolTipText("loan Amount");
        interestRateTextField.setToolTipText("interest rate");
        yearsTextField.setToolTipText("years");
        monthsTextField.setToolTipText("months");

        mainPanel.setBackground(Color.white);
        loanAmountPanel.setBackground(Color.white);
        interestPanel.setBackground(Color.white);
        titlePanel.setBackground(Color.white);
        interestRadioButtonPanel.setBackground(Color.white);
        typeOfInterestCheckBoxPanel.setBackground(Color.white);
        durationPanel.setBackground(Color.white);
        resultPanel.setBackground(Color.white);
        interestTotalPanel.setBackground(Color.white);
        monthlyPaymentPanel.setBackground(Color.white);
        totalPaymentsPanel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.white);

        // Create the main frame & add mainPanel,buttonsPanel
        frame = new JFrame("Loan Calculator");
        jMenuBar = new NaviMenuBar();
        frame.setJMenuBar(jMenuBar);// not add or setMenuBar
        frame.add(mainPanel, BorderLayout.NORTH);
        //// frame.add(InterestTotalPanel,BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void dispose() {
        // Assuming your JFrame is named "frame"
        frame.dispose(); // Closes the window
    }

    private class ComputeButtonListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            compute();

        }
    }

    private class ClearButtonListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clear();
        }
    }

    private class simpleInterestCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberCompoundInterestTextField.setEnabled(false);
        }
    }

    private class compoundInterestCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberCompoundInterestTextField.setEnabled(true);
        }
    }

    private class totalCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            numberCompoundInterestTextField.setEnabled(false);
            // to do
        }
    }

    private void compute() {
        float loanAmount = 0, rate = 0;
        int years = 0, months = 0;
        InterestRate interestOb;
        String errorMessage = ""; // Combine all errors into a single string
        int times = 0;

        if (!loanAmountTextField.getText().isBlank()) {
            try {
                loanAmount = Float.parseFloat(loanAmountTextField.getText().replace("k", "000"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorMessage += "Please enter a valid numeric value for the loan amount.\n";
                loanAmountTextField.setText("");
            }
        } else {
            errorMessage += "Enter a loan Amount. \n";
        }

        if (!interestRateTextField.getText().isBlank()) {
            try {
                // interestRateTextField.setText(o.validate);
                rate = Float.parseFloat(interestRateTextField.getText().replace("%", ""));
                // Calculate the annual interest rate to standardize calculations and laws
                if (dailyRadioButton.isSelected()) {
                    rate = rate * 365.25f;
                } else if (monthlyRadioButton.isSelected()) {
                    rate = rate * 12;
                } else if (quarterlyRadioButton.isSelected()) {
                    rate = rate * 4;
                } else if (semiYearlyRadioButton.isSelected()) {
                    rate = rate * 2;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorMessage += "Invalid a interest rate, enter numeric values only.\n";
                interestRateTextField.setText("");
            }
        } else {
            errorMessage += "Enter the interest rate.\n";
        }

        if (compoundInterestCheckBox.isSelected())
            if (!numberCompoundInterestTextField.getText().isBlank()) {
                try {
                    times = Integer.parseInt(0 + numberCompoundInterestTextField.getText().trim());
                    if (times < 0)
                        errorMessage += "Invalid compound number, Enter a non-negative integer. \n";
                } catch (Exception e) {
                    System.out.println(e.toString());
                    errorMessage += "Invalid compound number, enter numeric values only.\n";
                    numberCompoundInterestTextField.setText("");
                }
            } else {
                errorMessage += "Enter the compound number.\n";
            }

        if (!yearsTextField.getText().isBlank() || !monthsTextField.getText().isBlank()) {
            try {
                years = Integer.parseInt(0 + yearsTextField.getText().trim());// 0+str for not parse empty string
                if (years < 0)
                    errorMessage += "Invalid years, Enter a non-negative integer. \n";
                months = Integer.parseInt(0 + monthsTextField.getText().trim());
                if (months < 0)
                    errorMessage += "Invalid months, Enter a non-negative integer. \n";
                months += years * 12;
            } catch (Exception e) {
                System.out.println(e.toString());
                errorMessage += "Invalid time, Enter integer number only.\n";
                yearsTextField.setText("");
                monthsTextField.setText("");
            }
        } else {
            errorMessage += "Enter the time for payment.\n";
        }

        if (errorMessage.isEmpty()) {
            interestOb = new InterestRate(loanAmount, rate, months, totalCheckBox.isSelected(), times);
            String result = "";
            result += String.format("Loan Amount %.2f$, at the rate of %.2f%%, for %d month: \n", loanAmount, rate,
                    months);
            if (compoundInterestCheckBox.isSelected())
                result += String.format(" with %d times\n", times);
            resultLabel.setText(result);
            result += String.format("Monthly payment: %.2f$\n", interestOb.getPayMonthly());
            result += String.format("Interest total: %.2f$\n", interestOb.getInterest());
            result += String.format("Payments total: %.2f$\n", interestOb.getPayTotalAmount());

            JOptionPane.showMessageDialog(null, result, "result Interest Rate", 1);
            interestTotalLabel.setText(String.format("Interest total: %.2f$", interestOb.getInterest()));
            monthlyPaymentLabel.setText(String.format("Monthly payment: %.2f$", interestOb.getPayMonthly()));
            totalPaymentsLabel.setText(String.format("Payments total: %.2f$", interestOb.getPayTotalAmount()));
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "ALert calc Interest Rate", 2);
        }

    }// end compute func

    public static void clear() {
        loanAmountTextField.setText("");
        interestRateTextField.setText("");
        yearlyRadioButton.setSelected(true);
        yearsTextField.setText("");
        monthsTextField.setText("");

        simpleInterestCheckBox.setSelected(true);
        numberCompoundInterestTextField.setText("");
        numberCompoundInterestTextField.setEnabled(false);
        resultLabel.setText("");
        interestTotalLabel.setText("");
        monthlyPaymentLabel.setText("");
        totalPaymentsLabel.setText("");

    }// end clear func

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterestRateCalculator();
            }
        });
    }// end main

}

class InterestRate {
    private float payMonthly, payTotalAmount, interest;

    public InterestRate(float loanAmount, float interestRate, int months, boolean isTotal, int times) {
        if (isTotal) {
            calculateInterestRateForTotal(loanAmount, interestRate, months);
        } else if (times == 0) {
            CalculateSimpleInterest(loanAmount, interestRate, months);
        } else {
            CalculateCompoundInterest(loanAmount, interestRate, months, times);
        }
    }

    private void calculateInterestRateForTotal(float loanAmount, float interestRate, int months) {
        payTotalAmount = loanAmount * (100 + interestRate) / 100;
        if (months != 0)
            payMonthly = payTotalAmount / months;
        else
            payMonthly = payTotalAmount;
        interest = payTotalAmount - loanAmount;
    }

    private void CalculateSimpleInterest(float loanAmount, float interestRate, int months) {
        /*
         * Simple interest is a quick method of calculating the interest charge on a
         * loan.
         * Simple interest is determined by multiplying the daily interest rate by the
         * principal by the number of days that elapse between payments.
         * 
         * Simple Interest = (p × r × t)/100
         * 
         * where p = Principal Amount, r = Rate percent Annum, t = Time (period of
         * years)
         * 
         * A = p+prt/100 = p+pRt = p*(1 + Rt)
         * R = r / 100
         * 
         * Where
         * A = total accrued amount (principal + interest)
         * R = rate of interest per year in decimal; r = R*100 % percent
         */
        float p = loanAmount, r = interestRate / 100, y = months / 12;
        interest = p * r * y;
        payTotalAmount = p + interest;
        if (months == 0)
            payMonthly = payTotalAmount;
        else
            payMonthly = payTotalAmount / months;
    }

    private void CalculateCompoundInterest(float loanAmount, float interestRate, int months, int times) {
        /*
         * CI Annual = principle* (pow((1 + rate / 100), numbers));
         * payTotalAmount= CI = principle* (pow((1 + rate / 100), numbers*years));
         * interest= I = principle* (pow((1 + rate / 100), numbers*years))-principle;
         */
        float p = loanAmount, r = interestRate, y = months / 12;
        int n = times;// times: Number of Times interest Compounded
        interest = (float) (p * Math.pow((1 + r / 100), y * n)) - p;
        payTotalAmount = interest + p;
        if (months == 0)
            payMonthly = payTotalAmount;
        else
            payMonthly = payTotalAmount / months;
    }

    public float getPayTotalAmount() {

        return payTotalAmount;
    }

    public float getPayMonthly() {
        return payMonthly;
    }

    public float getInterest() {
        return interest;
    }

}

/*
 * to do
 * // date=LocalDate.getInstance();
 * // date.add(Year, year);
 * 
 * // keyboard enter calc
 */

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
        if (currentText.isEmpty() || isNumeric(currentText) && isDecimal(currentText)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    boolean isNumeric(String percent) {
        percent = percent.replace(".", "");// isNumeric
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

class MyIntRangeFilter extends DocumentFilter {
    /*
     * range integer numbers
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

        if (currentText.isEmpty() || isIntRange(currentText)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            // warn the user and don't allow the insert
        }
    }

    private boolean isIntRange(String text) {
        try {
            int number = Integer.parseInt(text);// check if int
            return number >= MIN && number <= MAX;// check if range
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

/*
 * 
 * if added a percentage, you cannot add numbers after it.
 * Note that I want the range of numbers between min and max.
 */
class MypercentFilter extends DocumentFilter {
    /*
     * percentage only
     * for use class
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
        if (currentText.isEmpty() || isContainsPercent && isNumeric(currentText) && isInRange(currentText)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            String errorMessage = "Invalid a interest rate, enter numeric values only.\n";
            JOptionPane.showMessageDialog(null, errorMessage, "ALert calc Interest Rate", 2);
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

    boolean isNumeric(String percent) {
        percent = percent.replace(".", "");
        return percent.matches("\\d*");
    }

    private boolean isInRange(String text) {
        try {
            double number = Double.parseDouble(text);// isDecimal
            return number >= MIN && number <= MAX;// isRange
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class NaviMenuBar extends JMenuBar {// extends JMenuBar {

    public NaviMenuBar() {
        // Create menus
        JMenu InterestRateMenu = new JMenu("Interestrate Calculator");
        JMenu fileMenu = new JMenu("Home");
        JMenu editMenu = new JMenu("Log");
        JMenu helpMenu = new JMenu("Help");

        // Create menu items
        JMenuItem clearItem = new JMenuItem("Clear feilds");
        JMenuItem restartItem = new JMenuItem("Restart");
        JMenuItem closeItem = new JMenuItem("Close");

        JMenuItem documentItem = new JMenuItem("Document");
        JMenuItem websiteItem = new JMenuItem("Website");
        JMenuItem shortcutItem = new JMenuItem("Shortcut");
        JMenuItem aboutItem = new JMenuItem("About the Developer");

        JMenuItem openLogItem = new JMenuItem("Open log");
        JMenuItem saveLogItem = new JMenuItem("Save log");
        JMenuItem clearLogItem = new JMenuItem("Clear log");

        InterestRateMenu.setEnabled(false);

        clearItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InterestRateCalculator.clear();
            }
        });

        restartItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        InterestRateCalculator.dispose();
                        new InterestRateCalculator();
                    }
                });
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterestRateCalculator.dispose();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            String aboutMe = "Fullstack developer with flutter dart frontend\nPy django or Php pure backend\nGithub:mmsbah191\nContact for projects email:mmsbah191@outlook.com\n";

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, aboutMe, "About the developer", JOptionPane.PLAIN_MESSAGE);
            }
        });

        saveLogItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Add menu items to menus
        fileMenu.add(clearItem);
        fileMenu.add(helpMenu);// nested menu
        fileMenu.addSeparator();
        fileMenu.add(restartItem);
        fileMenu.add(closeItem);

        editMenu.add(openLogItem);
        editMenu.add(saveLogItem);
        editMenu.addSeparator();
        editMenu.add(clearLogItem);

        helpMenu.add(shortcutItem);
        helpMenu.add(websiteItem);
        helpMenu.add(documentItem);
        helpMenu.add(aboutItem);

        // add to JMenuBar panel
        add(InterestRateMenu);
        add(fileMenu);
        add(editMenu);

        // custmers not effect never
        // setPreferredSize(new Dimension(900, 35));

        // setFont(new Font("Arial", Font.BOLD, 10));
        // setForeground(Color.blue);
        // setLayout(new FlowLayout());

        // Set the desired RGB color values
        setBackground(new Color(255, 255, 255));// or
        UIManager.put("MenuBar.background", Color.BLACK);
    }

}
