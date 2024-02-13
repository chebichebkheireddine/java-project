import java.util.concurrent.Semaphore;

public class Main {
	static final int NUM_Doctor = 1;
    static final int NUM_EXAM_ROOMS = 1;
    static final int NUM_PATIENTS = 10;

    static Semaphore waitingRoomSem = new Semaphore(10);
    static Semaphore examRoomSem = new Semaphore(NUM_EXAM_ROOMS);
    static Semaphore doctorSem = new Semaphore(NUM_Doctor);

    static class Patient extends Thread {
        int id;

        public Patient(int id) {
            this.id = id;
        }

        public void run() {
            try {
                System.out.println("Patient " + id + " arriving at doctor's office.");
                
                examRoomSem.acquire();
                System.out.println("Patient " + id + " waiting for exam room.");
 
                System.out.println("Patient " + id + " entering exam room.");
                Thread.sleep(1000);
                examRoomSem.release();
                System.out.println("Patient " + id + " finished with exam.");
                doctorSem.acquire();
                System.out.println("Patient " + id + " seeing doctor don with doctor.");
                Thread.sleep(1000);
                doctorSem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM_PATIENTS; i++) {
            new Patient(i).start();
        }
    }
}
