package com.example.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
// import javax.websocket.CloseReason.CloseCodes;

import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myproperties")
public class MyWSServer {
    private Logger logger = Logger.getLogger(this.getClass().getName());    

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Server started. Session id: " + session.getId() + ".");
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        logger.info("Message received on server.");
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            MyProperties.getInstance().getProperties().store(byteOutput, "");            
        } catch (IOException e) {
            System.out.println("Error to convert properties to a byte array.");
        }

        return byteOutput.toString();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info( String.format("The server was stopped.\nClosure reason: %s. Session ID: %s.", closeReason.getReasonPhrase(), session.getId()));
    }

}
