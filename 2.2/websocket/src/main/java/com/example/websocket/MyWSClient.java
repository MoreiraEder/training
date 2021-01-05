package com.example.websocket;

import java.util.Properties;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.concurrent.CountDownLatch;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint
public class MyWSClient {    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Properties mProperties = null;
    private static CountDownLatch latch;
        
    @OnOpen
    public void onOpen(Session session) {
        logger.info(String.format("Client started. Session ID: %s", session.getId()));
        try {
            session.getBasicRemote().sendText("get-properties");            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {                
        byte[] data = message.getBytes();
        ByteArrayInputStream byteInput = new ByteArrayInputStream(data);

        mProperties = new Properties();
        try {            
            mProperties.load(byteInput);
            System.out.println("Properties: " + mProperties.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("Properties received.");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("The session was closed. Reason: %s.", closeReason.getReasonPhrase()));
        latch.countDown();
    }

    public static void main(String[] args) {
        latch = new CountDownLatch(1);
        ClientManager client = ClientManager.createClient();
        
        try {
            URI uri = new URI("ws://localhost:8025/websocket/myproperties");
            client.connectToServer(MyWSClient.class, uri);
            latch.await();
        } catch (DeploymentException | URISyntaxException |InterruptedException e) {
            e.printStackTrace();            
        }
    }   

    
}
