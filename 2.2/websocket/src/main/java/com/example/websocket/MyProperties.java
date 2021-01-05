package com.example.websocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

    private static MyProperties sInstance = null;
    private Properties properties = null;

    private MyProperties(){
        try {
            File file = new File("\\\\C:\\Users\\emoreira\\Documents\\Training\\Threads\\file.properties");
            InputStream stream = new FileInputStream(file);
            properties = new Properties();
            properties.load(stream);            
        } catch (IOException e) {
            System.out.println("Fail to read properties file.");
            e.printStackTrace();
        }
    }

    public static synchronized MyProperties getInstance() {
        if (sInstance == null) {
            sInstance = new MyProperties();
        }
        return sInstance;
    }

    public Properties getProperties() {
        return properties;
    }   
    
}