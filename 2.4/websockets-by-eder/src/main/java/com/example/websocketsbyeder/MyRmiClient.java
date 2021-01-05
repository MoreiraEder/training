package com.example.websocketsbyeder;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class MyRmiClient extends UnicastRemoteObject implements MyRmiInterface {
    
    private static final long serialVersionUID = 1L;

    public MyRmiClient() throws RemoteException {
        super();
    }

    @Override
    public Properties getProperties() throws RemoteException {
        Properties result = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            MyRmiInterface remoteObject = (MyRmiInterface) registry.lookup("properties");
            result = remoteObject.getProperties();            
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Failed to get remote object from server!");
        }
        return result;
    }    
}
