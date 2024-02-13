package Gun;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientGUI extends JFrame implements ActionListener {
    private JTextField wordField;
    private JTextArea resultArea;
    private JButton searchButton;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientGUI() {
        super("Word Search Client");

        // Initialize the GUI components
        wordField = new JTextField(20);
        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        // Add the components to the GUI
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        inputPanel.add(new JLabel("Enter a word to search:"), BorderLayout.WEST);
        inputPanel.add(wordField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(resultPanel, BorderLayout.CENTER);

        // Create the socket and streams
        try {
            socket = new Socket("localhost", 50000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        // Set the size and visibility of the GUI
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String word = wordField.getText();

            if (!word.isEmpty()) {
                // Send the word to the server
                out.println(word);

                try {
                    // Read the result from the server and display it
                    String result = in.readLine();
                    resultArea.append(result + "\n");
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI();
            }
        });
    }
}
