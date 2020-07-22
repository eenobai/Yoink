package com.java.cart;

import com.java.controller.CookieController;
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
    @Autowired
    CookieController cookieController;

    public HashMap<String, ArrayList<Integer>> addToCart(int id, int quantity, HttpServletRequest request) throws SQLException {
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
        System.out.println("id " + id);
        System.out.println("quantity " + quantity);
        HashMap<String, ArrayList<Integer>> itemsInCart = new HashMap();
        itemsInCart = cartController.carts.get(cookieVal);

        System.out.println("cookie cart from carts map " + cartController.carts.get(cookieVal));


        ArrayList <Integer> itemIds = itemsInCart.get("item_ids");
        ArrayList <Integer> itemQuantity = itemsInCart.get("item_quantity");

        itemIds.add(id);
        System.out.println("item Quantity before adding " + itemQuantity);
        itemQuantity.add(quantity);

        itemsInCart.put("item_ids", itemIds);
        itemsInCart.put("item_quantity", itemQuantity);

        cartController.carts.put(cookieVal, itemsInCart);

        System.out.println("items in cart " + itemsInCart.toString());

        cartController.carts.put(cookieVal, itemsInCart);

        return itemsInCart;
    }
}
