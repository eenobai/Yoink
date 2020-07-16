package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.sql.*;

//@EnableAutoConfiguration
//@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan
//@PropertySource(value = "application.properties")
//@EnableJpaRepositories(basePackages = "com.java.Repository")
class Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //SpringApplication.run(Application.class, args);
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword");
        //PreparedStatement ps = connection.prepareStatement("CREATE DATABASE databasename");
        //int result = ps.executeUpdate();

        Connection conn = DriverManager.getConnection("jdbc:mysql://database-1.coaeroq78uw6.eu-central-1.rds.amazonaws.com/test_schema","admin", "mjaso3000");
        Statement statement = conn.createStatement();
        ResultSet myRs = statement.executeQuery("SELECT * FROM test_table");
        while(myRs.next()){
            System.out.println(myRs.getInt("id")+ " " + myRs.getString("text") + " " + myRs.getInt("more_id"));
        }

    }

    //@Bean
    //public LocalSessionFactoryBean sessionFactory() {
        //LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        //return sessionFactory;
    //}

}


