package com.example.websocketsbyeder;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class MyWSClient {

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("text");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        byte[] data = message.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

        MyPropClient.getInstance().setProperties(inputStream);

        return null;
    }
    
}
