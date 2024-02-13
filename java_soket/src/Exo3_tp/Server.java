package Exo3_tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(40000);
            System.out.println("Server is listening on port 20000...");

            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the paragraph: ");

            while (true) {
            	String PARAGRAPH= scan.nextLine();
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected...");

                // Create input and output streams for the client socket
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the client's request
                String word = in.readLine();

                while (word != null) {
                    // Search for the word in the paragraph
                    boolean found = PARAGRAPH.toLowerCase().contains(word.toLowerCase());

                    // Send the result to the client
                    out.println(found ? "Word <<" + word + ">> found in the paragraph." : "Word <<" + word + ">> not found in the paragraph.");

                    // Read the next client request
                    word = in.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
