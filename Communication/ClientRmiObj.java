import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class ClientRmiObj extends UnicastRemoteObject implements IRmi {
    private static final long serialVersionUID = 1L;    

    public ClientRmiObj() throws RemoteException {
        super();        
    }

    public Properties getProperties() throws RemoteException {
        Properties properties = null;
        try {
            Registry registry = LocateRegistry.getRegistry();            
            IRmi response = (IRmi) registry.lookup("properties");
            properties = response.getProperties();

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
}
