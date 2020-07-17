package com.java.controller;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class SQLController {
    public Connection sqlController() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com/test_schema","admin", "mjaso3000");
        return conn;
    }
}
