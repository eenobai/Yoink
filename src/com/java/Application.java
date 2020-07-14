package com.java;

import com.java.Customer.Customer;
//import jdk.internal.org.jline.utils.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan
class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);
  //  @Autowired
  //  JdbcTemplate jdbcTemplate;


    /*@Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
    }
    */

    public static Connection getRemoteConnection() throws SQLException, ClassNotFoundException {

                Class.forName("org.postgresql.Driver"); //<--pepehands
                String dbName = System.getenv("yoinkName");
                String userName = System.getenv("admin");
                String password = System.getenv("mjaso3000");
                String hostname = System.getenv("database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com");
                String port = System.getenv("3306");
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                log.trace("Getting remote connection with connection string from environment variables.");
                Connection conn = DriverManager.getConnection(jdbcUrl);
                log.info("Remote connection successful.");

                String query = "SELECT * FROM users";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        System.out.println("does this work even");
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String questionmark = rs.getString("does_this_work");

                        // print the results
                        System.out.format("%s, %s, %s, %s\n", id, name, age, questionmark);
                    }
                    st.close();
            return conn;
            }





    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        //SpringApplication.run(Application.class, args);
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        getRemoteConnection();
    }


}


