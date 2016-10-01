package com.example.lontongboy.posapp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by ASUS on 29/09/2016.
 */

public class MySQLAccess {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // TODO: 10/1/16 Ganti DB_URL dengan alamat server sql nya tiap lepi beda
    static final String DB_URL = "jdbc:mysql://localhost/posapp";

    // TODO: 10/1/16 username dan password disesuaikan dengan DBnya
    static final String USER = "root";
    static final String PASS = "admin";

    public Connection conn = null;
    public Statement stmt = null;

    public void initDBConnection(){
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createStatemen(String sql){
        try {
            stmt = conn.createStatement();
            // execute query
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public void readDatabase() throws Exception{
        try {
            // This will load the MySQL driver, each FB has it own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://");
            // Statement allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from xxx");
            writeResultSet(resultSet);

            // remove again the insert comment
            preparedStatement = connect
                    .prepareStatement("delete from xxx where xxx=");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("select * from xxxx");
            writeMetaData(resultSet);
        } catch (Exception e){
            throw e;
        } finally {
            close();
        }
    }

    private void writeMetaData(ResultSet resultSet) throws  SQLException{
        // Now get come metadata from the database
        // Result set get the result of the SQL Query

        System.out.println("The columns in the table are: ");

        System.out.println("Table" + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " + i + "" + resultSet.getMetaData().getTableName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()){
            // it is possible to get the columns via name
            // also possible to get columns via the columns number
            // which starts at 1
            // e.g. resultSet.getString(2)
            String user = resultSet.getString("myuser");
            String website = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date date = resultSet.getDate("datum");
            String comment = resultSet.getString("comments");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("summary: " + summary);
            System.out.println("Date: " + date);
            System.out.println("Comment: " + comment);
        }
    }

    // You need to close the resultSet
    private void close(){
        try {
            if (resultSet != null){
                resultSet.close();
            }

            if (statement != null){
                statement.close();
            }

            if (connect != null){
                connect.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
