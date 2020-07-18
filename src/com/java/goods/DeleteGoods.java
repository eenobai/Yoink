package com.java.goods;

import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DeleteGoods {

    @Autowired
    SQLController sqlController;

    public String deleteGoods(int id, String categoryName) throws SQLException {

        Statement statement = sqlController.sqlController().createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM "+categoryName+"");
        List<Integer> existingGoods = new ArrayList();

        while(myRs.next()){
            existingGoods.add(myRs.getInt("id"));
        }

        if(existingGoods.contains(id)){
            PreparedStatement post = sqlController.sqlController().prepareStatement("DELETE FROM "+categoryName+" WHERE id = "+id+";");
            post.executeUpdate();
            return "good's been deleted successfully";
        }else{
            return "good that you are trying to delete does not exist";
        }
    }
}
