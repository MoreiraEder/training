import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class MyRmi implements MySocket {

    Registry registry = null;
    MyRmiInterface sharedObject = null;
    MyRmiInterface remoteObject = null;

    @Override
    public void createServerSocket(int portNubmer) {
        try {
            registry = LocateRegistry.getRegistry();
            sharedObject = new MyRmiServer();
        } catch (RemoteException e) {
            System.out.println("Failed to create shared object!");
        }
    }

    @Override
    public void listen() {
        try {
            registry.rebind("properties", sharedObject);
        } catch (RemoteException e) {
            System.out.println("Failed to register shared object!");
        }
    }

    @Override
    public void sendResponse() {}

    @Override
    public void closeServerSocket() {}

    @Override
    public void createClientSocket(int portNumber) {
        try {
            registry = LocateRegistry.getRegistry();
            remoteObject = (MyRmiInterface) registry.lookup("properties");
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Faled to get remote object!");
        }
    }

    @Override
    public void sendRequest() {}

    @Override
    public Properties getProperties() {
        Properties result = null;
        try {
            result = remoteObject.getProperties();
        }        
        catch (RemoteException e) {
            System.out.println("Failed to get properties!");
        }
        return result;
    }
    
    @Override
    public void closeClientSocket() {}
}