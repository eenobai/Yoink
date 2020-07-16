package com.java.app;

import com.java.SpringConfig;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.ComponentScan;


//@EnableAutoConfiguration
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"com.java.controller"})
//@PropertySource(value = "application.properties")
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


