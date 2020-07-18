package com.java.goods;

import com.java.controller.SQLController;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class NewCategory {
    SQLController sqlController;

    public void createNewCategory(String categoryName) throws SQLException {
        PreparedStatement post = sqlController.sqlController().prepareStatement("CREATE TABLE "+categoryName+" (id INT, goods_name, price, quantity, tags);");
        post.executeUpdate();
    }
}
