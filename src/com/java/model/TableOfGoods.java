package com.java.model;

import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

@Component
public class TableOfGoods {

    @Autowired
    SQLController sqlController;

    public void showTable() throws SQLException {
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM list_of_goods;");
        String leftAlignFormat = "| %-6s | %-16s | %-5s | %-6d | %-31s |%n";
        System.out.println("################################################################################");
        System.out.println("#                       Existing items in database                             #");
        System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");
        System.out.format("+   ID   +     Item Name    + Price +Quantity+             Tags                +%n");
        System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");
        int i = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        while(myRs.next()){
            i++;
            int id = myRs.getInt("id");
            String name = myRs.getString("goods_name");
            String price = df.format(myRs.getFloat("price"));
            int quantity = myRs.getInt("quantity");
            String tags = myRs.getString("tags");

            System.out.format(leftAlignFormat, id, name, price, quantity, tags);
        }
        System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");
    }

}
