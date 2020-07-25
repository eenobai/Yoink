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
public class MyServlet extends HttpServlet {

    //TODO doesn't work

    @RequestMapping("/index.html")
    public void showPage() {
        System.out.println("it works?");
/*
        System.out.println(button1 + button2 + button3);

        if (request.getParameter("button1") != null) {
            System.out.println("button 1 click");
        } else if (request.getParameter("button2") != null) {
            System.out.println("button 2 click");
        } else if (request.getParameter("button3") != null) {
            System.out.println("button 3 click");
        } else {
            System.out.println("else got triggered");
        }

 */
    }
}
