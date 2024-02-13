package Exo3_tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 40000);

            // Create input and output streams for the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read the paragraph from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the paragraph: ");
            String PARAGRAPH = reader.readLine();

            // Start the word search loop
            while (true) {
                // Read the word to search from the user
                System.out.print("Enter a word to search in the paragraph (or type 'exit' to quit): ");
                String word = reader.readLine();

                if (word.equalsIgnoreCase("exit")) {
                    break; // Exit the loop if the user types 'exit'
                }

                // Send the word to the server
                out.println(word);

                // Read the result from the server and print it
                String result = in.readLine();
                System.out.println(result);
            }

            // Close the socket and streams
            in.close();
            out.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
