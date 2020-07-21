package com.java.cart;

import com.java.controller.SQLController;
import com.java.controller.WebController;
import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ManageCart {

    @Autowired
    CartController cartController;
    @Autowired
    SQLController sqlController;
    @Autowired
    WebController webController;

    public ArrayList addToCart(int id, HttpServletRequest request) throws SQLException {
        //Statement statement = sqlController.sqlController().createStatement();
        //ResultSet selectedItem = statement.executeQuery("SELECT * FROM "+categoryName+" WHERE id = "+id+";");
        //selectedItem.next();

        String cookieVal = " ";

        //String itemId = String.valueOf(selectedItem.getInt("id"));
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("myCookie")) {
                    cookieVal = cookie.getValue();
                }
            }
        }

        System.out.println("cookie val " + cookieVal);
            //JSONObject selectedGood = new JSONObject();
            //selectedGood.put("id", itemId);
            int selectedGoodStr = id;
        System.out.println("id " + id);
        ArrayList itemsInCart = cartController.carts.get("1B1D81NUMI0H5L0S8P");
        System.out.println("ids before add " + itemsInCart);
        itemsInCart.add(id);
        System.out.println("ids after add " + itemsInCart);
        cartController.carts.put(cookieVal, itemsInCart);
        return itemsInCart;
    }
}
