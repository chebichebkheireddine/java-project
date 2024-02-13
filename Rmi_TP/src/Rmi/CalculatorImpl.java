package Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject  implements Calculator    {
protected CalculatorImpl() throws RemoteException {super();}

public long add(long a, long b) throws RemoteException {
	// TODO Auto-generated method stub
	return a+b;
}

public long sub(long a, long b) throws RemoteException {
	// TODO Auto-generated method stub
	return a-b;
}

public long mul(long a, long b) throws RemoteException {
	// TODO Auto-generated method stub
	return a*b;
}

@Override
public long div(long a, long b) throws RemoteException {
	// TODO Auto-generated method stub
	return a/b;
}
	
}
