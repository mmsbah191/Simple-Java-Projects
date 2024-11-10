package p6;

/*
 * //add check month
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class p6InterestCalculator {

    private JLabel errorAmountLabel, errorInterestLabel, errorYearsLabel;

    private JPanel mainPanel, buttonsPanel;
    private JTextField amountTextField, interestRateTextField, yearsTextField;
    private JCheckBox annualCheckbox;

    private JLabel monthlyResLabel, totalResLabel;

    private JTextField monthsTextField;

    p6InterestCalculator() {

        mainPanel = new JPanel(new GridLayout(5, 1));
        // mainPanel.setBorder(new TitledBorder("Enter loan Amount, Interst, &
        // years:"));

        //
        JPanel amountThe2Line = new JPanel();
        amountThe2Line.add(new JLabel("Loan Amount (k):"));
        amountThe2Line.add(amountTextField = new JTextField("12k", 10));

        JPanel amountErrorPanel = new JPanel();
        amountErrorPanel.add(errorAmountLabel = new JLabel(""));

        JPanel amountPanel = new JPanel(new BorderLayout(0, 10));
        amountPanel.add(amountErrorPanel, BorderLayout.NORTH);
        amountPanel.add(amountThe2Line, BorderLayout.CENTER);

        //
        JPanel interestThe2Line = new JPanel();
        interestThe2Line.add(new JLabel("Interst rate (%):"));
        interestThe2Line.add(interestRateTextField = new JTextField("10%", 5));
        interestThe2Line.add(annualCheckbox = new JCheckBox("Yearly", true));

        JPanel interestErrorPanel = new JPanel();
        interestErrorPanel.add(errorInterestLabel = new JLabel(""));

        JPanel interestPanel = new JPanel(new BorderLayout(0, 10));
        interestPanel.add(interestErrorPanel, BorderLayout.NORTH);
        interestPanel.add(interestThe2Line, BorderLayout.CENTER);

        //
        JPanel yearsThe2Line = new JPanel();
        yearsThe2Line.add(new JLabel("Years:"));
        yearsThe2Line.add(yearsTextField = new JTextField("2", 5));
        yearsThe2Line.add(new JLabel("Months:"));
        yearsThe2Line.add(monthsTextField = new JTextField(5));

        JPanel yearsErrorPanel = new JPanel();
        yearsErrorPanel.add(errorYearsLabel = new JLabel(""));

        JPanel yearsPanel = new JPanel(new BorderLayout(0, 10));
        yearsPanel.add(yearsErrorPanel, BorderLayout.NORTH);
        yearsPanel.add(yearsThe2Line, BorderLayout.CENTER);

        JPanel payMonthlyPaymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        payMonthlyPaymentPanel.add(new JLabel("payMonthlyPayment:"));
        payMonthlyPaymentPanel.add(monthlyResLabel = new JLabel());

        JPanel TotalPaymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        TotalPaymentPanel.add(new JLabel("Total payment:"));
        TotalPaymentPanel.add(totalResLabel = new JLabel());

        mainPanel.add(amountPanel);
        mainPanel.add(interestPanel);
        mainPanel.add(yearsPanel);
        mainPanel.add(payMonthlyPaymentPanel);
        mainPanel.add(TotalPaymentPanel);

        amountTextField.setToolTipText("amount");
        interestRateTextField.setToolTipText("interst rate");
        yearsTextField.setToolTipText("years");
        monthsTextField.setToolTipText("months");

        // create buttons:compute,clear & add it to buttonsPanel
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton computeJButton = new JButton("Compute Payment");
        buttonsPanel.add(computeJButton);
        JButton clearJButton = new JButton("Clear");
        buttonsPanel.add(clearJButton);

        // addActionListener for buttons
        computeJButton.addActionListener(new ComputeJButtonListenerClass());
        clearJButton.addActionListener(new ClearJButtonListenerClass());

        // Create the main frame & add mainPanel,buttonsPanel
        JFrame frame = new JFrame("Loan Calculator");
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new p6InterestCalculator();
    }// end main

    private class ComputeJButtonListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            compute();
        }
    }// inner class

    private class ClearJButtonListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clear();
        }
    }// inner class

    private void compute() {
        float amount = 0, interst = 0, years = 0, months = 0;
        String error1 = "", error2 = "", error3 = "";
        InterstRate interestOb;

        if (!amountTextField.getText().isEmpty()) {
            try {
                amount = getValue(amountTextField);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error1 = "Invalid amount, Enter numirec values.";
            }
        } else {
            error1 = "Enter amount:";
        }

        if (!interestRateTextField.getText().isBlank()) {
            try {
                interst = getValue(interestRateTextField);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error2 = "Invalid interst.";
                interestRateTextField.setText("");
            }
        } else {
            error2 = "Enter interst:";
        }

        if (!yearsTextField.getText().isBlank() || !monthsTextField.getText().isBlank()) {
            try {
                years = getValue(yearsTextField);
                months = getValue(monthsTextField);
                months += years * 12;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error3 = "Invalid year.";
                yearsTextField.setText("");
            }
        } else {
            error3 = "Enter year:";
        }

        if (error1.isBlank() && error2.isBlank() && error3.isBlank()) {
            interestOb = new InterstRate(amount, interst, months, annualCheckbox.isSelected());
            monthlyResLabel.setText(String.format("%.2f$", interestOb.getPayMonthly()));
            totalResLabel.setText(String.format("%.2f$", interestOb.getLoanAmount()));
        } else {
            errorAmountLabel.setText(error1);
            errorInterestLabel.setText(error2);
            errorYearsLabel.setText(error3);
        }

    }// end compute func

    private float getValue(JTextField jtf) {
        String val = jtf.getText().replace("$", "");
        val = val.replace("%", "");
        val = val.replace("k", "000");
        val = val.replace("K", "000");

        return Float.parseFloat(val);
    }

    private void clear() {
        amountTextField.setText("");
        interestRateTextField.setText("");
        yearsTextField.setText("");
        monthlyResLabel.setText("");
        totalResLabel.setText("");
    }// end clear func

}// end p6LoanCalculator

class InterstRate {
    private float payMonthly, loanAmount;

    public InterstRate(float amount, float interst, float months, boolean yearly) {
        if (yearly)
            calculateInterestRateYearly(amount, interst, months);
        else
            calculateInterestRateForTotal(amount, interst, months);
    }

    private void calculateInterestRateForTotal(float amount, float interst, float months) {
        loanAmount = amount * (100 + interst) / 100;
        if (months != 0)
            payMonthly = loanAmount / months;
        else
            payMonthly = 0;
        // date=LocalDate.getInstance();
        // date.add(Year, year);
    }

    private void calculateInterestRateYearly(float amount, float interst, float months) {
        float perMonth = interst / 12;
        payMonthly = amount * perMonth;
        loanAmount = months * payMonthly;
    }

    public float getPayMonthly() {
        return payMonthly;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

}// end loan class
