package java_soket;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
    	ServerSocket server;
    	Socket socketClient;
    	PrintWriter aout;
    try {
		server = new ServerSocket(10000);
		System.out.println("Wating for the client ........ ");
	socketClient = server.accept();
	aout = new PrintWriter(
		new OutputStreamWriter(socketClient.getOutputStream()));
	aout.println("hell");
	aout.close();
	
			
    } catch (IOException e) {
		// TODO: handle exception
    	System.out.println("errr!!!!");
	}	
    }
}
