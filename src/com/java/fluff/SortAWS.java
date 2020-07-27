package com.java.fluff;


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
public class SortAWS {

    @Autowired
    SQLController sqlController;

    public void sort(String category, String sortBy) throws SQLException {
        if (sortBy.equals("id")) {
            List<Integer> ids = new ArrayList();
            List<String> goodsNames = new ArrayList();
            List<Float> price = new ArrayList();
            List<Integer> quantity = new ArrayList();
            List<String> tags = new ArrayList();

            int i = 0;

            Statement statement = sqlController.sqlController().createStatement();
            ResultSet myRs = statement.executeQuery("SELECT * FROM "+category+" ORDER BY "+sortBy+";");


            while(myRs.next()){
                ids.add(myRs.getInt("id"));
                goodsNames.add(myRs.getString("goods_name"));
                price.add(myRs.getFloat("price"));
                quantity.add(myRs.getInt("quantity"));
                tags.add(myRs.getString("tags"));
            }
            System.out.println(ids.toString());
            System.out.println(goodsNames.toString());
            System.out.println(price.toString());
            System.out.println(quantity.toString());
            System.out.println(tags.toString());


            /*
            while(i<ids.size()){
                PreparedStatement post = sqlController.sqlController().prepareStatement("INSERT INTO "+category+" VALUES ("+ids.get(i)+", "+goodsNames.get(i)+", "+price.get(i)+", "+quantity.get(i)+", "+tags.get(i)+")");
                post.executeUpdate();
                i++;
            }
*/
        }

    }
}
