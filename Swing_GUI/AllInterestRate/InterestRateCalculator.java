package Swing_GUI.AllInterestRate;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

class InterestRateCalculator {

    private final JPanel mainPanel, buttonsPanel;
    private final JTextField loanAmountTextField,
            numberCompoundInterestTextField, interestRateTextField,
            yearsTextField, monthsTextField;
    // private final J
    private final JCheckBox simpleInterestCheckBox, compoundInterestCheckBox,
            totalCheckBox;
    private final JRadioButton yearlyRadioButton, semiYearlyRadioButton,
            quarterlyRadioButton, monthlyRadioButton,
            dailyRadioButton;
    private final JLabel interestTotalLabel, monthlyPaymentLabel,
            totalPaymentsLabel;

    InterestRateCalculator() {

        mainPanel = new JPanel(new GridLayout(7, 1));
        TitledBorder titleBorder = BorderFactory.createTitledBorder("Enter loan Amount, Interst rate and duration:");
        mainPanel.setBorder(titleBorder);

        // Loan amount panel
        JPanel loanAmountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,
                10));
        loanAmountPanel.add(new JLabel("Loan Amount (k):"));
        loanAmountPanel.add(loanAmountTextField = new JTextField("12k", 10));

        // Type Of Interest
        JPanel typeOfInterestCheckBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        typeOfInterestCheckBoxPanel.add(simpleInterestCheckBox = new JCheckBox("Simple", true));
        typeOfInterestCheckBoxPanel.add(totalCheckBox = new JCheckBox("Total"));
        typeOfInterestCheckBoxPanel.add(compoundInterestCheckBox = new JCheckBox("Compound"));
        typeOfInterestCheckBoxPanel.add(numberCompoundInterestTextField = new JTextField("", 2));
        numberCompoundInterestTextField.setEnabled(false);

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

        // interest RadioButton Panel
        JPanel interestRadioButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        interestRadioButtonPanel.add(new JLabel("interest rate Frequency:"));
        interestRadioButtonPanel.add(yearlyRadioButton = new JRadioButton("Annually",
                true));
        interestRadioButtonPanel.add(semiYearlyRadioButton = new JRadioButton("Semiannually"));
        interestRadioButtonPanel.add(quarterlyRadioButton = new JRadioButton("Quarterly"));
        interestRadioButtonPanel.add(monthlyRadioButton = new JRadioButton("Monthly"));
        interestRadioButtonPanel.add(dailyRadioButton = new JRadioButton("Daily"));

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
        durationPanel.add(new JLabel("take Integer only"));

        // interestResPanel
        JPanel InterestTotalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,
                5));
        InterestTotalPanel.add(interestTotalLabel = new JLabel("Interest total:"));

        // payMonthlyPaymentPanel
        JPanel MonthlyPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,
                5));
        MonthlyPaymentPanel.add(monthlyPaymentLabel = new JLabel("Monthly payment:"));

        // TotalPaymentPanel
        JPanel totalPaymentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,
                5));
        totalPaymentsPanel.add(totalPaymentsLabel = new JLabel("Payments total:"));

        mainPanel.add(loanAmountPanel);
        mainPanel.add(interestPanel);
        mainPanel.add(interestRadioButtonPanel);
        mainPanel.add(durationPanel);
        mainPanel.add(InterestTotalPanel);
        mainPanel.add(MonthlyPaymentPanel);
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

        // mainPanel.setBackground(Color.white);
        // loanAmountPanel.setBackground(Color.white);
        // interestPanel.setBackground(Color.white);
        // typeOfInterestPanel.setBackground(Color.white);
        // interestCheckboxPanel.setBackground(Color.white);
        // durationPanel.setBackground(Color.white);
        // InterestTotalPanel.setBackground(Color.white);
        // MonthlyPaymentPanel.setBackground(Color.white);
        // totalPaymentsPanel.setBackground(Color.white);
        // buttonsPanel.setBackground(Color.white);

        // Create the main frame & add mainPanel,buttonsPanel
        JFrame frame = new JFrame("Loan Calculator");
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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
        float loanAmount = 0, interest = 0;
        int years = 0, months = 0;
        InterestRate interestOb;
        String errorMessage = ""; // Combine all errors into a single string
        int times = 0;

        if (!loanAmountTextField.getText().isBlank()) {
            try {
                loanAmount = Float.parseFloat(loanAmountTextField.getText().replace("k",
                        "000"));
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
                interest = Float.parseFloat(interestRateTextField.getText().replace("%",
                        ""));
                // Calculate the annual interest rate to standardize calculations and laws
                if (dailyRadioButton.isSelected()) {
                    interest = interest * 365.25f;
                } else if (monthlyRadioButton.isSelected()) {
                    interest = interest * 12;
                } else if (quarterlyRadioButton.isSelected()) {
                    interest = interest * 4;
                } else if (semiYearlyRadioButton.isSelected()) {
                    interest = interest * 2;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                errorMessage += "Invalid a interest rate, Enter numirec values.\n";
                interestRateTextField.setText("");
                interestRateTextField.setText("");
            }
        } else {
            errorMessage += "Enter the interest rate.\n";
        }

        if (compoundInterestCheckBox.isSelected())
            if (!numberCompoundInterestTextField.getText().isBlank()) {
                try {
                    times = Integer.parseInt(0 +
                            numberCompoundInterestTextField.getText().trim());
                    if (times < 0)
                        errorMessage += "Invalid compound number . Enter a non-negative integer. \n";
                } catch (Exception e) {
                    System.out.println(e.toString());
                    errorMessage += "Invalid compound number, Enter integer number only.\n";
                    numberCompoundInterestTextField.setText("");
                }
            } else {
                errorMessage += "Enter the compound number of .\n";
            }

        if (!yearsTextField.getText().isBlank() ||
                !monthsTextField.getText().isBlank()) {
            try {
                years = Integer.parseInt(0 + yearsTextField.getText().trim());// 0+str for not parse empty string
                if (years < 0)
                    errorMessage += "Invalid years. Enter a non-negative integer. \n";
                months = Integer.parseInt(0 + monthsTextField.getText().trim());
                if (months < 0)
                    errorMessage += "Invalid months. Enter a non-negative integer. \n";
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
            interestOb = new InterestRate(loanAmount, interest, months,
                    totalCheckBox.isSelected(), times);
            interestTotalLabel.setText(String.format("Interest total: %.2f$",
                    interestOb.getInterest()));
            monthlyPaymentLabel.setText(String.format("Monthly payment: %.2f$",
                    interestOb.getPayMonthly()));
            totalPaymentsLabel.setText(String.format("Payments total: %.2f$",
                    interestOb.getPayTotalAmount()));
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "ALert calc Interest Rate",
                    2);
        }

    }// end compute func

    private void clear() {
        loanAmountTextField.setText("");
        interestRateTextField.setText("");
        yearlyRadioButton.setSelected(true);
        yearsTextField.setText("");
        monthsTextField.setText("");

        // Remove the results labels
        interestTotalLabel.setText("");
        monthlyPaymentLabel.setText("");
        totalPaymentsLabel.setText("");

        simpleInterestCheckBox.setSelected(true);
        numberCompoundInterestTextField.setText("");
        numberCompoundInterestTextField.setEnabled(false);
    }// end clear func

    public static void main(String[] args) {
        new InterestRateCalculator();
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

    private void CalculateSimpleInterest(float loanAmount, float interestRate,
            int months) {
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

    private void CalculateCompoundInterest(float loanAmount, float interestRate,
            int months, int times) {
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
 * // 10, 000$ at the rate of 5% for 5
 */
