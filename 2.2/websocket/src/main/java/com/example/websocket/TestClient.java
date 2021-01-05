package com.example.websocket;

public class TestClient {
    public static void main(String[] args) {                
        // MySocket client = MyFactory.getInstance().getObject(MyFactory.FactoryKeys.RMI_CLIENT);
        MySocket client = new MyWSServerBooter();
        client.createClientSocket(8025);        
        client.sendRequest();
        System.out.println(client.getProperties().toString());
    }
}
