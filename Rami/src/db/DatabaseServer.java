package db;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServer extends UnicastRemoteObject implements DatabaseService{
	private static final long serialVersionUID = 1L;
	private Connection connection;

    public DatabaseServer() throws RemoteException {
        super();
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            throw new RemoteException("Failed to load JDBC driver", e);
        }

        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RemoteException("Failed to connect to database", e);
        }
    }

    public List<Person> getAllPeople() throws RemoteException {
        List<Person> people = new ArrayList<Person>();
        try {
            String query = "SELECT id, name, age FROM people";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                people.add(new Person(id, name, age));
            }
        } catch (Exception e) {
            throw new RemoteException("Failed to retrieve people from database", e);
        }
        return people;
    }

    public void addPerson(Person person) throws RemoteException {
        try {
            String query = "INSERT INTO people (name, age) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RemoteException("Failed to add person to database", e);
        }
    }

    public static void main(String[] args) {
        try {
            DatabaseServer server = new DatabaseServer();
            Naming.rebind("rmi://localhost:1000/DatabaseService", server);
            System.out.println("Server is running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}