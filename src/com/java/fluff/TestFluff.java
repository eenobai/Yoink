package com.java.fluff;


import com.java.controller.SQLController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestFluff {

    @Autowired
    SQLController sqlController;

    public String sort() throws SQLException {

            List<String> tags = new ArrayList();

            int i = 0;

            Statement statement = sqlController.sqlController().createStatement();
            ResultSet myRs = statement.executeQuery("SELECT * FROM list_of_goods;");

            while(myRs.next()){
                tags.add(myRs.getString("tags"));
            }
            System.out.println(tags.toString());
            String replaced1 = tags.get(1).replace("[", "\"tags\":[ ");
        System.out.println(replaced1);
            String replaced2 = replaced1.replace("]", "\"]");
        System.out.println(replaced2);
            String replaced3 = replaced2.replace(" ", "\"");
        System.out.println(replaced3);
            String replaced4 = replaced3.replace(",", "\",");
        System.out.println(replaced4);






            /*
            while(i<ids.size()){
                PreparedStatement post = sqlController.sqlController().prepareStatement("INSERT INTO "+category+" VALUES ("+ids.get(i)+", "+goodsNames.get(i)+", "+price.get(i)+", "+quantity.get(i)+", "+tags.get(i)+")");
                post.executeUpdate();
                i++;
            }
*/
        return replaced4;
    }
}
