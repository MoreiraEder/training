package com.example.websocketsbyeder;

public class TestServer {
    public static void main(String[] args) {
        MySocket server = MyFactory.getInstance().getObject(MyFactory.FactoryKeys.WEB_SOCKET_SERVER);
        server.createServerSocket(12345);
        server.listen();
        server.sendResponse();        
        // server.closeServerSocket();
    }   
}