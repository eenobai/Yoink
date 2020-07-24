package com.java;

import com.java.controller.SQLController;
import com.java.goods.ChangeGoodsQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.Statement;


@EnableAutoConfiguration
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"com.java.controller"})
@ComponentScan({"com.java.Customer"})
@ComponentScan({"com.java.goods"})
//@PropertySource("C:\\Users\\Ilya\\Desktop\\Yoink\\src\\com\\resources\\application.properties")
//@EnableJpaRepositories(basePackages = "com.java.Repository")
class Application {

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    //@Bean
    //public LocalSessionFactoryBean sessionFactory() {
        //LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        //return sessionFactory;
    //}

}


