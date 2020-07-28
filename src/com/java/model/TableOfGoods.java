package com.java.model;

import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class TableOfGoods {

    private List<Integer> ids = new ArrayList();
    private List<String> goodsNames = new ArrayList();
    private List<Float> price = new ArrayList();
    private List<Integer> quantity = new ArrayList();
    private List<String> tags = new ArrayList();
    private int i = 0;

    @Autowired
    SQLController sqlController;

        public void sortingMess(String category, String sortBy) throws SQLException {
            HashSet<String> waysToSort = new HashSet<String>(Arrays.asList("id", "goods_name", "price", "quantity"));
            Statement statement = sqlController.sqlController().createStatement();
            if (!waysToSort.contains(sortBy)) {
              System.out.println("wrong sort value");
            }else {
                ResultSet myRs = statement.executeQuery("SELECT * FROM " + category + " ORDER BY " + sortBy + ";");
                while (myRs.next()) {

                    ids.add(myRs.getInt("id"));
                 if (myRs.getString("goods_name") != null) {
                    goodsNames.add(myRs.getString("goods_name"));
                }else{
                    goodsNames.add("String is a null");
                    }
                price.add(myRs.getFloat("price"));
                quantity.add(myRs.getInt("quantity"));
                if (myRs.getString("tags") != null) {
                    tags.add(myRs.getString("tags"));
                }else{
                    tags.add("String is a null");
                    }
                }
            }
        }

        public void showTable (String category, String sortBy) throws SQLException {
            sortingMess(category, sortBy);

            String leftAlignFormat = "| %-6s | %-16s | %-5s | %-6d | %-31s |%n";
            System.out.println("################################################################################");
            System.out.println("#                       Existing items in database                             #");
            System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");
            System.out.format("+   ID   +     Item Name    + Price +Quantity+             Tags                +%n");
            System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");

            while (i < ids.size()) {
                System.out.format(leftAlignFormat, ids.get(i), goodsNames.get(i), price.get(i), quantity.get(i), tags.get(i));
                i++;
            }
            System.out.format("+--------+------------------+-------+--------+---------------------------------+%n");

            ids.clear();
            goodsNames.clear();
            price.clear();
            quantity.clear();
            tags.clear();
            i = 0;
        }
}
