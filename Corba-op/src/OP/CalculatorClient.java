package OP;
import Calculator.CalculatorInterface;
import Calculator.CalculatorInterfaceHelper;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorClient {
    private CalculatorInterface calculator;

    private JFrame frame;
    private JTextField num1TextField;
    private JTextField num2TextField;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JLabel resultLabel;

    public CalculatorClient(CalculatorInterface calculator) {
        this.calculator = calculator;
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        num1TextField = new JTextField(10);
        num2TextField = new JTextField(10);

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");

        resultLabel = new JLabel();

        // Layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Number 1:"));
        panel.add(num1TextField);
        panel.add(new JLabel("Number 2:"));
        panel.add(num2TextField);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(resultLabel, BorderLayout.PAGE_END);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("add");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("subtract");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("multiply");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("divide");
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void performOperation(String operation) {
        try {
            double num1 = Double.parseDouble(num1TextField.getText());
            double num2 = Double.parseDouble(num2TextField.getText());
            double result;

            switch (operation) {
                case "add":
                    result = calculator.add(num1, num2);
                    break;
                case "subtract":
                    result = calculator.subtract(num1, num2);
                    break;
                case "multiply":
                    result = calculator.multiply(num1, num2);
                    break;
                case "divide":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    result = 0.0;
            }

            JOptionPane.showMessageDialog(frame, "Result: " + result);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   

    public static void main(String[] args) {
        try {
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the calculator object from the naming service
            String name = "Calculator";
            CalculatorInterface calculator = CalculatorInterfaceHelper.narrow(ncRef.resolve_str(name));

            SwingUtilities.invokeLater(() -> {
                new CalculatorClient(calculator);
            });
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

