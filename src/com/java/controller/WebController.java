package com.java.controller;

import com.java.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class WebController {

@Autowired
    Customer customer;

    @RequestMapping("/testget")
    public List<String> test() throws SQLException {
        List<String> test = new ArrayList();
        Connection conn = DriverManager.getConnection("jdbc:mysql://database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com/test_schema","admin", "mjaso3000");
        Statement statement = conn.createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM test_table");
        while(myRs.next()){
            System.out.println(myRs.getInt("id")+ " " + myRs.getString("text") + " " + myRs.getInt("more_id"));
            test.add(myRs.getString("text"));
        }

        return test;
    }

    @RequestMapping("/testpost")
    public void post(@RequestBody Customer customer) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com/test_schema","admin", "mjaso3000");
        PreparedStatement post = conn.prepareStatement("INSERT INTO test_table(id) VALUES('"+customer.getId()+"')");
        post.executeUpdate();
    }

    //TODO communication with web and other shait

}
