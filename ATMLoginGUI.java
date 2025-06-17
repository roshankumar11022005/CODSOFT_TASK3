import javax.swing.*;
import java.util.HashMap;

public class ATMLoginGUI extends JFrame {
    private JTextField accField;
    private JPasswordField pinField;
    private static HashMap<String, BankAccount> accounts = new HashMap<>();

    public ATMLoginGUI() {
        setTitle("ATM Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        accounts.put("12345", new BankAccount("12345", "1111", 1000));
        accounts.put("67890", new BankAccount("67890", "2222", 2000));

        JLabel accLabel = new JLabel("Account Number:");
        JLabel pinLabel = new JLabel("PIN:");
        accField = new JTextField(10);
        pinField = new JPasswordField(10);
        JButton loginBtn = new JButton("Login");

        JPanel panel = new JPanel();
        panel.add(accLabel);
        panel.add(accField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(loginBtn);
        add(panel);

        loginBtn.addActionListener(e -> handleLogin());

        setVisible(true);
    }

    private void handleLogin() {
        String accNo = accField.getText();
        String pin = new String(pinField.getPassword());

        if (accounts.containsKey(accNo)) {
            BankAccount account = accounts.get(accNo);
            if (account.authenticate(pin)) {
                dispose();
                new ATM_GUI(account);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMLoginGUI());
    }
}