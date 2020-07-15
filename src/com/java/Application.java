package com.java;


import com.java.Customer.Customer;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.criteria.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@EnableAutoConfiguration
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan
@PropertySource(value = "application.properties")
@EnableJpaRepositories(basePackages = "com.java.Repository")
class Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword");
        //PreparedStatement ps = connection.prepareStatement("CREATE DATABASE databasename");
        //int result = ps.executeUpdate();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        return sessionFactory;
    }

}


