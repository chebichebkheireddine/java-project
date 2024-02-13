package bankser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankClient extends JFrame implements ActionListener {

    private BankServerInterface bankServer;
    private JTextField accountField;
    private JTextField amountField;
    private JLabel statusLabel;

    public BankClient() {
        super("Banking System");

        // Initialize components
        JLabel accountLabel = new JLabel("Account Number:");
        JLabel amountLabel = new JLabel("Amount:");
        accountField = new JTextField(10);
        amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        statusLabel = new JLabel();

        // Set layout and add components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(accountLabel, constraints);
        constraints.gridx = 1;
        panel.add(accountField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(amountLabel, constraints);
        constraints.gridx = 1;
        panel.add(amountField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(depositButton, constraints);
        constraints.gridx = 1;
        panel.add(withdrawButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(statusLabel, constraints);
        add(panel);

        // Set button listeners
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        // Connect to server
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1096);
            bankServer = (BankServerInterface) registry.lookup("BankServer");
            statusLabel.setText("Connected to server.");
        } catch (Exception e) {
            statusLabel.setText("Error connecting to server: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        String accountNumber = accountField.getText();
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid amount.");
            return;
        }
        try {
            if (e.getActionCommand().equals("Deposit")) {
                bankServer.deposit(accountNumber, amount);
                statusLabel.setText("Deposit successful.");
            } else if (e.getActionCommand().equals("Withdraw")) {
                boolean success = bankServer.withdraw(accountNumber, amount);
                if (success) {
                    statusLabel.setText("Withdrawal successful.");
                } else {
                    statusLabel.setText("Insufficient funds.");
                }
            }
        } catch (RemoteException ex) {
            statusLabel.setText("Error communicating with server: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new BankClient();
    }
}
