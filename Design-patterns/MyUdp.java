import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;

public class MyUdp implements MySocket {
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    byte[] data = new byte[256];
    InetAddress localAddress = null;
    Properties properties = null;
    int portNumber = 0;

    @Override
    public void createServerSocket(int portNubmer) {
        try {
            this.portNumber = portNubmer;
            socket = new DatagramSocket(this.portNumber);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listen() {
        packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendResponse() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {            
            MyProperties.getInstance().getProperties().store(stream, null);
            data = stream.toByteArray();
            packet = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeServerSocket() {
        socket.close();
    }

    @Override
    public void createClientSocket(int portNumber) {
        try {
            this.portNumber = portNumber;
            localAddress = InetAddress.getByName("localhost");
            socket = new DatagramSocket();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void sendRequest() {
        try {
            packet = new DatagramPacket(data, data.length, localAddress, portNumber);
            socket.send(packet);            
            socket.receive(packet);
            ByteArrayInputStream stream = new ByteArrayInputStream(packet.getData());
            properties = new Properties();
            properties.load(stream);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void closeClientSocket() {
        socket.close();
    }
    
}