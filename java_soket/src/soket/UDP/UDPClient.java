package soket.UDP;

import java.io.*;
import java.net.*;

public class UDPClient {

    public static void main(String[] args) {
        
        // Define the server IP address and port number
        String serverHostname = "localhost";
        int serverPort = 12345;
        
        try {
            // Create a datagram socket for sending and receiving UDP packets
            DatagramSocket socket = new DatagramSocket();
            
            // Send a message to the server
            String message = "Hello, server!";
            byte[] messageBytes = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName(serverHostname);
            DatagramPacket sendPacket = 
            new DatagramPacket(messageBytes, messageBytes.length, serverAddress, serverPort);
            socket.send(sendPacket);
            
            // Receive a message from the server
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from server: " + receivedMessage);
            
            // Close the socket
            socket.close();
            
        } catch (SocketException e) {
            System.err.println("SocketException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

}
