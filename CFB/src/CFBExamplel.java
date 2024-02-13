import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CFBExamplel {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Get the key from the user
        System.out.print("Enter the key in Base64 encoding: ");
        String keyString = scanner.nextLine();
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        Key key = new SecretKeySpec(keyBytes, "AES");

        // Get the plaintext from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

        // Get the user's option (encrypt or decrypt)
        System.out.print("Enter 'E' to encrypt or 'D' to decrypt: ");
        String option = scanner.nextLine();

        // Initialize the cipher
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        if (option.equalsIgnoreCase("E")) {
            // Encrypt the plaintext
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] ciphertext = cipher.doFinal(plaintextBytes);

            // Print the results
            System.out.println("Encrypted ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
        } else if (option.equalsIgnoreCase("D")) {
            // Get the encoded ciphertext from the user
            System.out.print("Enter the encrypted ciphertext in Base64 encoding: ");
            String encodedCiphertext = scanner.nextLine();
            byte[] ciphertext = Base64.getDecoder().decode(encodedCiphertext);

            // Decrypt the ciphertext
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = cipher.doFinal(ciphertext);
            String decryptedText = new String(decrypted, StandardCharsets.UTF_8);

            // Print the results
            System.out.println("Decrypted plaintext: " + decryptedText);
        } else {
            System.out.println("Invalid option. Please enter 'E' to encrypt or 'D' to decrypt.");
        }
    }
}
