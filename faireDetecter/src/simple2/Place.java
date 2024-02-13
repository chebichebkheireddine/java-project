package simple2;


public class Place {
    String name;
    int id;
    int type;
    int jetons;
    int[] connectedTo;
    int[] connectedBy;
    boolean ready = false;
    public Place(int id, String name, int jetons, int[] connectedBy, int[] connectedTo,int type){
        this.name = name;
        this.type = type;
        this.id = id;
        this.jetons = jetons;
        this.connectedBy = connectedBy;
        this.connectedTo = connectedTo;
        if(jetons>0)setReady(true);
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void activate(int type){
        if((jetons==1 ) && this.type==type) System.out.println(this.name + "  -----  "+this.jetons);
        if(this.name.equals("Active")&& jetons>0) System.out.println(this.name+ "  -----  "+this.jetons);
    }
}