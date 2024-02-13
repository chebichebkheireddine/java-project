package simple2;


//public class Maquette {
//    Place[] places;
//    Transition[] transitions;
//
//    public Maquette(Place[] places, Transition[] transitions) {
//        this.places = places;
//        this.transitions = transitions;
//    }
//
//    public void step(){
//        for(int i= 0; i<transitions.length;i++){
//           if(transitions[i].placesReady());
//
//        }
//    }
//
//
//
//}
public class Maquette {
    Place[] places;
    Transition[] transitions;

    public Maquette(Place[] places, Transition[] transitions) {
        this.places = places;
        this.transitions = transitions;
    }

    public void step() {
        boolean transitionFired = false;

        for (int i = 0; i < transitions.length; i++) {
            if (transitions[i].placesReady()) {
                // Print the details of the transition firing
                System.out.println("Transition " + transitions[i].id + " fired:");
                System.out.println("    Input places:");
                for (int j = 0; j < transitions[i].id; j++) {
                    int placeId = transitions[i].id;
                    int tokens = places[placeId - 1].jetons;
                    System.out.println("        Place " + placeId + ": " + tokens + " token(s)");
                }
                System.out.println("    Output places:");
                for (int j = 0; j < transitions[i].id; j++) {
                    int placeId = transitions[i].id;
                    int tokens = places[placeId - 1].jetons;
                    System.out.println("        Place " + placeId + ": " + tokens + " token(s)");
                }

                transitions[i].activate();
                transitionFired = true;
                break;
            }
        }

        if (!transitionFired) {
            System.out.println("No transition can fire.");
        }
    }

}

