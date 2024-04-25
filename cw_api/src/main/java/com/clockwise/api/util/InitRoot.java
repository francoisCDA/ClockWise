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

            String mysql_user = System.getenv("MYSQL_USER");
            String mysql_password = System.getenv("MYSQL_PASSWORD");

            String sql_root_password = System.getenv("SQL_ROOT_PASSWORD");

            String database = System.getenv("SQL_DATABASE");
            String host = System.getenv("SQL_URL");

            String user = System.getenv("SQL_USERNAME");
            String password = System.getenv("SQL_PASSWORD");

            String apiRootMail = System.getenv("API_ROOT_MAIL");
            String apiPassword = System.getenv("API_ROOT_PASSWORD");

            String port = System.getenv("SQL_PORT");

//            System.out.println("mysql_user = " + mysql_user);
//            System.out.println("mysql_password = " + mysql_password);
//            System.out.println("sql_root_password = " + sql_root_password);
//            System.out.println("database = " + database);
//            System.out.println("host = " + host);
//            System.out.println("user = " + user);
//            System.out.println("password = " + password);
//            System.out.println("port = " + port);
//            System.out.println("apiRootMail = " + apiRootMail);
//            System.out.println("apiPassword = " + apiPassword);


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
