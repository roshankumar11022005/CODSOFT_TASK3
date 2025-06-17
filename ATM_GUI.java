import javax.swing.*;
import java.awt.*;

public class ATM_GUI extends JFrame {
    private BankAccount account;
    private JTextField amountField;
    private JTextArea displayArea;

    public ATM_GUI(BankAccount acc) {
        this.account = acc;

        setTitle("ATM Machine - Account " + account.getAccountNumber());
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(10);

        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton historyBtn = new JButton("Transaction History");

        displayArea = new JTextArea(7, 30);
        displayArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawBtn);
        panel.add(depositBtn);
        panel.add(checkBalanceBtn);
        panel.add(historyBtn);
        panel.add(new JScrollPane(displayArea));

        add(panel);

        withdrawBtn.addActionListener(e -> handleWithdraw());
        depositBtn.addActionListener(e -> handleDeposit());
        checkBalanceBtn.addActionListener(e -> displayArea.setText("Balance: ₹" + account.getBalance()));
        historyBtn.addActionListener(e -> displayArea.setText("Transaction History:\n" + account.getTransactionHistory()));

        setVisible(true);
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Enter a valid amount!");
            } else if (account.withdraw(amount)) {
                showMessage("Withdrawn ₹" + amount + "\nBalance: ₹" + account.getBalance());
            } else {
                showMessage("Insufficient balance!");
            }
        } catch (NumberFormatException ex) {
            showMessage("Enter a valid number!");
        }
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Enter a valid amount!");
            } else {
                account.deposit(amount);
                showMessage("Deposited ₹" + amount + "\nBalance: ₹" + account.getBalance());
            }
        } catch (NumberFormatException ex) {
            showMessage("Enter a valid number!");
        }
    }

    private void showMessage(String message) {
        displayArea.setText(message);
    }
}