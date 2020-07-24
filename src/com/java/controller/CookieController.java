package com.java.controller;

import com.java.cart.CartController;
import com.java.fluff.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component
public class CookieController {

    @Autowired
    CartController cartController;
    @Autowired
    RandomStringGenerator randomStringGenerator;

    public String rndCookie;

    public String getCookie(HttpServletRequest request){

            //don't ask why
            String cookieVal = "";
            //requests all cookies from the websites and stores them in an array
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("myCookie")) {
                        cookieVal = cookie.getValue();
                    }
                }
            }
        return cookieVal;
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
                if (cookie.getName().equals("myCookie")) {
                    System.out.println("try// cookie exists newCookie");
                } else {
                    System.out.println("nullpoint been thrown");
                    throw new NullPointerException("cookie doesn't exist");
                }
            }
            System.out.println("cookie exists");
        }catch(NullPointerException e){
            rndCookie = randomStringGenerator.randomString(20);
            Cookie randomCookie = new Cookie("myCookie", rndCookie);
            response.addCookie(randomCookie);
            System.out.println("catch// cookie's been created from nullpoint");
        }catch(IllegalArgumentException e){
            rndCookie = randomStringGenerator.randomString(20);
            Cookie randomCookie = new Cookie("myCookie", rndCookie);
            response.addCookie(randomCookie);
            System.out.println("catch// cookie's been created from argument exception");
        }
        cartController.cartMaker(request);
    }

}
