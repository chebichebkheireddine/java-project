package modal;

import java.util.Random;

public class Sall_attont implements Runnable {
	
	int pation ;
	
	
	public int arive_lasall() {
		Random rand = new Random();
		
	    while (true) {
	        int randNum = rand.nextInt(1000); // Generate a random number between 1 and 1000
	        try {
	            Thread.sleep(randNum); // Sleep the thread for a random amount of time
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    
		return randNum;
		}
	}
	@Override
	public void run() {
		    System.out.println("New patient has arrived. Patient number: " + arive_lasall());
	    	
		
	}
	

}
