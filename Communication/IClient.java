import java.util.Properties;

public interface IClient {
    public enum ConnectionType {TCP, UDP, RMI}

    public Properties getProperties();
}
