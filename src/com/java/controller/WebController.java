package com.java.controller;

import com.java.Customer.Customer;
import com.java.fluff.SortAWS;
import com.java.model.ManageCart;
import com.java.model.TableOfGoods;
import com.java.goods.*;
import com.java.model.ChangeGoodsQuantity;
import com.java.model.ManageCategories;
import com.java.model.ManageGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    CookieController cookieController;
    @Autowired
    TableOfGoods tableOfGoods;
    @Autowired
    SortAWS sortAWS;

    //################################################################
    //################################################################
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
    public void post(@RequestBody Customer customer){
    }

    @RequestMapping("/asd")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }
    @RequestMapping("/a")
    public void getCookie(@CookieValue("myCookie") String fooCookie){
        System.out.println(fooCookie);
    }

    @RequestMapping(value = "/managegoods.html", method = RequestMethod.GET)
    public GoodsParameters testAddGoodsGet(){ //takes "categoryName", "id", "goodsName", "price", "quantity", "tags"
        GoodsParameters newGood = new GoodsParameters();
        return newGood;
    }
    @RequestMapping(value = "/submitgood.html", method = RequestMethod.POST)
    public String testAddGoodsPost(@RequestParam("goodsName") String name, @RequestParam("categoryName") String categoryName, @RequestParam("id") int id,
                                   @RequestParam("price") double price, @RequestParam("quantity") int quantity, @RequestParam("tags") String tags){ //takes "categoryName", "id", "goodsName", "price", "quantity", "tags"
        //return manageGoods.addGoods(goodsParameters.getCategoryName(), goodsParameters.getId(), goodsParameters.getGoodsName(), goodsParameters.getPrice(),goodsParameters.getQuantity(),goodsParameters.getTags());
        System.out.println(name + categoryName +id + price + quantity + tags);
        return "kekw?";
    }

    //################################################################
    //################################################################

    @RequestMapping("/")
    public String setCookie(HttpServletResponse servRes, HttpServletRequest servReq){
        cookieController.cookieController(servRes, servReq);
        return "does the cookie work?";
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
        changeGoodsQuantity.increaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity(), goodsParameters.getCategoryName());
    }

    @RequestMapping("/decreaseGoods")
    public void decreaseGoods(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "id" and "quantity". Substracts input quantity from already existing one
        changeGoodsQuantity.decreaseQuantity(goodsParameters.getId(), goodsParameters.getQuantity(), goodsParameters.getCategoryName());
    }

    @RequestMapping("/createCategory")
    public String createNewCategory(@RequestBody GoodsParameters goodsParameters) throws SQLException { //takes "categoryName"
        return manageCategories.createNewCategory(goodsParameters.getCategoryName());
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestBody GoodsParameters goodsParameters) throws SQLException{
        return manageCategories.deleteCategory(goodsParameters.getCategoryName());
    }

    @RequestMapping("/addToCart")
    public void addToCart(@RequestBody GoodsParameters goodsParameters, HttpServletResponse servRes, HttpServletRequest servReq) throws SQLException{
        cookieController.cookieController(servRes, servReq);
        System.out.println(cartController.carts.get(cookieController.getCookie(servReq)));
        cartController.carts.put(cookieController.getCookie(servReq), manageCart.addToCart(goodsParameters.getId(), goodsParameters.getQuantity(), goodsParameters.getCategoryName(), servReq));
        //cartController.cartsCollection.put(userId, manageCart.addToCart(goodsParameters.getCategoryName(), goodsParameters.getId()));
        System.out.println("hashmap request "+cartController.carts.get(cookieController.getCookie(servReq)));
    }

    @RequestMapping("/testCart")
    public HashMap<String, ArrayList<Integer>> testCart(HttpServletResponse servRes, HttpServletRequest servReq){
        cookieController.cookieController(servRes, servReq);
        return cartController.shoppingCart(servReq);
    }

    @RequestMapping("/showEverything")
    public void showEverything(@RequestBody GoodsParameters goodsParameters) throws SQLException {
        System.out.println(goodsParameters.getSortBy());
        tableOfGoods.showTable(goodsParameters.getCategoryName(), goodsParameters.getSortBy());
    }

}