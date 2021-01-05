import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class ServerRmiObj extends UnicastRemoteObject  implements IRmi {
    private static final long serialVersionUID = 1L;

    protected ServerRmiObj() throws RemoteException {
        super();
    }

    public Properties getProperties() throws RemoteException {
        return MyProperties.getProperties();
    }
    
}
