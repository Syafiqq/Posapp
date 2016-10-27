package com.example.lontongboy.posapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ASUS on 02/10/2016.
 */

class DBInter {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // TODO: 10/1/16 Ganti DB_URL dengan alamat server sql nya tiap lepi beda
    private static final String DB_URL = "jdbc:mysql://192.168.0.105/posapp";

    // TODO: 10/1/16 username dan password disesuaikan dengan DBnya
    private static final String USER = "root";
    private static final String PASS = "";

    public Connection conn = null;
    public Statement stmt = null;

    public void initDBConnection(){
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

}
