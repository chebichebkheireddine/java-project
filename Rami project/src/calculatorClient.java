import java.lang.reflect.MalformedParameterizedTypeException;
import java.rmi.Naming;





public class calculatorClient {
	


	public static void main(String[] args) {
		try {
			Calculator c = (Calculator) Naming.lookup("rmi://localhost:1099/Calcul");

	        System.out.println("5 + 3 = " + c.add(5, 3));
	        System.out.println("5 - 3 = " + c.sub(5, 3));
	        System.out.println("5 * 3 = " + c.mult(5, 3));
	        System.out.println("5 / 3 = " + c.div(5, 3));

			
		} catch (Exception murle) {
			System.out.println("java.net.MalformedURIException"+ murle);
		}
		// TODO Auto-generated method stub
		
	}

}
