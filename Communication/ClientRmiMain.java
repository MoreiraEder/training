import java.rmi.RemoteException;
import java.util.Properties;

public class ClientRmiMain implements IClient {
    @Override
    public Properties getProperties() {
        Properties result = null;
        try {            
            ClientRmiObj client = new ClientRmiObj();
            
            result = client.getProperties();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;        
    }    
}
