package pation;

public class Patient implements Runnable {
    private int patientNumber;
    private Clinic clinic;
    private long arrivalTime;

    public Patient(int patientNumber, Clinic clinic) {
        this.patientNumber = patientNumber;
        this.clinic = clinic;
        this.arrivalTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        System.out.println("New patient has arrived. Patient number: " + patientNumber);
        try {
            int examineTime = clinic.examinePatient();
            long completionTime = System.currentTimeMillis();
            System.out.println("Patient " + patientNumber + " arrived at " + arrivalTime +
                    " and has finished examination at " + completionTime + ". Total time: " + (completionTime - arrivalTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

