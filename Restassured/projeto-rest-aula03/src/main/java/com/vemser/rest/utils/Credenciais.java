package com.vemser.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Credenciais {

    public static String getEmail() {
        return getProp().getProperty("email");
    }

    public static String getPassword() {
        return getProp().getProperty("password");
    }

    private static Properties getProp() {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/dados-teste.properties");
            props.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
