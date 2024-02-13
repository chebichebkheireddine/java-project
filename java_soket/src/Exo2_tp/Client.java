package Exo2_tp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);
             int ha=socket.getPort();
             
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // Read user input
            	System.out.println(ha);
                System.out.print("Enter expression (e.g. 2 + 3): ");
                String input = consoleIn.readLine();

                // Send expression to server
                out.println(input);

                // Read result from server
                String result = in.readLine();
                System.out.println("Result: " + result);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
