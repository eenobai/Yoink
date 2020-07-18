package com.java.cart;

import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CartController {

    HashMap<Integer, JSONObject> cart = new HashMap();
    int serialNum = 1;

    public String shoppingCart(){
        return cart.toString();
    }

}
