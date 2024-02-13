package Rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;



public class CalculatorClinte {

	public static void main(String[] args) {
			try {
			//RMI for do it 
			Calculator c=(Calculator) Naming.lookup("rmi://localhost/Calcul");
			//Scanner To do 
			Scanner scan=new Scanner(System.in);
			System.out.println("Selection les opiration eg : 2 + 3");
	
			while (true) {
				String request = scan.nextLine();
				if (request == null) {
					break; // Exit the loop if the client closes the connection
				}

				String[] tokens = request.split(" ");
				int operand1 = Integer.parseInt(tokens[0]);
				int operand2 = Integer.parseInt(tokens[2]);
				String operator = tokens[1];
				String ob="";

				double result = 0;
				if (operator.equals("+")) {
					result = c.add(operand1,operand2);
					ob ="ADD ";
				} else if (operator.equals("-")) {
					result = c.sub(operand1, operand2);
					ob ="SUB ";
				} else if (operator.equals("*")) {
					result = c.mul(operand1, operand2);
					ob ="MUL ";
				} else if (operator.equals("/")) {
					if (operand2 == 0) {
						System.out.println("Cannot divide by zero");
						continue;
					}
					result = (double) c.div(operand1, operand2);
					ob ="DIV ";
				} else {
					System.out.println("Invalid operator");
					continue;
				}

				System.out.println(ob +" :: "+ result);
				System.out.println(" Enter New Operation : ");
			}

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
