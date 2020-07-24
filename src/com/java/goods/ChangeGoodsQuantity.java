package com.java.goods;

import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ChangeGoodsQuantity {

    @Autowired
    SQLController sqlController;

    public void increaseQuantity(int id, int moreGoods, String category) throws SQLException {
        int newQuantity = sqlController.getQuantity(id, category) + moreGoods;
        sqlController.setQuantity(category, newQuantity, id);
        System.out.println(newQuantity + " increase");
    }

    public void decreaseQuantity(int id, int lessGoods, String category) throws SQLException {
        int newQuantity = sqlController.getQuantity(id, category) - lessGoods;
        sqlController.setQuantity(category, newQuantity, id);
        System.out.println(newQuantity + " decrease");
    }
}
