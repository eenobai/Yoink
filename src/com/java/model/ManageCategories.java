package com.java.model;

import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManageCategories {

    @Autowired
    SQLController sqlController;

    public String createNewCategory(String categoryName) throws SQLException {
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'test_schema'");

        List<String> existingCategories = new ArrayList();

        while(myRs.next()){
            existingCategories.add(myRs.getString("TABLE_NAME"));
       }

        if(existingCategories.contains(categoryName)){
            return "category exists already";
        }else{
            PreparedStatement post = sqlController.sqlController().prepareStatement("CREATE TABLE "+categoryName+" (id INT, goods_name TEXT, price FLOAT, quantity INT, tags TEXT);");
            post.executeUpdate();
            return "New category " + categoryName + " has been created";
        }
    }

    public String deleteCategory(String categoryName) throws SQLException{
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'test_schema'");

        List<String> existingCategories = new ArrayList();

        while(myRs.next()){
            existingCategories.add(myRs.getString("TABLE_NAME"));
        }

        if(existingCategories.contains(categoryName)){
            PreparedStatement post = sqlController.sqlController().prepareStatement("DROP TABLE "+categoryName+";");
            post.executeUpdate();
            return "Category has been deleted";
        }else{
            return "Table " + categoryName + " doesn't exist";
        }
    }
}
