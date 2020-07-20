package com.java.cart;

import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CartController {

    public List<Integer> userIds = new ArrayList();

    public HashMap<Integer, JSONObject> cart = new HashMap();
    public HashMap<Integer, String> cartsCollection = new HashMap<>();
    int serialNum = 1;
    //public int cartId = 1;

    //TODO make it so each new shopping cart creates a new SQL table

    //public int createUniqueCart(){
        //int cartId = 1;
        //while(cartsCollection.containsKey(cartId)){
         //   cartId++;
        //}
        //cartsCollection.put(cartId, cart);
        //return cartId;
    //}

    //public void checkIfEmpty(){
    //    if(cartsCollection.containsKey(createUniqueCart()) && cartsCollection.get(createUniqueCart()).isEmpty()){
//
     //   }
   // }


    public String shoppingCart(int cartId){

        return cartsCollection.toString();
    }

}
