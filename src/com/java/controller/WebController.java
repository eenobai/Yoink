package com.java.controller;

import com.java.Customer.Customer;
import com.java.cart.CartController;
import com.java.cart.ManageCart;
import com.java.goods.*;
import org.apache.catalina.filters.ExpiresFilter;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


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

    //HashMap<String, String> cart = new HashMap();
    //JSONArray cartArray = new JSONArray();
    //int serialNumber = 1;
    int cockie = 0;


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

    @RequestMapping("/asd")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }
    @RequestMapping("/a")
    public void getCookie(@CookieValue("myCookie") String fooCookie){
       System.out.println(fooCookie);
    }

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

    @RequestMapping("/addToCart")
    public void addToCart(@RequestBody GoodsParameters goodsParameters, HttpServletResponse servRes, HttpServletRequest servReq) throws SQLException{

        cookieController.cookieController(servRes, servReq);
        System.out.println(cartController.carts.get(cookieController.getCookie(servReq)));
        cartController.carts.put(cookieController.getCookie(servReq), manageCart.addToCart(goodsParameters.getId(), goodsParameters.getQuantity(), servReq));
        //cartController.cartsCollection.put(userId, manageCart.addToCart(goodsParameters.getCategoryName(), goodsParameters.getId()));
        System.out.println("hashmap request "+cartController.carts.get(cookieController.getCookie(servReq)));

    }

    @RequestMapping("/testCart")
    public HashMap<String, ArrayList<Integer>> testCart(HttpServletResponse servRes, HttpServletRequest servReq){
        cookieController.cookieController(servRes, servReq);
        return cartController.shoppingCart(servReq);
    }


    @GetMapping("/index.html")
    public void showPage(Model model, HttpServletRequest request) {
        model.addAttribute("someBean"); //assume SomeBean has a property called datePlanted
        if (request.getParameter("button1") != null) {
            System.out.println("button 1 click");
        } else if (request.getParameter("button2") != null) {
            System.out.println("button 2 click");
        } else if (request.getParameter("button3") != null) {
            System.out.println("button 3 click");
        } else {
            System.out.println("else got triggered");
        }
    }

}
