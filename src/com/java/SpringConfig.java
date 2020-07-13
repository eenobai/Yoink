package com.java;

import com.java.Customer.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.*;

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