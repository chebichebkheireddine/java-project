package simpl;

public class Place {
    private String name;
    private int id;
    private int jetons;
    private int[] in;
    private int[] out;
    private int capacity;
    private boolean ready;

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getJetons() {
        return jetons;
    }

    public void setJetons(int jetons) {
        this.jetons = jetons;
    }

    public int[] getIn() {
        return in;
    }

    public void setIn(int[] in) {
        this.in = in;
    }

    public int[] getOut() {
        return out;
    }

    public void setOut(int[] out) {
        this.out = out;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void activate(int type) {
        if ((jetons == 1) && (type == getId())) {
            System.out.println(getName() + " ----- " + getJetons());
        }
        if ((getName().equals("Alarme") || getName().equals("Senser")) && (jetons > 0)) {
            System.out.println(getName() + " ----- " + getJetons());
        }
    }
}
