package com.example.websocketsbyeder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myproperties")
public class MyWSServer {
    private Logger logger = Logger.getLogger(this.getClass().getName());    

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Session opened. ID: " + session.getId() + ".");
    }

    @OnMessage
    public String onMessage(String message, Session session) {        
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            MyPropSource.getInstance().getProperties().store(byteOutput, "");
        } catch (IOException e) {
            logger.severe("Error to convert properties to a byte array.");
        }
        return byteOutput.toString();
    }

}
