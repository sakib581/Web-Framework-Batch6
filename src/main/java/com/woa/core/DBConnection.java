package com.woa.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static DBConnection dbInstance;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private DBConnection() {

    }

    public static DBConnection getInstance() {
        if (dbInstance == null) {
            dbInstance = new DBConnection();
        }
        return dbInstance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/classicmodels";
            String uName = "root";
            String password = "root1234";
            connection = DriverManager.getConnection(url, uName, password);
        }
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (statement == null) {
            statement = connection.createStatement();
        }
        return statement;
    }

    public List<String> getListOfDataFromDB(String query, String columnName) throws SQLException {
        resultSet = statement.executeQuery(query);
        List<String> dataList = new ArrayList<>();
        while (resultSet.next()) {
            dataList.add(resultSet.getString(columnName));
        }
        return dataList;
    }

    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        dbConnection.getConnection();
        dbConnection.getStatement();
        List<String> data =  dbConnection.getListOfDataFromDB("SELECT * FROM classicmodels.test","user");
        System.out.println(data);
    }

}