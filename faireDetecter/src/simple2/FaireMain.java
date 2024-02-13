package simple2;

import java.util.concurrent.Semaphore;

public class FaireMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Place [] places = new Place[14];
        Transition [] transitions = new Transition[10];
        Semaphore s = new Semaphore(1);
        //Places To btn
        places[0] = new Place(1,
                "Huma",
                1,
                new int[]{},//add from number
                new int[]{2}
                ,1);

        places[1] = new Place(2,
                "bouton n'est pas enfonce",
                1,
                new int[]{1,5},
                new int[]{2}
        	,2);
        places[2] = new Place(3,
                "Alarme Desactive",
                1,
                new int[]{5},
                new int[]{3}
        	,3);
        places[3] = new Place(4,
                "Alarme",
                0,
                new int[]{3},//add from number
                new int[]{5}
                ,4);

        places[4] = new Place(5,
                "Sprinklers Active",
                0,
                new int[]{3},
                new int[]{5}
        	,5);
        places[5] = new Place(6,
                "bouton enfonce",
                0,
                new int[]{2},
                new int[]{3}
        	,6);
        //End of plass btn
        //Start of Places of Sprinklers 
        places[6] = new Place(7,
                "Sprinklers Dectev",
                0,
                new int[]{1,5,6},//add from number
                new int[]{1,7}
                ,7);

        places[7] = new Place(8,
                "Sprinklers Active",
                1,
                new int[]{7},
                new int[]{6}
        	,5);
        //End of Sprinklers
        //Start alarm 
        places[8] = new Place(9,
                "Alarme Desactive",
                0,
                new int[]{5,1},
                new int[]{8}//done
        	,3);
        places[9] = new Place(10,
                "Alarme dclenche",
                0,
                new int[]{8},
                new int[]{7}//done 
        	,8);
        //End of Alarme
        //Start of Senser
        places[10] = new Place(11,
                "sensible Desactivation",
                1,
                new int[]{10},
                new int[]{9}
        	,9);
        places[11] = new Place(12,
                "Fumee",
                1,
                new int[]{},
                new int[]{9}
        	,10);
        places[12] = new Place(13,
                "faites sensible",
                0,
                new int[]{9},
                new int[]{10}
        	,11);
        places[13] = new Place(14,
                "Pas de fumee",
                0,
                new int[]{7},
                new int[]{10}
        	,12);
        
        transitions[0] = new Transition(1,
                "Test",
                new int[]{2,9,7},
                new int[]{2,9,7});

        //transitions for butn
        transitions[1] = new Transition(2,
                "Le bouton a t appuye",
                new int[]{1,2},
                new int[]{6});

        transitions[2] = new Transition(3,
                "Changement",
                new int[]{6,10},
                new int[]{4,5});
        transitions[4] = new Transition(5,
                "Reinitialiser",
                new int[]{4,5},
                new int[]{2,7,9});
        //End of transitions btn
        
        //The transitions for Sprinklers
        transitions[5] = new Transition(6,
                "Desactivation des sprinklers",
                new int[]{8},
                new int[]{7,14});
        transitions[6] = new Transition(7,
                "Activation des sprinklers",
                new int[]{7,10},
                new int[]{8});
        //Start for Alarm System
        
        transitions[7] = new Transition(8,
                "Changement de Alarme",
                new int[]{9},
                new int[]{10});
        //End of System of Alarme
       //Start of Senser
        
        transitions[8] = new Transition(9,
                "Changement de sensible",
                new int[]{11,12},
                new int[]{13});
        transitions[9] = new Transition(10,
                "desactive sensible",	
                new int[]{13,14},
                new int[]{11});
        
        while (places[1].jetons > 0) {

            if (places[2].jetons > 0) {
                
                places[2].jetons++;
                System.out.println();
                places[1].jetons--;
                
                Maquette maquette = new Maquette(places, transitions);
                
                
                // Execute one step of the Petri net
                maquette.step();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        for (Place place : places) {
            System.out.println(place.name + ": " + place.jetons);
            
            
            // Execute one step of the Petri net
        }
	}

}
