import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRmiMain extends Thread {
    @Override
    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry();           
            ServerRmiObj rmiObj = new ServerRmiObj();
            registry.rebind("properties", rmiObj);

            System.out.println("Server RMI pronto.");
            
        } catch (RemoteException e) {
            System.out.println("Não foi possível iniciair o server RMI.");
            e.printStackTrace();
        }

    }
}
