package com.saucedemo.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {

        try {
            String path="configuration.properties";
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String get (String keyName){
        return properties.getProperty(keyName);
    }
}
