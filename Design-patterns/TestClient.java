public class TestClient {
    public static void main(String[] args) {        
        MySocket client = MyFactory.getInstance().getObject(MyFactory.FactoryKeys.UDP_CLIENT);
        client.createClientSocket(12345);
        client.sendRequest();
        System.out.println(client.getProperties().toString());
    }
}
