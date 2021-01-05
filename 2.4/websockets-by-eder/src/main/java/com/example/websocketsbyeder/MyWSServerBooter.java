package com.example.websocketsbyeder;

import java.util.Properties;
import javax.websocket.DeploymentException;
import org.glassfish.tyrus.server.Server;

public class MyWSServerBooter implements MySocket {
    Server mServer;    

    Thread mServerThread = new Thread(
        new Runnable(){
            public void run() {
                try {
                    mServer.start();
                    synchronized(this) {
                        wait();
                    }
                } catch (DeploymentException | InterruptedException e) {
                    mServer.stop();
                    System.out.println("Failed to start server!");
                    e.printStackTrace();
                }
            }
        }
    );

    @Override
    public void createServerSocket(int portNubmer) {
        mServer = new Server("localhost", portNubmer, "/mywebsocket", MyWSServer.class);
    }

    @Override
    public void listen() {
        mServerThread.start();
    }

    @Override
    public void sendResponse() {}

    @Override
    public void closeServerSocket() {        
        synchronized(mServerThread) {
            mServerThread.notify();
        }        
        mServer.stop();        
        mServerThread.stop();
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
