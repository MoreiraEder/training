package com.example.websocketsbyeder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropClient {
    private static MyPropClient sInstance = null;
    private Properties mProperties = null;

    private MyPropClient() {}

    public static MyPropClient getInstance() {
        if (sInstance == null) {
            sInstance = new MyPropClient();
        }
        return sInstance;
    }

    public Properties getProperties() {
        return mProperties;
    }

    public void setProperties(InputStream inStream) {
        if (mProperties == null) {
            mProperties = new Properties();
        }
        try {
            mProperties.load(inStream);            
        } catch (IOException e) {
            System.out.println("Error to set MyPropClient!");
            e.printStackTrace();
        }
    }

}
