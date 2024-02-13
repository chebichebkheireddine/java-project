package Exo2_tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Server is listening on port 10000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected...");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                while (true) {
                    String request = in.readLine();
                    if (request == null) {
                        break; // Exit the loop if the client closes the connection
                    }

                    String[] tokens = request.split(" ");
                    int operand1 = Integer.parseInt(tokens[0]);
                    int operand2 = Integer.parseInt(tokens[2]);
                    String operator = tokens[1];

                    double result = 0;
                    if (operator.equals("+")) {
                        result = operand1 + operand2;
                    } else if (operator.equals("-")) {
                        result = operand1 - operand2;
                    } else if (operator.equals("*")) {
                        result = operand1 * operand2;
                    } else if (operator.equals("/")) {
                        if (operand2 == 0) {
                            out.println("Cannot divide by zero");
                            continue;
                        }
                        result = (double) operand1 / operand2;
                    } else {
                        out.println("Invalid operator");
                        continue;
                    }

                    out.println(result);
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
