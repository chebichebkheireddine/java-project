import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    protected CalculatorImpl () throws RemoteException  {
    	super();
    }

    public long  add(long x, long y) throws RemoteException {
    	return x + y;
    }
    
    public long sub(long x, long y) throws RemoteException {
        return x - y;
    }
	
	

	public long  mult(long x, long y) throws RemoteException{
        return x * y;
    }

	public long div (long x, long y) throws RemoteException{
        return x / y;
    }
}
