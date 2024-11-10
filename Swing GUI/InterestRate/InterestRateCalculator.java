package p6;

/*
 * //add check month
 */
import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

class p6InterestRateCalculator {

    private final JPanel mainPanel, buttonsPanel;
    private final JTextField loanAmountTextField, interestRateTextField, yearsTextField, monthsTextField;
    private final JCheckBox totalCheckbox, yearlyCheckbox, monthlyCheckbox, dailyCheckbox;
    private final JLabel interestTotalLabel, monthlyPaymentLabel, totalPaymentsLabel;

    p6InterestRateCalculator() {

        mainPanel = new JPanel(new GridLayout(7, 1));
        TitledBorder titleBorder = BorderFactory.createTitledBorder("Enter  loan Amount, Interst rate and duration:");
        mainPanel.setBorder(titleBorder);

        // Loan amount panel
        JPanel loanAmountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        loanAmountPanel.add(new JLabel("Loan Amount (k):"));
        loanAmountPanel.add(loanAmountTextField = new JTextField("12k", 10));

        // Interest rate panel
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        interestPanel.add(new JLabel("Interest rate (%):"));
        interestPanel.add(interestRateTextField = new JTextField("10%", 5));

        JPanel interestCheckboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, -5));
        interestCheckboxPanel.add(totalCheckbox = new JCheckBox("total"));
        interestCheckboxPanel.add(yearlyCheckbox = new JCheckBox("Yearly", true));
        interestCheckboxPanel.add(monthlyCheckbox = new JCheckBox("monthly"));
        interestCheckboxPanel.add(dailyCheckbox = new JCheckBox("daily"));

        ButtonGroup checkboxGroup = new ButtonGroup();
        checkboxGroup.add(totalCheckbox);
        checkboxGroup.add(yearlyCheckbox);
        checkboxGroup.add(monthlyCheckbox);
        checkboxGroup.add(dailyCheckbox);

        //// if you make interest im one line 1-remove comments & change interestPanel
        //// to interestThe2Line & change grid 7 & remove row grid
        // JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0, 0));
        // interestPanel.add(interestThe2Line);
        // interestPanel.add(checkboxPanel);

        // Duration panel
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        durationPanel.add(new JLabel("Loan duration "));
        durationPanel.add(new JLabel("Years:"));
        durationPanel.add(yearsTextField = new JTextField("2", 5));
        durationPanel.add(new JLabel("Months:"));
        durationPanel.add(monthsTextField = new JTextField(5));
        durationPanel.add(new JLabel("take Integer only"));

        // interestResPanel
        JPanel InterestTotalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        InterestTotalPanel.add(interestTotalLabel = new JLabel("Interest total:"));

        // payMonthlyPaymentPanel
        JPanel MonthlyPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        MonthlyPaymentPanel.add(monthlyPaymentLabel = new JLabel("Monthly payment:"));

        // TotalPaymentPanel
        JPanel totalPaymentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        totalPaymentsPanel.add(totalPaymentsLabel = new JLabel("Payments total:"));

        mainPanel.add(loanAmountPanel);
        mainPanel.add(interestPanel);
        mainPanel.add(interestCheckboxPanel);
        mainPanel.add(durationPanel);
        mainPanel.add(InterestTotalPanel);
        mainPanel.add(MonthlyPaymentPanel);
        mainPanel.add(totalPaymentsPanel);
        // mainPanel.add(InterestTotalLabel=new JLabel("Interest total:")); here ihv
        // problem left only must add panel first to move center

        loanAmountTextField.setToolTipText("loan Amount");
        interestRateTextField.setToolTipText("interest rate");
        yearsTextField.setToolTipText("years");
        monthsTextField.setToolTipText("months");

        // create buttons:compute,clear & add it to buttonsPanel
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton computeJButton = new JButton("Compute Payment");
        buttonsPanel.add(computeJButton);
        JButton clearJButton = new JButton("Clear");
        buttonsPanel.add(clearJButton);

        // add Action Listener for buttons
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
        float loanAmount = 0, interest = 0;
        int years = 0, months = 0;
        InterestRate interestOb;
        String errorMessage = ""; // Combine all errors into a single string

        if (!loanAmountTextField.getText().isEmpty()) {
            try {
                loanAmount = Float.parseFloat(loanAmountTextField.getText().replace("k", "000"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorMessage += "Invalid loan amount. Enter a numeric values. \n";
                loanAmountTextField.setText("");
            }
        } else {
            errorMessage += "Enter a loan Amount. \n";
        }

        if (!interestRateTextField.getText().isEmpty()) {
            try {
                interest = Float.parseFloat(interestRateTextField.getText().replace("%", ""));
                // Calculate the annual interest rate to standardize calculations and laws
                if (dailyCheckbox.isSelected()) {
                    interest = interest * 365.25f;
                } else if (monthlyCheckbox.isSelected()) {
                    interest = interest * 12;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorMessage += "Invalid a interest rate, Enter numirec values.\n";
                interestRateTextField.setText("");
                interestRateTextField.setText("");
            }
        } else {
            errorMessage += "Enter a interest rate.\n";
        }

        if (!yearsTextField.getText().isEmpty() || !monthsTextField.getText().isEmpty()) {
            try {
                years = Integer.parseInt(0 + yearsTextField.getText());// 0+str for not parse empty string
                if (years < 0)
                    errorMessage += "Invalid years. Enter a non-negative integer. \n";
                months = Integer.parseInt(0 + monthsTextField.getText());
                if (months < 0)
                    errorMessage += "Invalid months. Enter a non-negative integer. \n";
                months += years * 12;
            } catch (Exception e) {
                System.out.println(e.toString());
                errorMessage += "Invalid the time, Enter integer number only.\n";
                yearsTextField.setText("");
                monthsTextField.setText("");
            }
        } else {
            errorMessage += "Enter the time.\n";
        }

        if (errorMessage.isBlank()) {
            interestOb = new InterestRate(loanAmount, interest, months, totalCheckbox.isSelected());
            interestTotalLabel.setText(String.format("Interest total: %.2f$", interestOb.getPayMonthly()));
            monthlyPaymentLabel.setText(String.format("Monthly payment: %.2f$", interestOb.getPayMonthly()));
            totalPaymentsLabel.setText(String.format("Payments total: %.2f$", interestOb.getPayTotalAmount()));
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "ALert calc Interest Rate", 2);
        }

    }// end compute func

    private void clear() {
        loanAmountTextField.setText("");
        interestRateTextField.setText("");
        yearlyCheckbox.setSelected(true);
        yearsTextField.setText("");
        monthsTextField.setText("");

        // Remove the result payment labels
        interestTotalLabel.setText("");
        monthlyPaymentLabel.setText("");
        totalPaymentsLabel.setText("");
    }// end clear func

    public static void main(String[] args) {
        new p6InterestRateCalculator();
    }// end main

}// end p6LoanCalculator

class InterestRate {
    private float payMonthly, payTotalAmount, interest;

    public InterestRate(float loanAmount, float interestRate, float months, boolean isTotal) {
        if (isTotal) {
            calculateInterestRateForTotal(loanAmount, interestRate, months);
        } else {
            CalculateSimpleInterest(loanAmount, interestRate, months);
        }
    }

    private void calculateInterestRateForTotal(float loanAmount, float interestRate, float months) {
        payTotalAmount = loanAmount * (100 + interestRate) / 100;
        if (months != 0)
            payMonthly = payTotalAmount / months;
        else
            payMonthly = payTotalAmount;
        interest = payTotalAmount - payMonthly;
        // date=LocalDate.getInstance();
        // date.add(Year, year);

        // 10, 000$ at the rate of 5% for 5
    }

    private void calculateInterestRateYearly(float loanAmount, float interestRate, float months) {
        float monthlyInterestRate = interestRate / 12 / 100;
        int totalPayments = (int) Math.ceil(months / 12); // Use Math.ceil for potential non-integer years
        payMonthly = (loanAmount * monthlyInterestRate)
                / (1 - (float) Math.pow(1 + monthlyInterestRate, -totalPayments));
    }

    private void CalculateSimpleInterest(float loanAmount, float interestRate, float months) {
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
        payTotalAmount = p + p * r * y;
        if (months == 0)
            payMonthly = payTotalAmount;
        else
            payMonthly = payTotalAmount / months;
    }

    private void CalculateCompoundInterest(float loanAmount, float interestRate, float months) {
        float principal = loanAmount, rate = interestRate, years = months / 12;
        int number = 2;// number: Number of Time interest Compounded
        payTotalAmount = (float) (principal * (Math.pow((1 + rate / 100), (years * number))) - principal);
    }

    public float getPayMonthly() {
        return payMonthly;
    }

    public float getPayTotalAmount() {
        return payTotalAmount;
    }

}// end loan class

/*
 *
 * interestLabal
 * new Options or buttons
 */