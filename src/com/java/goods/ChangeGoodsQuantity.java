package com.java.goods;

import org.springframework.web.bind.annotation.*;
import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.sql.*;

@Component
public class ChangeGoodsQuantity {

    @Autowired
    SQLController sqlController;

    public void increaseQuantity(int id, int moreGoods) throws SQLException {
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM list_of_goods WHERE id= '"+id+"';");
        System.out.println();
        myRs.next();
        int newQuantity = myRs.getInt("quantity") + moreGoods;
        PreparedStatement post = sqlController.sqlController().prepareStatement("UPDATE list_of_goods SET quantity = '"+newQuantity+"' where id = '"+id+"'");
        post.executeUpdate();
        System.out.println(newQuantity);
    }

    public void decreaseQuantity(int id, int moreGoods) throws SQLException {
        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM list_of_goods WHERE id= '"+id+"';");
        System.out.println();
        myRs.next();
        int newQuantity = myRs.getInt("quantity") - moreGoods;
        PreparedStatement post = sqlController.sqlController().prepareStatement("UPDATE list_of_goods SET quantity = '"+newQuantity+"' where id = '"+id+"'");
        post.executeUpdate();
        System.out.println(newQuantity + " decrease");
    }
}
