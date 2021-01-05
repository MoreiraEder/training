package com.example.websocketsbyeder;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class MyWSClientBooter implements MySocket {

    @Override
    public void createServerSocket(int portNubmer) {}

    @Override
    public void listen() {}

    @Override
    public void sendResponse() {}

    @Override
    public void closeServerSocket() {}

    @Override
    public void createClientSocket(int portNumber) {
        WebSocketContainer container = null;
        container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(MyWSClient.class, URI.create("ws://localhost:" + portNumber + "/mywebsocket/myproperties"));

            int cont = 0;
            while (cont < 10 && MyPropClient.getInstance().getProperties() == null) {
                try {
                    Thread.sleep(1000);
                    cont++;
                } catch (InterruptedException e) {
                    System.out.println("Properties not received.");
                }                
            }
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRequest() {}

    @Override
    public Properties getProperties() {
        return MyPropClient.getInstance().getProperties();
    }

    @Override
    public void closeClientSocket() {}
    
}
