package com.java.cart;

import com.java.controller.SQLController;
import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

@Component
public class ManageCart {

    @Autowired
    CartController cartController;
    @Autowired
    SQLController sqlController;

    public String addToCart(String categoryName, int id) throws SQLException {
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet selectedItem = statement.executeQuery("SELECT * FROM "+categoryName+" WHERE id = "+id+";");
        selectedItem.next();

        String itemId = String.valueOf(selectedItem.getInt("id"));
        String goodsName = selectedItem.getString("goods_name");
        String quantity = String.valueOf(selectedItem.getInt("quantity"));
        String price = String.valueOf(selectedItem.getDouble("price"));
        String tags = selectedItem.getString("tags");
        try {
            JSONObject selectedGood = new JSONObject();
            selectedGood.put("id", itemId);
            selectedGood.put("goods_name", goodsName);
            selectedGood.put("quantity", quantity);
            selectedGood.put("price", price);
            selectedGood.put("tags", tags);
            cartController.cart.put(cartController.serialNum, selectedGood);
            cartController.serialNum++;
            return selectedGood.toString();
        }catch(JSONException e){
        }
        return "addToCart() error";
    }
}
