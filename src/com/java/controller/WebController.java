package com.java.controller;

import com.java.Customer.Customer;
import com.java.goods.ChangeGoodsQuantity;
import com.java.goods.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@RestController
public class WebController {

@Autowired
Customer customer;
@Autowired
SQLController sqlController;
@Autowired
Goods goods;
@Autowired
ChangeGoodsQuantity changeGoodsQuantity;

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
        PreparedStatement post = sqlController.sqlController().prepareStatement("INSERT INTO test_table(id) VALUES('"+customer.getId()+"')");
        post.executeUpdate();
    }

    @RequestMapping("/addGoods")
    public void addGoods(@RequestBody Goods goods) throws SQLException{ //takes "categoryName", "id", "goodsName", "price", "quantity", "tags"
        System.out.println(goods.getTags().toString());
        PreparedStatement post = sqlController.sqlController().prepareStatement("INSERT INTO "+goods.getCategoryName()+"(id, goods_name, price, quantity, tags) VALUES('"+goods.getId()+"', '"+goods.getGoodsName()+"','"+goods.getPrice()+"','"+goods.getQuantity()+"','"+Arrays.toString(goods.getTags())+"')");
        post.executeUpdate();
    }

    @RequestMapping("/increaseGoods")
    public void increaseGoods(@RequestBody Goods goods) throws SQLException { //takes "id" and "quantity". Adds input quantity to already existing one
        changeGoodsQuantity.increaseQuantity(goods.getId(), goods.getQuantity());
       // changeGoodsQuantity.alterQuantity(changeGoodsQuantity.getId(), changeGoodsQuantity.getQuantity());
    }

    @RequestMapping("/decreaseGoods")
    public void decreaseGoods(@RequestBody Goods goods) throws SQLException { //takes "id" and "quantity". Substracts input quantity from already existing one
        changeGoodsQuantity.decreaseQuantity(goods.getId(), goods.getQuantity());
        // changeGoodsQuantity.alterQuantity(changeGoodsQuantity.getId(), changeGoodsQuantity.getQuantity());
    }

    @RequestMapping("/createNewCategory")
    public void createNewCategory(){

    }


}
