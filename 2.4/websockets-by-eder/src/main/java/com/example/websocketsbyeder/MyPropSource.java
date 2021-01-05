package com.example.websocketsbyeder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropSource {

    private static MyPropSource sInstance = null;
    private Properties properties = null;

    private MyPropSource(){
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

    public static synchronized MyPropSource getInstance() {
        if (sInstance == null) {
            sInstance = new MyPropSource();
        }
        return sInstance;
    }

    public Properties getProperties() {
        return properties;
    }   
    
}