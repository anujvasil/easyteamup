package com.example.easyteamup;

import android.os.StrictMode;


import java.sql.*;


public class DBConnectionHelper {
    Connection connection;
    String user, pass, url, schema;

    public DBConnectionHelper() {
        createConnection();
    }



    private void createConnection() {
        user = "cs310admin";
        pass = "v4zIgbKpUx6z";
        url = "jdbc:mysql://cs310.cj9r3muy3ryl.us-east-1.rds.amazonaws.com:3306/team_up";
        schema = "team_up";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,user,pass);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection = conn;
    }

    public ResultSet runProcedure(String query) {
        createConnection();
        ResultSet resultSet = null;
        try {
            Statement statement;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public CallableStatement prepCall(String query) {
        createConnection();
        try {
            return connection.prepareCall(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void close() {

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() {
        close();
    }

}
