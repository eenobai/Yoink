package com.java.cart;

import com.java.controller.CookieController;
import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CartController {

    @Autowired
    CookieController cookieController;

    public List<Integer> userIds = new ArrayList();

    public HashMap<String, HashMap<String, ArrayList<Integer>>> carts = new HashMap();

    int serialNum = 1;



    public void cartMaker(HttpServletRequest request){
        System.out.println("cookie val from carMaker " + cookieController.getCookie(request));
        if(carts.get(cookieController.getCookie(request))== null){
            HashMap<String, ArrayList<Integer>> tempMap = new HashMap();
            ArrayList <Integer> blankListId = new ArrayList();
            blankListId.add(0);
            ArrayList <Integer> blankListQuantity = new ArrayList();
            blankListQuantity.add(0);
            tempMap.put("item_ids", blankListId);
            tempMap.put("item_quantity", blankListQuantity);
            System.out.println("temp map from cart maker " + tempMap.toString());
            carts.put(cookieController.getCookie(request), tempMap);
        }else{
            System.out.println("cart already exists");
        }
    }

    public HashMap<String, ArrayList<Integer>> personalCart(HttpServletRequest request, int id, int quantity){
        String cookieVal = cookieController.getCookie(request);

        System.out.println("cookie val " + cookieVal);
        System.out.println("id " + id);
        System.out.println("quantity " + quantity);

        //creates temp hashmap cart that will be stored in carts map later on
        HashMap<String, ArrayList<Integer>> itemsInCart = new HashMap();

        //get specific cart from carts map
        itemsInCart = carts.get(cookieVal);
        return itemsInCart;
    }

    //TODO idk why I made this
    public HashMap<String, ArrayList<Integer>> shoppingCart(HttpServletRequest request){

        return carts.get(cookieController.getCookie(request));
    }

}
