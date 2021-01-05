import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTcp extends Thread {
    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {   
            serverSocket = new ServerSocket(12345);         
            System.out.println("Servidor TCP iniciado.");
            while (true) {
                socket = serverSocket.accept();                
                ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
                stream.flush();
                stream.writeObject(MyProperties.getProperties());
                stream.close();
                socket.close();                
            }            
        }
        catch (IOException e) {
            System.out.println("Erro no server TCP!");
        }
        finally {
            try {
                if(serverSocket != null) {
                    serverSocket.close();
                }                
            } catch (IOException e) {
                System.out.println("Erro ao encerrar server TCP!");
            }
        }
    }   
}