package com.java.controller;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SQLController {
    public Connection sqlController() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com/test_schema","admin", "mjaso3000");
        return conn;
    }

    public int getQuantity(int id, String category) throws SQLException {
        System.out.println("getQuantity got called");
        Statement statement = sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM "+category+" WHERE id= '"+id+"';");
        myRs.next();
        int quantity = myRs.getInt("quantity");
        return quantity;
    }

    public void setQuantity(String category, int quantity, int id) throws SQLException{
        System.out.println("setQuantity got called");
        PreparedStatement post = sqlController().prepareStatement("UPDATE "+category+" SET quantity = '"+quantity+"' where id = '"+id+"'");
        post.executeUpdate();
    }


}
