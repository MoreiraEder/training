import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class MyRmiServer extends UnicastRemoteObject implements MyRmiInterface {

    private static final long serialVersionUID = 1L;

    protected MyRmiServer() throws RemoteException {
        super();
    }
    
    @Override
    public Properties getProperties() throws RemoteException {                
        return MyProperties.getInstance().getProperties();
    }
    
}
