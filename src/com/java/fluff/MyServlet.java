package com.java.fluff;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MyServlet {

    //TODO doesn't work

    @RequestMapping("/hello")
    public String redirect() {
        System.out.println("it works?");
        return "viewpage";
    }

    @RequestMapping("/helloagain")
    public String display(){
        return "final";
    }

}
