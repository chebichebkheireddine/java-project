package corba;

import HelloApp.*;
import corba.HelloImpl;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class HelloServeur {
    public static void main(String[] args) throws Exception {
        //initialisation de l'orb
        ORB orb = ORB.init(args, null);

        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        HelloImpl helloImpl = new HelloImpl();

        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
        Hello href = HelloHelper.narrow(ref);

        org.omg.CORBA.Object refNServ = orb.resolve_initial_references("NameService");
        NamingContextExt nce = NamingContextExtHelper.narrow(refNServ);

        String serviceName = "HelloServices";
        NameComponent nc[] = nce.to_name(serviceName);
        nce.rebind(nc, href);

        System.out.println("On traite les requetes des clients ...");
        orb.run();
    }
}
