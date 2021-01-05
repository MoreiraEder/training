import java.util.Properties;

public class ClientMain {
    public static void main(String[] args) {
        IClient clienteTcp = new ClientFactory(IClient.ConnectionType.TCP);
        Properties propertiesTcp = clienteTcp.getProperties();
        System.out.println("Resposta via TCP: " + propertiesTcp.toString());

        IClient clienteUdp = new ClientFactory(IClient.ConnectionType.UDP);
        Properties propertiesUdp = clienteUdp.getProperties();
        System.out.println("Resposta via UDP: " + propertiesUdp.toString());

        IClient clienteRmi = new ClientFactory(IClient.ConnectionType.RMI);
        Properties propertiesRmi = clienteRmi.getProperties();
        System.out.println("Resposta via RMI: " + propertiesRmi.toString());
    }
}
