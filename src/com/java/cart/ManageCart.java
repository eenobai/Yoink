package com.java.cart;

import com.java.controller.CookieController;
import com.java.controller.SQLController;
import com.java.controller.WebController;
import com.java.goods.ChangeGoodsQuantity;
import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Array;
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
    @Autowired
    ChangeGoodsQuantity changeGoodsQuantity;

    public HashMap<String, ArrayList<Integer>> addToCart(int id, int quantity, String category, HttpServletRequest request) throws SQLException {

        HashMap<String, ArrayList<Integer>> itemsInCart = cartController.personalCart(request, id, quantity);

        String cookieVal = cookieController.getCookie(request);

        //gets already existing items and their quantity from existing cart
        ArrayList <Integer> itemIds = itemsInCart.get("item_ids");
        ArrayList <Integer> itemQuantity = itemsInCart.get("item_quantity");

        //checks whether the item already exists in the cart
        if(itemIds.contains(id)){
            System.out.println("#####################################");
            System.out.println("#####################################");
            System.out.println("item already exists in the cart"); //test
            System.out.println("#####################################");
            System.out.println("#####################################");
        }else if(sqlController.getQuantity(id, category)<quantity){

            System.out.println("out of stock, you can get only " + sqlController.getQuantity(id, category) + " items");
            //need to add some kind of return idk
        }else{
            //adds new id to already existing array of ids
            itemIds.add(id);
            System.out.println("item Quantity before adding " + itemQuantity); //test
            //adds new quantity to already existing array of quantities
            itemQuantity.add(quantity);

            //puts arrays of ids and quantities back in the map
            itemsInCart.put("item_ids", itemIds);
            itemsInCart.put("item_quantity", itemQuantity);

            //changeGoodsQuantity.decreaseQuantity(id, quantity,category);  <<<<---- //TODO this is for the checkout

            //puts temporary map into carts map
            cartController.carts.put(cookieVal, itemsInCart);
        }

        System.out.println("items in cart " + itemsInCart.toString()); //test

        return itemsInCart;
    }

    //adjust quantity of the items in cart by 1
    public void adjustQuantity(HttpServletRequest request, int id, int quantity){

    }
}
