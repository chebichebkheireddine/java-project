package OP;
import Calculator.CalculatorInterface;
import Calculator.CalculatorInterfaceHelper;
import Calculator.CalculatorInterfacePOA;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Create the calculator servant
            CalculatorServant calculatorServant = new CalculatorServant();

            // Get the root POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.activate_object(calculatorServant);

            // Connect the calculator servant to the ORB
            org.omg.CORBA.Object objRef = rootPOA.servant_to_reference(calculatorServant);
            CalculatorInterface calculator = CalculatorInterfaceHelper.narrow(objRef);

            // Get the root naming context
            org.omg.CORBA.Object objRef2 = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef2);

            // Bind the calculator object to the naming service
            String name = "Calculator";
            NameComponent[] nc = ncRef.to_name(name);
            ncRef.rebind(nc, calculator);

            System.out.println("CalculatorServer ready...");

            // Activate the POA manager
            rootPOA.the_POAManager().activate();

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

class CalculatorServant extends CalculatorInterfacePOA {
    @Override
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) {
        if (num2 != 0) {
            return num1 / num2;
        } else {
            throw new ArithmeticException("Cannot divide by zero.");
        }
    }
}
