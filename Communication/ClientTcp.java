import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Properties;

public class ClientTcp implements IClient {    
    @Override
    public Properties getProperties() {
        Properties result = null;
        Socket socket = null;        
        try {
            socket = new Socket("localhost", 12345);
            ObjectInputStream stream = new ObjectInputStream(socket.getInputStream());            
            result = (Properties)stream.readObject();            
            
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro no cliente! " + e.getMessage() + e.getClass() + e.toString());
            e.printStackTrace();
        }
        finally {            
            try {
                if (socket != null) socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }            
        }
        return result;        
    }

}
