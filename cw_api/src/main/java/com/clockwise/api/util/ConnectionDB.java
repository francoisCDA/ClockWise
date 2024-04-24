package com.clockwise.api.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection getConnection(){

        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            String database = System.getenv("SQL_DATABASE");
            String host = System.getenv("SQL_URL");
            String port = System.getenv("SQL_PORT");
            String user = System.getenv("SQL_USERNAME");
            String password = System.getenv("SQL_PASSWORD");


            String url = "jdbc:mysql://"+ host + ":" + port + "/";

            Connection con = DriverManager.getConnection(url + database,user,password);
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}
