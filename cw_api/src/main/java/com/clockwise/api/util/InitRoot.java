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
            url = new URL("http://localhost:8000/cwise/api/v2/user/init");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    public void init(){
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//            }
//            in.close();

            connection.disconnect();

            System.out.println("root initialized");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
