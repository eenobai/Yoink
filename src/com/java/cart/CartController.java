package com.java.cart;

import com.java.controller.CookieController;
import com.java.goods.GoodsParameters;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public HashMap<String, ArrayList> carts = new HashMap();
    //public HashMap<Integer, String> cartsCollection = new HashMap<>();
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



    public void cartMaker(HttpServletRequest request){
        System.out.println("cookie val from carMaker " + cookieController.getCookie(request));
        if(carts.get(cookieController.getCookie(request))== null){
            ArrayList<Integer> tempList = new ArrayList();
            tempList.add(123);
            System.out.println("temp list from cart maker " + tempList.toString());
            carts.put(cookieController.getCookie(request), tempList);
        }else{
            System.out.println("cart already exists");
        }
    }

    public ArrayList shoppingCart(HttpServletRequest request){

        return carts.get(cookieController.getCookie(request));
    }

}
