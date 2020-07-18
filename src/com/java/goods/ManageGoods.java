package com.java.goods;

import com.java.controller.SQLController;
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
import java.util.List;

@Component
public class ManageGoods {

    @Autowired
    SQLController sqlController;

    public String addGoods(String categoryName, int id, String goodsName, double price, int quantity, String[] tags) throws SQLException{

        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT id FROM "+categoryName+";");

        List<Integer> existingIds = new ArrayList();
        boolean status = true;

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
            String existingPrice = String.valueOf(existingItem.getDouble("price"));
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

    public String deleteGoods(int id, String categoryName) throws SQLException {

        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM "+categoryName+"");
        List<Integer> existingGoods = new ArrayList();

        while(myRs.next()){
            existingGoods.add(myRs.getInt("id"));
        }

        if(existingGoods.contains(id)){
            PreparedStatement post = sqlController.sqlController().prepareStatement("DELETE FROM "+categoryName+" WHERE id = "+id+";");
            post.executeUpdate();
            return "good's been deleted successfully";
        }else{
            return "good that you are trying to delete does not exist";
        }
    }
}
