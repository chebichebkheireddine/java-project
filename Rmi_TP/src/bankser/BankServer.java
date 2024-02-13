package bankser;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class BankServer extends UnicastRemoteObject implements BankServerInterface {

    private static Map<String, Double> accounts;
    private JTextArea logTextArea;

    public BankServer(JTextArea logTextArea) throws RemoteException {
        accounts = new HashMap<>();
        accounts.put("001", 1000.0);
        accounts.put("002", 2000.0);
        accounts.put("003", 3000.0);
        this.logTextArea = logTextArea;
    }

    public synchronized void deposit(String accountNumber, double amount) throws RemoteException {
        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber);
            balance += amount;
            accounts.put(accountNumber, balance);
            logTextArea.append("Deposit of " + amount + " to account " + accountNumber + "\n");
        } else {
            throw new RemoteException("Account not found.");
        }
    }

    public synchronized boolean withdraw(String accountNumber, double amount) throws RemoteException {
        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber);
            if (balance >= amount) {
                balance -= amount;
                accounts.put(accountNumber, balance);
                logTextArea.append("Withdrawal of " + amount + " from account " + accountNumber + "\n");
                return true;
            } else {
                return false;
            }
        } else {
            throw new RemoteException("Account not found.");
        }
    }

    public static void main(String[] args) {
        try {
            // Create GUI
            JFrame frame = new JFrame("Bank Server");
            JTextArea logTextArea = new JTextArea(10, 30);
            logTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(logTextArea);
            frame.getContentPane().add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Create Dialog
            JDialog accountDialog = new JDialog(frame, "Add Account", true);
            accountDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JPanel accountPanel = new JPanel();
            accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
            JTextField accountNumberField = new JTextField(10);
            JTextField accountBalanceField = new JTextField(10);
            accountPanel.add(new JLabel("Account Number:"));
            accountPanel.add(accountNumberField);
            accountPanel.add(new JLabel("Account Balance:"));
            accountPanel.add(accountBalanceField);
            JButton addButton = new JButton("Add Account");
            addButton.addActionListener(e -> {
                String accountNumber = accountNumberField.getText();
                Double accountBalance = Double.valueOf(accountBalanceField.getText());
                accounts.put(accountNumber, accountBalance);
                logTextArea.append("Account " + accountNumber + " added with balance " + accountBalance + "\n");
                accountNumberField.setText("");
                accountBalanceField.setText("");
                accountDialog.dispose();
            });
            accountPanel.add(addButton);
            accountDialog.add(accountPanel);
            accountDialog.pack();

            // Add Account button
            JButton addAccountButton = new JButton("Add Account");
            addAccountButton.addActionListener(e -> {
                accountDialog.setVisible(true);
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addAccountButton);
            frame.getContentPane().add(buttonPanel, "South");

            // Start server
            LocateRegistry.createRegistry(1096);
            BankServer server = new BankServer(logTextArea);
            Naming.rebind("rmi://localhost:1096/BankServer", server);
            logTextArea.append("Server started.\n");
        } catch (Exception e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
