package com.java.controller;

import com.java.Customer.Customer;
import com.java.goods.AddGoods;
import com.java.goods.ChangeGoodsQuantity;
import com.java.goods.GoodsParameters;
import com.java.goods.NewCategory;
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
GoodsParameters goodsParameters;
@Autowired
ChangeGoodsQuantity changeGoodsQuantity;
@Autowired
AddGoods addGoods;
@Autowired
NewCategory newCategory;

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
        return addGoods.addGoods(goodsParameters.getCategoryName(), goodsParameters.getId(), goodsParameters.getGoodsName(), goodsParameters.getPrice(),goodsParameters.getQuantity(),goodsParameters.getTags());
    }

    @RequestMapping("/increaseGoods")
    public void increaseGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "id" and "quantity". Adds input quantity to already existing one
        changeGoodsQuantity.increaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity());
    }

    @RequestMapping("/decreaseGoods")
    public void decreaseGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "id" and "quantity". Substracts input quantity from already existing one
        changeGoodsQuantity.decreaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity());
    }

    @RequestMapping("/createNewCategory")
    public String createNewCategory(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "categoryName"
        return newCategory.createNewCategory(goodsParameters.getCategoryName());
    }


}
