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

class p6InterestRateCalculator {

    private JLabel errorloanAmountLabel, errorInterestLabel, errorYearsLabel;

    private JPanel mainPanel, buttonsPanel;
    private JTextField loanAmountTextField, interestRateTextField, yearsTextField;
    private JCheckBox annualCheckbox;

    private JLabel monthlyResLabel, totalResLabel;

    private JTextField monthsTextField;

    p6InterestRateCalculator() {

        mainPanel = new JPanel(new GridLayout(5, 1));
        // mainPanel.setBorder(new TitledBorder("Enter loan loanAmount, Interst, &
        // years:"));

        //
        JPanel loanAmountThe2Line = new JPanel();
        loanAmountThe2Line.add(new JLabel("Loan loanAmount (k):"));
        loanAmountThe2Line.add(loanAmountTextField = new JTextField("12k", 10));

        JPanel loanAmountErrorPanel = new JPanel();
        loanAmountErrorPanel.add(errorloanAmountLabel = new JLabel(""));

        JPanel loanAmountPanel = new JPanel(new BorderLayout(0, 10));
        loanAmountPanel.add(loanAmountErrorPanel, BorderLayout.NORTH);
        loanAmountPanel.add(loanAmountThe2Line, BorderLayout.CENTER);

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

        mainPanel.add(loanAmountPanel);
        mainPanel.add(interestPanel);
        mainPanel.add(yearsPanel);
        mainPanel.add(payMonthlyPaymentPanel);
        mainPanel.add(TotalPaymentPanel);

        loanAmountTextField.setToolTipText("loanAmount");
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
        new p6InterestRateCalculator();
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
        float loanAmount = 0, interst = 0;
        int years = 0, months = 0;
        String error1 = "", error2 = "", error3 = "";
        InterstRate interestOb;

        if (!loanAmountTextField.getText().isEmpty()) {
            try {
                loanAmount = getValue(loanAmountTextField);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error1 = "Invalid loanAmount, Enter numirec values.";
            }
        } else {
            error1 = "Enter loanAmount:";
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

        if (!yearsTextField.getText().isEmpty() || !monthsTextField.getText().isEmpty()) {
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
            interestOb = new InterstRate(loanAmount, interst, months, annualCheckbox.isSelected());
            monthlyResLabel.setText(String.format("%.2f$", interestOb.getPayMonthly()));
            totalResLabel.setText(String.format("%.2f$", interestOb.getPayAmount()));
        } else {
            errorloanAmountLabel.setText(error1);
            errorInterestLabel.setText(error2);
            errorYearsLabel.setText(error3);
        }

    }// end compute func

    private int getValue(JTextField jtf) {
        String text = jtf.getText().replace("$", "");
        text = text.replace("%", "");
        text = text.replace("k", "000");
        text = text.replace("K", "000");

        if (text.isEmpty())
            return 0;
        return Integer.parseInt(text);
    }

    private void clear() {
        loanAmountTextField.setText("");
        interestRateTextField.setText("");
        yearsTextField.setText("");
        monthlyResLabel.setText("");
        totalResLabel.setText("");
    }// end clear func

}// end p6LoanCalculator

class InterstRate {
    private float payMonthly, payAmount;

    public InterstRate(float loanAmount, float interestRate, float months, boolean yearly) {
        if (yearly)
            CalculateSimpleInterest(loanAmount, interestRate, months);
        else
            calculateInterestRateForTotal(loanAmount, interestRate, months);
    }

    private void calculateInterestRateForTotal(float loanAmount, float interestRate, float months) {
        payAmount = loanAmount * (100 + interestRate) / 100;
        if (months != 0)
            payMonthly = payAmount / months;
        else
            payMonthly = 0;
        // date=LocalDate.getInstance();
        // date.add(Year, year);
    }

    private void calculateInterestRateYearly(float loanAmount, float interestRate, float months) {
        float monthlyInterestRate = interestRate / 12 / 100;
        int totalPayments = (int) Math.ceil(months / 12); // Use Math.ceil for potential non-integer years
        payMonthly = (loanAmount * monthlyInterestRate)
                / (1 - (float) Math.pow(1 + monthlyInterestRate, -totalPayments));
    }

    public float getPayMonthly() {
        return payMonthly;
    }

    public float getPayAmount() {
        return payAmount;
    }

    private void CalculateSimpleInterest(float loanAmount, float interestRate, float months) {
        float p = loanAmount, r = interestRate, y = months / 12;
        payAmount = (p * r * y) / 100;
    }

    private void CalculateCompoundInterest(float loanAmount, float interestRate, float months) {
        float principal = loanAmount, rate = interestRate, years = months / 12;
        float number;
        // number:Number of Time interest Compounded
        payAmount = (float) (principal * (Math.pow((1 + rate / 100), (years * 1))) - principal);
    }

}// end loan class
