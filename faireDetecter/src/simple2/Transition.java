package simple2;

public class Transition {
    String name;
    int id;
   public int[] connectedBy;
   public int[] connectedTo;

    public Transition(int id, String name, int[] connectedBy, int[] connectedTo){
        this.name = name;
        this.id = id;
        this.connectedBy = connectedBy;
        this.connectedTo = connectedTo;
    }

    public boolean placesReady(){
        boolean ready = true;
        for(int i = 0; i<connectedBy.length; i++){
        }
        return ready;
    }
    public void activate(){
        System.out.println("-----------------Transition "+id+"-------------------");
        System.out.println(this.name);
        System.out.println("connected from");
        for(int i=0;i<connectedBy.length;i++) System.out.println(connectedBy[i]);
        System.out.println("connected to");
        for(int i=0;i<connectedTo.length;i++) System.out.println(connectedTo[i]);
        System.out.println("------------------------------------------------");
    }
}

