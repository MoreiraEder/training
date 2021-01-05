import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUdp extends Thread {
    public void run() {
        DatagramSocket serverSocket = null;
        byte[] data = new byte[200];
        
        try {
            serverSocket = new DatagramSocket(12345);
            System.out.println("Server UDP iniciado.");
            while (true) {
                DatagramPacket packetReceived = new DatagramPacket(data, data.length);
                serverSocket.receive(packetReceived);
                
                ByteArrayOutputStream stream = new ByteArrayOutputStream();                
                MyProperties.getProperties().store(stream, "File got via UDP.");
                data = stream.toByteArray();               
                
                DatagramPacket packetSend = new DatagramPacket(data, data.length, packetReceived.getAddress(), packetReceived.getPort());
                serverSocket.send(packetSend);
            }   
        }
        catch (IOException e) {
            System.out.println("Erro no server!");    
            e.printStackTrace();        
        }
        finally {                        
            if(serverSocket != null) {
                serverSocket.close();
            }
            
        }
    }

}