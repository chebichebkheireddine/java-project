package db;
import java.awt.Color;
import java.awt.Dimension;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DatabaseClient {
    public static void main(String[] args) {
        try {
            DatabaseService service = (DatabaseService) Naming.lookup("rmi://localhost:1094/DatabaseServicel");
            JFrame frame = new JFrame("Database Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(new Color(215, 215, 215)); // set background color
            JPanel panel = new JPanel();
            panel.setBackground(new Color(215, 215, 215)); // set panel background color
            frame.add(panel);
            JTextField nameField = new JTextField("Name");
            JTextField ageField = new JTextField("Age");
            JButton addButton = new JButton("Add Person");
            nameField.setPreferredSize(new Dimension(100, 30));
            ageField.setPreferredSize(new Dimension(100, 30));
            panel.add(nameField);
            panel.add(ageField);
            panel.add(addButton);
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age"}, 0);
            JTable table = new JTable(tableModel);
            table.setBackground(Color.white); // set table background color
            panel.add(new JScrollPane(table));

            // Define the action to take when the "Add Person" button is clicked
            addButton.addActionListener(e -> {
                Person person = new Person(0,nameField.getText(), Integer.parseInt(ageField.getText())); // ID will be assigned by server
                try {
                    service.addPerson(person);
                    tableModel.addRow(new Object[]{person.getId(), person.getName(), person.getAge()});
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error adding person to database.");
                }
            });

            // Display all people in the table
            List<Person> people = service.getAllPeople();
            for (Person person : people) {
                tableModel.addRow(new Object[]{person.getId(), person.getName(), person.getAge()});
            }

            // Show the window
            frame.pack();
            frame.setLocationRelativeTo(null); // center the window
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to database service.");
        }
    }
}

