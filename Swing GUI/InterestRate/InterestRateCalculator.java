package p6;

/*
 * //add check month
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class p6InterestRateCalculator {

    private JLabel errorloanAmountLabel, errorInterestLabel, errorDurationLabel;

    private JPanel mainPanel, buttonsPanel;
    private JTextField loanAmountTextField, interestRateTextField, yearsTextField;
    private JCheckBox totalCheckbox, yearlyCheckbox, monthlyCheckbox, dailyCheckbox;

    private JLabel monthlyResLabel, totalResLabel;

    private JTextField monthsTextField;

    p6InterestRateCalculator() {

        mainPanel = new JPanel(new GridLayout(5, 1));
        mainPanel = new JPanel(new GridLayout(5, 1));

        // TitledBorder titleBorder = BorderFactory.createTitledBorder("Enter loan
        // Amount, Interst and years:");
        // mainPanel.setBorder(titleBorder);

        //
        JPanel loanAmountErrorPanel = new JPanel();
        loanAmountErrorPanel.add(errorloanAmountLabel = new JLabel(""));

        JPanel loanAmountThe2Line = new JPanel();
        loanAmountThe2Line.add(new JLabel("Loan Amount (k):"));
        loanAmountThe2Line.add(loanAmountTextField = new JTextField("12k", 10));

        JPanel loanAmountPanel = new JPanel(new BorderLayout(0, 0));
        loanAmountPanel.add(loanAmountErrorPanel, BorderLayout.NORTH);
        loanAmountPanel.add(loanAmountThe2Line, BorderLayout.CENTER);

        //
        JPanel interestErrorPanel = new JPanel();
        interestErrorPanel.add(errorInterestLabel = new JLabel(""));

        JPanel interestThe2Line = new JPanel();
        interestThe2Line.add(new JLabel("Interest rate (%):"));
        interestThe2Line.add(interestRateTextField = new JTextField("10%", 5));

        JPanel checkboxPanel = new JPanel();

        checkboxPanel.add(totalCheckbox = new JCheckBox("total"));
        checkboxPanel.add(yearlyCheckbox = new JCheckBox("Yearly", true));
        checkboxPanel.add(monthlyCheckbox = new JCheckBox("monthly"));
        checkboxPanel.add(dailyCheckbox = new JCheckBox("daily"));

        ButtonGroup checkboxGroup = new ButtonGroup();
        checkboxGroup.add(totalCheckbox);
        checkboxGroup.add(yearlyCheckbox);
        checkboxGroup.add(monthlyCheckbox);
        checkboxGroup.add(dailyCheckbox);

        JPanel interestPanel = new JPanel(new BorderLayout(0, 0));
        interestPanel.add(interestErrorPanel, BorderLayout.NORTH);
        interestPanel.add(interestThe2Line, BorderLayout.CENTER);
        interestPanel.add(checkboxPanel, BorderLayout.SOUTH);

        //
        JPanel durationErrorPanel = new JPanel();
        durationErrorPanel.add(errorDurationLabel = new JLabel(""));

        JPanel durationThe2Line = new JPanel();
        durationThe2Line.add(new JLabel("Years:"));
        durationThe2Line.add(yearsTextField = new JTextField("2", 5));
        durationThe2Line.add(new JLabel("Months:"));
        durationThe2Line.add(monthsTextField = new JTextField(5));

        JPanel durationPanel = new JPanel(new BorderLayout());
        durationPanel.add(durationErrorPanel, BorderLayout.NORTH);
        durationPanel.add(durationThe2Line, BorderLayout.CENTER);

        JPanel payMonthlyPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        payMonthlyPaymentPanel.add(new JLabel("payMonthlyPayment:"));
        payMonthlyPaymentPanel.add(monthlyResLabel = new JLabel());

        JPanel TotalPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        TotalPaymentPanel.add(new JLabel("Total payment:"));
        TotalPaymentPanel.add(totalResLabel = new JLabel());

        mainPanel.add(loanAmountPanel);
        mainPanel.add(interestPanel);
        // mainPanel.add(checkboxPanel);
        mainPanel.add(durationThe2Line);
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
        float loanAmount = 0, interest = 0;
        int years = 0, months = 0;
        String error1 = "", error2 = "", error3 = "";
        InterstRate interestOb;

        if (!loanAmountTextField.getText().isEmpty()) {
            try {
                loanAmount = getValue(loanAmountTextField);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error1 = "Invalid loanAmount, Enter numirec values.";
                loanAmountTextField.setText("");
            }
        } else {
            error1 = "Enter the loanAmount";
        }

        if (!interestRateTextField.getText().isEmpty()) {
            try {
                interest = getValue(interestRateTextField);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error2 = "Invalid interest, Enter numirec values.";
                interestRateTextField.setText("");
                interestRateTextField.setText("");
            }
        } else {
            error2 = "Enter the interest";
        }

        if (!yearsTextField.getText().isEmpty() || !monthsTextField.getText().isEmpty()) {
            try {
                years = (int) getValue(yearsTextField);
                months = (int) getValue(monthsTextField);
                months += years * 12;
                // System.out.println("T");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error3 = "Invalid time, Enter numirec values.";
                yearsTextField.setText("");
                monthsTextField.setText("");
                // System.out.println(error3);
            }
        } else {
            error3 = "Enter the time";
            // System.out.println(error3);
        }

        if (error1.isBlank() && error2.isBlank() && error3.isBlank()) {
            interestOb = new InterstRate(loanAmount, interest, months, yearlyCheckbox.isSelected());
            monthlyResLabel.setText(String.format("%.2f$", interestOb.getPayMonthly()));
            totalResLabel.setText(String.format("%.2f$", interestOb.getPayTotalAmount()));
            errorloanAmountLabel.setText("");
            errorInterestLabel.setText("");
            errorDurationLabel.setText("");
        } else {
            errorloanAmountLabel.setText(error1);
            errorInterestLabel.setText(error2);
            errorDurationLabel.setText(error3);
        }

    }// end compute func

    private float getValue(JTextField jtf) {
        String text = jtf.getText().replace("$", "");
        text = text.replace("%", "");
        text = text.replace("k", "000");
        text = text.replace("K", "000");

        if (text.isEmpty())
            return 0;
        return Float.parseFloat(text);
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
    private float payMonthly, payTotalAmount;
    private Object interest;

    public InterstRate(float loanAmount, float interestRate, float months, boolean yearly) {
        if (yearly)
            CalculateSimpleInterest(loanAmount, interestRate, months);
        else
            calculateInterestRateForTotal(loanAmount, interestRate, months);
    }

    private void calculateInterestRateForTotal(float loanAmount, float interestRate, float months) {
        payTotalAmount = loanAmount * (100 + interestRate) / 100;
        if (months != 0)
            payMonthly = payTotalAmount / months;
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

    public float getPayTotalAmount() {
        return payTotalAmount;
    }

    private void CalculateSimpleInterest(float loanAmount, float interestRate, float months) {
        /*
         * Simple interest is a quick method of calculating the interest charge on a
         * loan.
         * Simple interest is determined by multiplying the daily interest rate by the
         * principal by the number of days that elapse between payments.
         * 
         * Simple Interest = (P × R × T)/100
         * 
         * where P = Principal Amount, R = Rate per Annum, T = Time (years)
         * 
         * A = P+Prt = P+PRt/100= P(1 + rt)
         * 
         * R = r * 100
         * 
         * Where
         * 
         * A = total accrued amount (principal + interest)
         * 
         * P = principal amount
         * 
         * r = rate of interest per year in decimal; r = R/100% percent
         * 
         * t = period of years
         */
        float p = loanAmount, r = interestRate / 100, y = months / 12;
        interest = p * r * y;
        payTotalAmount = p + p * r * y;
        payMonthly = payTotalAmount / months;
        // Rs. 10, 000 at the rate of 5% for 5
    }

    private void CalculateCompoundInterest(float loanAmount, float interestRate, float months) {
        float principal = loanAmount, rate = interestRate, years = months / 12;
        int number = 2;// number: Number of Time interest Compounded
        payTotalAmount = (float) (principal * (Math.pow((1 + rate / 100), (years * number))) - principal);
    }

}// end loan class
