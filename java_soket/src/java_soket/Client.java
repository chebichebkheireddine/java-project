package java_soket;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
    	BufferedReader ain ;
    	Socket SocketClient;
    	
    	
        try {
            // Create a socket ob
        	SocketClient = new Socket("localhost",10000);
        	ain=new BufferedReader(
        	new InputStreamReader(
        	SocketClient.getInputStream()));
        	String a=null;
        	while (a==null) {
				a= ain.readLine();
				System.out.println(a);
				
			}
        	}
        	catch (IOException e) {
            System.out.println("imposible de se connecte a looot");
            System.exit(1);
            
        }
        System.out.println("fin");
    }
}
