package simpl;

import java.util.Map;

public class Maquette {
    Map<Integer, Place> places;
    Map<Integer, Transition> transitions;

    public Maquette(Map<Integer, Place> places2, Map<Integer, Transition> transitions2) {
        this.places = places2;
        this.transitions = transitions2;
    }

    public void step(){
        for(int i= 0; i<transitions.size();i++){
           if(((Transition) transitions).placesReady());

        }
    }
    public Map<Integer, Transition> getTransitions() {
        return transitions;
    }

	public Object removeJeton(int placeIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object addJeton(int placeIndex) {
		// TODO Auto-generated method stub
		return null;
	}



}