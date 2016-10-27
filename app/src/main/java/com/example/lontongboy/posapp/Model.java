package com.example.lontongboy.posapp;

/**
 * Created by mokichi on 10/27/16.
 */

public class Model {

    public static String select(String table){
        String query = "SELECT * FROM" + table;
        return query;
    }

    public static String selectWhere(String table, String where){
        String query = "SELECT * FROM" + table + "WHERE" + where;
        return query;
    }

    public static String select_order(String table, String order){
        String query = "SELECT * FROM" + table + order;
        return query;
    }

    public static void insert(String table, String[] data){
        String fieldB = "(";
        String valueInput = "VALUE(";

        String query = "INSERT INTO" + table;
    }

}
