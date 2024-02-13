package pation;

public class Sall {

    private int number;
    private int patient;
    private boolean available;

    public Sall(int number) {
        this.number = number;
        this.available = true;
    }

    public int getNumber() {
        return this.number;
    }

    public synchronized boolean isAvailable() {
        return this.available;
    }

    public synchronized void assignPatient(int patient) {
        this.patient = patient;
        this.available = false;
    }

    public synchronized void release() {
        this.available = true;
    }

    public int getPatient() {
        return this.patient;
    }

}
