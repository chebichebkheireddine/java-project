package bankser;

import java.rmi.*;

public interface BankServerInterface extends Remote {
    void deposit(String accountNumber, double amount) throws RemoteException;
    boolean withdraw(String accountNumber, double amount) throws RemoteException;
}
