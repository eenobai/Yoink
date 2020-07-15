package com.java;

import com.java.Customer.Customer;
//import jdk.internal.org.jline.utils.Log;

import org.postgresql.util.PSQLException;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan
@PropertySource(value = "application.properties")
@EnableJpaRepositories(basePackages = "com.java.Repository")
class Application {
    public static void main(String[] args) throws ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}


