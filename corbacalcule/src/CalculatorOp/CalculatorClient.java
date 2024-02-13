package CalculatorOp;
import org.omg.CosNaming.*;


import Calculator.CalculatorInterfaceHelper;

import org.omg.CORBA.*;

public class CalculatorClient {

  public static void main(String[] args) {
    try {
      // Initialize the ORB
      ORB orb = ORB.init(args, null);

      // Get the root naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Resolve the object reference in naming
      String name = "Calculator";
      CalculatorImpl calculator = (CalculatorImpl) CalculatorInterfaceHelper.narrow(ncRef.resolve_str(name));

      // Call the add operation
      float num1 = 5.0f;
      float num2 = 10.0f;
      float result = calculator.add(num1, num2);
      System.out.println(num1 + " + " + num2 + " = " + result);

      // Call the sup operation
      result = calculator.sup(num1, num2);
      System.out.println(num1 + " - " + num2 + " = " + result);

      // Call the div operation
      result = calculator.div(num1, num2);
      System.out.println(num1 + " / " + num2 + " = " + result);

      // Call the mul operation
      result = calculator.mul(num1, num2);
      System.out.println(num1 + " * " + num2 + " = " + result);

    } catch(Exception e) {
      System.out.println("CalculatorClient exception: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
