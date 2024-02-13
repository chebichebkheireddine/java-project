package soket.UDP;

import java.io.*;
import java.net.*;

public class UDPServer {

    public static void main(String[] args) {
        
        // Define the server port number
        int serverPort = 12345;
        
        try {
            // Create a datagram socket for receiving UDP packets
            DatagramSocket socket = new DatagramSocket(serverPort);
            System.out.println("Server started on port " + serverPort);
            
            while (true) {
                // Receive a message from a client
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: " + receivedMessage);
                
                // Send a response back to the client
                String responseMessage = "Hello, client!";
                byte[] responseBytes = responseMessage.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
            
        } catch (SocketException e) {
            System.err.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

}

