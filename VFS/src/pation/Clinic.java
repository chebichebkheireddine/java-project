package pation;

import java.util.LinkedList;
import java.util.Random;

public class Clinic {

    private ClinicData data = new ClinicData();

	public Clinic(int salesCount, int doctorCount) {
        this.data.setSalesCount(salesCount);
        this.data.setDoctorCount(doctorCount);
        this.data.setSalleQueue(new LinkedList<>());
        this.data.setDoctorList(new LinkedList<>());

        // Initialize sales and doctors
        for (int i = 1; i <= this.data.getSalesCount(); i++) {
            this.data.getSalleQueue().add(new Sall(i));
        }

        for (int i = 1; i <= this.data.getDoctorCount(); i++) {
            this.data.getDoctorList().add(new Doctor(i, 3 ,null));
        }
    }
	

    public synchronized Sall getAvailableSall() {
        if (!this.data.getSalleQueue().isEmpty()) {
            return this.data.getSalleQueue().remove();
        } else {
            return null;
        }
    }

    public synchronized Doctor getAvailableDoctor() {
        if (!this.data.getDoctorList().isEmpty()) {
            return this.data.getDoctorList().remove();
        } else {
            return null;
        }
    }

    public synchronized void releaseSales(Sall sall) {
        this.data.getSalleQueue().add(sall);
        notifyAll(); // Notify any waiting patients that a sales is available
    }

    public synchronized void releaseDoctor(Doctor doctor) {
        this.data.getDoctorList().add(doctor);
        notifyAll(); // Notify any waiting patients that a doctor is available
    }

    public synchronized void waitOnSales() throws InterruptedException {
        wait(); // Wait for notification that a sales is available
    }

    public synchronized void waitOnDoctor() throws InterruptedException {
        wait(); // Wait for notification that a doctor is available
    }
    public synchronized int examinePatient() throws InterruptedException {
        // Get an available sales and doctor
        Sall sall = getAvailableSall();
        Doctor doctor = getAvailableDoctor();

        // If either is not available, wait
        while (sall == null || doctor == null) {
            if (sall == null) {
                waitOnSales();
                sall = getAvailableSall();
            }

            if (doctor == null) {
                waitOnDoctor();
                doctor = getAvailableDoctor();
            }
        }

        // Simulate examination time
        Random rand = new Random();
        int examineTime = rand.nextInt(5000) + 1000; // Random number between 1000 and 6000
        Thread.sleep(examineTime);

        // Release the sales and doctor
        releaseSales(sall);
        releaseDoctor(doctor);

        return examineTime;
    }


}