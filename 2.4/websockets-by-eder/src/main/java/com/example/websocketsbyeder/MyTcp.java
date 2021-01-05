package com.example.websocketsbyeder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class MyTcp implements MySocket {

    ServerSocket server = null;
    Socket connection = null;
    ObjectOutputStream stream = null;
    Properties properties = null;
    
    @Override
    public void createServerSocket(int portNubmer) {
        // if (connection != null) throw new MySocketException(ErrorMessages.MYSOCKET_CALL_SERVER_METHOD);
        try {
            server = new ServerSocket(portNubmer);
        } catch (IOException e) {            
            e.printStackTrace();
        }        
    }

    @Override
    public void listen() {
        try {
            connection = server.accept();
            stream = new ObjectOutputStream(connection.getOutputStream());
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendResponse() {
        try {
            stream.writeObject(MyPropSource.getInstance().getProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeServerSocket() {
        try {
            stream.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createClientSocket(int portNumber) {
        try {
            connection = new Socket("localhost", portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void sendRequest() {
        ObjectInputStream stream;
        try {
            stream = new ObjectInputStream(connection.getInputStream());
            properties = (Properties) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void closeClientSocket() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}