import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

public class CFBExampleGUI extends JFrame implements ActionListener {

	private JTextField keyField;
	private JTextField plaintextField;
	private JRadioButton encryptButton;
	private JRadioButton decryptButton;
	private JTextArea resultArea;
	


	public CFBExampleGUI() {
		super("AES CFB Example");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Key input
		JPanel keyPanel = new JPanel();
		JLabel keyLabel = new JLabel("Key (Base64):");
		keyField = new JTextField(20);
		keyPanel.add(keyLabel);
		keyPanel.add(keyField);

		// Plaintext input
		JPanel plaintextPanel = new JPanel();
		JLabel plaintextLabel = new JLabel("Plaintext:");
		plaintextField = new JTextField(20);
		plaintextPanel.add(plaintextLabel);
		plaintextPanel.add(plaintextField);

		// Radio buttons for encryption/decryption
		JPanel modePanel = new JPanel();
		encryptButton = new JRadioButton("Encrypt");
		decryptButton = new JRadioButton("Decrypt");
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(encryptButton);
		modeGroup.add(decryptButton);
		modePanel.add(encryptButton);
		modePanel.add(decryptButton);

		// Result display area
		JPanel resultPanel = new JPanel();
		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		resultPanel.add(scrollPane);
		
		

		// Encrypt/decrypt button
		JPanel buttonPanel = new JPanel();
		JButton runButton = new JButton("Run");
		runButton.addActionListener(this);
		buttonPanel.add(runButton);

		// Add all components to main panel
		mainPanel.add(keyPanel);
		mainPanel.add(plaintextPanel);
		mainPanel.add(modePanel);
		mainPanel.add(resultPanel);
		mainPanel.add(buttonPanel);

		// Set main panel as content pane
		this.setContentPane(mainPanel);

		// Set window properties and show
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    try {
	        byte[] keyBytes = Base64.getDecoder().decode(keyField.getText());
	        Key key = new SecretKeySpec(keyBytes, "AES");
	        byte[] iv = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);

	        
	        String plaintext = plaintextField.getText();
	        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

	        Cipher cipher;

	        if (encryptButton.isSelected()) {
	            cipher = Cipher.getInstance("AES/CFB/NoPadding");
	            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
	            byte[] ciphertext = cipher.doFinal(plaintextBytes);
	            resultArea.setText("Encrypted ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
	        } else if (decryptButton.isSelected()) {
	            cipher = Cipher.getInstance("AES/CFB/NoPadding");
	            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
	            byte[] ciphertext = Base64.getDecoder().decode(plaintextField.getText());
	            byte[] decrypted = cipher.doFinal(ciphertext);
	            String decryptedText = new String(decrypted, StandardCharsets.UTF_8);
	            resultArea.setText("Decrypted text: " + decryptedText);
	        } else {
	            // Handle case where neither encrypt nor decrypt button is selected
	            resultArea.setText("Please select encryption or decryption mode.");
	        }
	    } catch (Exception ex) {
	        resultArea.setText("Error: " + ex.getMessage());
	    }
	}
	private Key createKey() throws Exception {
		String keyString = keyField.getText();
		byte[] keyBytes = Base64.getDecoder().decode(keyString);
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(keyBytes));
		return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
	}

	public static void main(String[] args) {
		new CFBExampleGUI();
	}
}