package modal;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Sall_attont sall_attont = new Sall_attont();
	    Thread thread = new Thread(sall_attont);
	    thread.start();
	}

}
