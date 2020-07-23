package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MyServlet extends HttpServlet {

    String button1;

    @RequestMapping("/index.html")
    public String showPage(Model model, @RequestParam(value = "button1", required = false) String button1, @RequestParam(value = "button1", required = false) String button2, @RequestParam(value = "button1", required = false) String button3, HttpServletRequest request) {

        if (request.getParameter("button1") != null) {
            System.out.println("button 1 click");
        } else if (request.getParameter("button2") != null) {
            System.out.println("button 2 click");
        } else if (request.getParameter("button3") != null) {
            System.out.println("button 3 click");
        } else {
            System.out.println("else got triggered");
        }
        return button1;
    }
}
