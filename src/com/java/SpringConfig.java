package com.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.java")
public class SpringConfig{
    /*
    private Customer customer;
    @Bean
    public Customer customer(){
        return new Customer(customer.getId(), customer.getName(), customer.getLastName());

    }
    */
}