package simpl;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BuildingFireSimulation {
    enum PlaceName { ENTRANCE, HALLWAY, ROOM }
    enum TransitionName { ALARM, EXTINGUISHER }

    public static void main(String[] args) {
        // Create the places
        Map<Integer, Place> places = new HashMap<>();
        places.put(0, new Place(0, PlaceName.ENTRANCE.name()));
        places.put(1, new Place(1, PlaceName.HALLWAY.name()));
        places.put(2, new Place(2, PlaceName.ROOM.name()));
        places.get(0).setJetons(1);
        places.get(0).setOut(new int[]{1});
        places.get(0).setCapacity(1);
        places.get(1).setIn(new int[]{0});
        places.get(1).setOut(new int[]{2});
        places.get(1).setCapacity(1);
        places.get(2).setIn(new int[]{1});
        places.get(2).setCapacity(2);
        
        // Create the transitions
        Map<Integer, Transition> transitions = new HashMap<>();
        transitions.put(0, new Transition(TransitionName.ALARM.name()));
        transitions.put(1, new Transition(TransitionName.EXTINGUISHER.name()));
        transitions.get(0).setIn(new int[]{1});
        transitions.get(0).setOut(new int[]{0, 2});
        transitions.get(1).setIn(new int[]{1});
        transitions.get(1).setOut(new int[]{0, 2});
        
        // Run the simulation
        int time = 0;
        while (places.get(2).getJetons() < 2) {
            System.out.println("Time: " + time + "\n");
            for (Transition transition : transitions.values()) {
                if (transition.isReady(places)) {
                    transition.activate(places);
                }
            }
            for (Place place : places.values()) {
                place.activate(time);
            }
            time++;
        }
    }
}
