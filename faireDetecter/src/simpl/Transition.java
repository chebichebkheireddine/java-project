package simpl;

import java.util.Arrays;
import java.util.Map;

import javax.swing.Icon;

public class Transition {
    private String name;
    private int[] in;
    private int[] out;
    
    public Transition(String name) {
        this.name = name;
    }
    
    public void setIn(int[] in) {
        this.in = in;
    }
    
    public void setOut(int[] out) {
        this.out = out;
    }
    
    public boolean isReady(Map<Integer, Place> places) {
        boolean ready = true;
        for (int placeIndex : in) {
            if (!places.equals(placeIndex)) {
                ready = false;
                break;
            }
        }
        return ready;
    }
    
    public void activate(Map<Integer, Place> places) {
        System.out.println("-----------------Transition " + name + "-------------------");
        System.out.println("connected from");
        Arrays.stream(in).forEach(System.out::println);
        System.out.println("connected to");
        Arrays.stream(out).forEach(System.out::println);
        System.out.println("------------------------------------------------");
        
        Arrays.stream(in).forEach(placeIndex -> places.remove(placeIndex));
       
    }

	public boolean placesReady() {
		// TODO Auto-generated method stub
		  boolean ready = true;
	        for(int i = 0; i<in.length; i++){
	        }
	        return ready;
	}

	public Icon getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
