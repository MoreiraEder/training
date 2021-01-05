public class ServerMain {
    public static void main(String[] args) {
        new ServerTcp().start();
        new ServerUdp().start();
        new ServerRmiMain().start();
    }
}
