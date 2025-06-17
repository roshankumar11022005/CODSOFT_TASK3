public class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private StringBuilder transactionHistory = new StringBuilder();

    public BankAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdraw: ₹").append(amount).append("\n");
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposit: ₹").append(amount).append("\n");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}