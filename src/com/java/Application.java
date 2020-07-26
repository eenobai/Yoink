package com.java;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;


@EnableAutoConfiguration
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"com.java.controller"})
@ComponentScan({"com.java.Customer"})
@ComponentScan({"com.java.goods"})
//@PropertySource("C:\\Users\\Ilya\\Desktop\\Yoink\\src\\com\\resources\\application.properties")
//@EnableJpaRepositories(basePackages = "com.java.Repository")
   // @ServletComponentScan({"com.java.WEB-INF"})
class Application {
  public static void main(String[] args){
       SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}


