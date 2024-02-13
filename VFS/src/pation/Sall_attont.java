package pation;

import java.util.Random;

public class Sall_attont implements Runnable {

    private int pation;
    private Clinic clinic;
    public Sall_attont() {
     
    }

    public Sall_attont(int pation, Clinic clinic) {
        this.pation = pation;
        this.clinic = clinic;
    }

    public int arive_lasall() {
        Random rand = new Random();
        while (true) {
            int randNum = rand.nextInt(30); // Generate a random number between 1 and 1000
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
        System.out.println("New patient has arrived. Patient number: " + this.pation);

        // Get the available sales from the clinic
        Sall sall = this.clinic.getAvailableSall();

        // If there is no available sales, wait for notification from the clinic
        while (sall == null) {
            try {
                System.out.println("No available sales. Patient " + this.pation + " is waiting.");
                this.clinic.waitOnSales();
                sall = this.clinic.getAvailableSall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Assign the patient to the available sales and print a message
        sall.assignPatient(this.pation);
        System.out.println("Patient " + this.pation + " has been assigned to Sales " + sall.getNumber());

        // Get the available doctor from the clinic
        Doctor doctor = this.clinic.getAvailableDoctor();

        // If there is no available doctor, wait for notification from the clinic
        while (doctor == null) {
            try {
                System.out.println("No available doctor. Patient " + this.pation + " is waiting.");
                this.clinic.waitOnDoctor();
                doctor = this.clinic.getAvailableDoctor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Assign the patient to the available doctor and print a message
        doctor.assignPatient(this.pation);
        System.out.println("Patient " + this.pation + " is being examined by Doctor " + doctor.getNumber());

        // Notify the clinic that the sales and doctor are available again
        this.clinic.releaseSales(sall);
        this.clinic.releaseDoctor(doctor);
    }
}


