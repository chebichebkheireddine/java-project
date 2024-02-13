package pation;


public class Main {
    public static void main(String[] args) {
        // Create a clinic with 3 sales attendants and 2 doctors
        Clinic clinic = new Clinic(2, 3);

        Sall_attont sall=new Sall_attont();
        // Create some sales attendant threads
        int num=sall.arive_lasall();
        System.out.println("this  pation arrive ;; "+num);
        Thread[] salesAttendants = new Thread[num];
        for (int i = 0; i < salesAttendants.length; i++) {
            salesAttendants[i] = new Thread(new Sall_attont(i+1 , clinic));
            salesAttendants[i].start();
        }

        // Create some doctor threads
       Thread[] doctors = new Thread[3];
        for (int i = 0; i < doctors.length; i++) {
            doctors[i] = new Thread(new Doctor(i + 1, 2, clinic));
            doctors[i].start();
        }

         //Simulate some patient arrivals
        for (int i = 1; i <= 5; i++) {
        	Patient patient = new Patient(i, clinic);
        	Thread patientThread = new Thread(patient);
        	patientThread.start();


            // Sleep for a random amount of time between patient arrivals
            try {
                Thread.sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
