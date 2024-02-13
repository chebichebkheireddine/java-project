package CalculatorOp;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Calculator.*;

public class CalculatorServer {

    public static class CalculatorImpl extends CalculatorInterfacePOA {

        private ORB orb;

        public void setORB(ORB orb_val) {
            orb = orb_val;
        }

        @Override
        public float add(float num1, float num2) {
            return num1 + num2;
        }

        @Override
        public float sup(float num1, float num2) {
            return num1 - num2;
        }

        @Override
        public float div(float num1, float num2) {
            if (num2 == 0) {
                System.out.println("Division by zero is not allowed.");
                return 0;
            }
            return num1 / num2;
        }

        @Override
        public float mul(float num1, float num2) {
            return num1 * num2;
        }
    }

    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Create the implementation object
            CalculatorImpl calculator = new CalculatorImpl();

            // Activate the object
            calculator.setORB(orb);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the object reference in naming
            String name = "Calculator";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, CalculatorInterfaceHelper.narrow((Object) calculator));

            System.out.println("CalculatorServer ready and waiting...");

            // Wait for invocations
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
