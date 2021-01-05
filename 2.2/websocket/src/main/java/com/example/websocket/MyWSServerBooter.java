package com.example.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

public class MyWSServerBooter implements MySocket {
    Server server;    

    @Override
    public void createServerSocket(int portNubmer) {
        server = new Server("localhost", portNubmer, "mywebsocket", MyWSServer.class);
        System.out.println("Sever instantiated.");
    }

    @Override
    public void listen() {
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Press any key to stop the server.");
            reader.readLine();
        } catch (DeploymentException | IOException e) {
            server.stop();
            System.out.println("Failed to start server!");
            e.printStackTrace();
        }
    }

    @Override
    public void sendResponse() {}

    @Override
    public void closeServerSocket() {
        server.stop();
        System.out.println("Server stopped.");
    }

    //--------------------------------------------------------------------------    

    @Override
    public void createClientSocket(int portNumber) {}

    @Override
    public void sendRequest() {}

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void closeClientSocket() {}
    
}
