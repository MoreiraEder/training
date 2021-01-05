import java.util.Properties;

public class ClientFactory implements IClient {
    private IClient.ConnectionType connectionType;

    public ClientFactory(IClient.ConnectionType ct) {
        this.connectionType = ct;
    }

    @Override
    public Properties getProperties() {
        Properties result = null;

        if (connectionType == IClient.ConnectionType.TCP) {
            result = new ClientTcp().getProperties();
        }
        else if (connectionType == IClient.ConnectionType.UDP) {
            result = new ClientUdp().getProperties();
        }        
        else if (connectionType == IClient.ConnectionType.RMI) {
            result = new ClientRmiMain().getProperties();
        }
        return result;
    }

    
}
