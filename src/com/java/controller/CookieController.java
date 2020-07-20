package com.java.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieController {

    private Object NullPointerException;

    public void getCookie(@CookieValue("myCookie") String fooCookie){
        System.out.println(fooCookie);
    }

    public void cookieController (HttpServletResponse response, HttpServletRequest request) {
        Cookie newCookie = new Cookie("myCookie", "doesThisWork?");

        try {
            Cookie cookies[] = request.getCookies();
            System.out.println(ArrayUtils.contains(cookies, newCookie));

            for(Cookie cookie :cookies) {
                System.out.println(cookie.equals(newCookie));
                //System.out.println(cookie + " retrieved cookie");
                //System.out.println(newCookie + " newCookie");
                if (cookie.equals(newCookie)) {
                    System.out.println("try// cookie exists newCookie");
                } else {
                    System.out.println("nullpoint been thrown");
                    //throw new NullPointerException("cookie doesn't exist");
                }
            }
            System.out.println("cookie exists");
        }catch(NullPointerException e){
            //response.addCookie(newCookie);
            //System.out.println("catch// cookie's been created from nullpoint");
        }catch(IllegalArgumentException e){
            response.addCookie(newCookie);
            System.out.println("catch// cookie's been created from argument exception");
        }
    }

}
