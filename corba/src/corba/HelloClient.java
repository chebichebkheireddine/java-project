package corba;
import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
public class HelloClient {
public static void main(String[]args)throws Exception {
	ORB orb = (ORB) ORB.init(args, null);

    org.omg.CORBA.Object nsRef = orb.resolve_initial_references("NameService");

    NamingContextExt nce = NamingContextExtHelper.narrow(nsRef);

    String serviceName = "HelloService";
    Hello hRef = HelloApp.HelloHelper.narrow(nce.resolve_str(serviceName));
    System.out.println("Reponse du serveur: " + hRef.sayHello());
}
}
