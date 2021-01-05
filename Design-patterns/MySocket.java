import java.util.Properties;

public interface MySocket {
    public void createServerSocket(int portNubmer);
    public void listen();
    public void sendResponse();
    public void closeServerSocket();

    public void createClientSocket(int portNumber);
    public void sendRequest();
    public Properties getProperties();
    public void closeClientSocket();
}
