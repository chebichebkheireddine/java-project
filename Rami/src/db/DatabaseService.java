package db;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseService extends Remote {
    List<Person> getAllPeople() throws RemoteException;
    void addPerson(Person person) throws RemoteException;
}
