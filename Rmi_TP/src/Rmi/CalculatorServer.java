package Rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
public CalculatorServer() {
	try {
		LocateRegistry.createRegistry(1099);
		Calculator ca=new CalculatorImpl();
		System.out.println(ca.toString());
			Naming.rebind("rmi://localhost/Calcul", ca);}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
new CalculatorServer();
	}

}
