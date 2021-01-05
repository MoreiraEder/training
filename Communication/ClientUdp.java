import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

public class ClientUdp implements IClient {    

    public Properties getProperties() {
        Properties result = null;
        DatagramSocket socket = null;
        byte[] data = new byte[200];            

        try {
            InetAddress localAddress = InetAddress.getByName("localhost");
            socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, data.length, localAddress, 12345);
            socket.send(packet);

            packet = new DatagramPacket(data, data.length, localAddress, 12345);
            socket.receive(packet);
            ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());

            result = new Properties();            
            result.load(stream);

            socket.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;        
    }
}
