package pation;
class Doctor implements Runnable {

    private int number;
    private int patient;
    private int examCount;
    private int maxExams;
    private Clinic clinic;

    public Doctor(int number, int maxExams, Clinic clinic) {
        this.number = number;
        this.maxExams = maxExams;
        this.examCount = 0;
        this.clinic = clinic;
    }

    public int getNumber() {
        return this.number;
    }

    public void assignPatient(int patient) {
        this.setPatient(patient);
    }

    @Override
    public void run() {
        while (true) {
            // Check if the doctor has reached the maximum number of exams
            if (this.examCount >= this.maxExams) {
                System.out.println("Doctor " + this.number + " has reached the maximum number of exams.");
                try {
                    this.clinic.waitOnDoctor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Get the available doctor from the clinic
            Doctor doctor = this.clinic.getAvailableDoctor();

            // If there is no available doctor, wait for notification from the clinic
            while (doctor == null) {
                try {
                    System.out.println("Doctor " + this.number + " is waiting.");
                    this.clinic.waitOnDoctor();
                    doctor = this.clinic.getAvailableDoctor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Examine the patient for a random amount of time
            int examineTime = 0;
            try {
                examineTime = this.clinic.examinePatient();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                Thread.sleep(examineTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Increment the exam counter and check if the doctor has reached the maximum number of exams
            this.examCount++;
            if (this.examCount >= this.maxExams) {
                System.out.println("Doctor " + this.number + " has reached the maximum number of exams.");
                this.clinic.releaseDoctor(doctor);
                try {
                    this.clinic.waitOnDoctor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Notify the clinic that the doctor is available again
                this.clinic.releaseDoctor(doctor);
            }
        }
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

}
