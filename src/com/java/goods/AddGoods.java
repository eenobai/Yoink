package com.java.goods;

import com.java.controller.SQLController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class AddGoods {

    @Autowired
    SQLController sqlController;
    @Autowired
    GoodsParameters goodsParameters;

    public String addGoods(String categoryName, int id, String goodsName, double price, int quantity, String[] tags) throws SQLException{

        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT id FROM "+categoryName+";");

        List<Integer> existingIds = new ArrayList();
        boolean status = true;
        int a = 0;
        while(myRs.next()){
            existingIds.add(myRs.getInt("id"));
        }

        for(int i = 0; i < existingIds.size(); i++){
            if(existingIds.get(i) == id){
                status = false;
            }
        }
        if(status == true){
            PreparedStatement post = sqlController.sqlController().prepareStatement("INSERT INTO " + categoryName + "(id, goods_name, price, quantity, tags) VALUES('" + id + "', '" + goodsName + "','" + price + "','" + quantity + "','" + Arrays.toString(tags) + "')");
            post.executeUpdate();
        }else{
            ResultSet existingItem = statement.executeQuery("SELECT * FROM "+categoryName+" WHERE id = "+id+"");
            existingItem.next();

            String existingId = String.valueOf(existingItem.getInt("id"));
            String existingGoodsName = existingItem.getString("goods_name");
            String existingQuantity = String.valueOf(existingItem.getInt("quantity"));
            String existingPrice = String.valueOf(existingItem.getInt("price"));
            String existingTags = existingItem.getString("tags");
            try {
                JSONObject existingGood = new JSONObject();
                existingGood.put("id", existingId);
                existingGood.put("goods_name", existingGoodsName);
                existingGood.put("quantity", existingQuantity);
                existingGood.put("price", existingPrice);
                existingGood.put("tags", existingTags);
                return existingGood.toString();
            }catch(JSONException e){

            }

        }
        return "error";
    }
}
