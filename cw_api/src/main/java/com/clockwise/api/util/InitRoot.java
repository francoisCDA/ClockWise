package com.clockwise.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class InitRoot {

    URL url;

    HttpURLConnection connection;

    public InitRoot() {

        try {

            String port = System.getenv("SQL_PORT");

            url = new URL("http://localhost:"+port+"/cwise/api/v2/user/init");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    public void init(){
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            connection.disconnect();

            System.out.println("root initialized");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
