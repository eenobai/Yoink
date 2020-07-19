package com.java.controller;

import com.java.Customer.Customer;
import com.java.cart.CartController;
import com.java.cart.ManageCart;
import com.java.goods.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class WebController {

@Autowired
Customer customer;
@Autowired
SQLController sqlController;
@Autowired
GoodsParameters goodsParameters;
@Autowired
ChangeGoodsQuantity changeGoodsQuantity;
@Autowired
ManageGoods manageGoods;
@Autowired
ManageCategories manageCategories;
@Autowired
ManageCart manageCart;
@Autowired
CartController cartController;

    //HashMap<String, String> cart = new HashMap();
    //JSONArray cartArray = new JSONArray();
    //int serialNumber = 1;

    @RequestMapping("/testget")
    public HashMap<String, List<String>> test() throws SQLException {
        List<String> id = new ArrayList();
        List<String> text = new ArrayList();
        List<String> more_id = new ArrayList();
        List<String> quantity = new ArrayList();
        HashMap<String, List<String>> returnMap = new HashMap<>();
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM test_table");
        while(myRs.next()){
            System.out.println(myRs.getInt("id")+ " " + myRs.getString("text") + " " + myRs.getInt("more_id"));
            id.add(String.valueOf(myRs.getInt("id")));
            text.add(myRs.getString("text"));
            more_id.add(String.valueOf(myRs.getInt("more_id")));
            quantity.add(String.valueOf(myRs.getInt("quantity")));
            returnMap.put("id", id);
            returnMap.put("text", text);
            returnMap.put("more_id", more_id);
            returnMap.put("quantity", quantity);
        }

        return returnMap;
    }

    @RequestMapping("/testpost")
    public void post(@RequestBody Customer customer) throws SQLException {
    }

    @RequestMapping("/addGoods")
    public String addGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException{ //takes "categoryName", "id", "goodsName", "price", "quantity", "tags"
        return manageGoods.addGoods(goodsParameters.getCategoryName(), goodsParameters.getId(), goodsParameters.getGoodsName(), goodsParameters.getPrice(),goodsParameters.getQuantity(),goodsParameters.getTags());
    }

    @RequestMapping("/deleteGoods")
    public String deleteGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException{
        return manageGoods.deleteGoods(goodsParameters.getId(), goodsParameters.getCategoryName());
    }

    @RequestMapping("/increaseGoods")
    public void increaseGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "id" and "quantity". Adds input quantity to already existing one
        changeGoodsQuantity.increaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity());
    }

    @RequestMapping("/decreaseGoods")
    public void decreaseGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "id" and "quantity". Substracts input quantity from already existing one
        changeGoodsQuantity.decreaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity());
    }

    @RequestMapping("/createCategory")
    public String createNewCategory(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "categoryName"
        return manageCategories.createNewCategory(goodsParameters.getCategoryName());
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestBody GoodsParameters goodsParameters) throws SQLException{
        return manageCategories.deleteCategory(goodsParameters.getCategoryName());
    }

    @RequestMapping("/{userId}/addToCart")
    public void addToCart(@RequestBody GoodsParameters goodsParameters, @PathVariable("userId") int userId) throws SQLException{
        //cartController.check(cartController.cart);
        //cartController.cartsCollection.put(cartController.cartId, cartController.cart)
        //cartController.createUniqueCart();
        //cartController.check(cartController.cart).put(userId, cartController.cart);
        cartController.cartsCollection.put(userId, manageCart.addToCart(goodsParameters.getCategoryName(), goodsParameters.getId()));

    }

    @RequestMapping("/{userId}/testCart")
    public String testCart(@PathVariable("userId") int userId){
        System.out.println(userId);
        return cartController.shoppingCart(userId);
    }

}
