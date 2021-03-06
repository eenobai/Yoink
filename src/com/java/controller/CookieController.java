package com.java.controller;


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

    public String rndCookie;

    private Object NullPointerException;

    //public void getCookie(@CookieValue("myCookie") String fooCookie){
    //    System.out.println(fooCookie);
    //}

    public String getCookie(HttpServletRequest request){

            String cookieVal = "";

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
                    //throw new NullPointerException("cookie doesn't exist");
                }
            }
            System.out.println("cookie exists");
        }catch(NullPointerException e){
            //response.addCookie(newCookie);
            //System.out.println("catch// cookie's been created from nullpoint");
        }catch(IllegalArgumentException e){
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder rndS = new StringBuilder();
            Random rnd = new Random();
            while (rndS.length() < 18) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                rndS.append(SALTCHARS.charAt(index));
            }
            rndCookie = rndS.toString();
            Cookie randomCookie = new Cookie("myCookie", rndCookie);
            response.addCookie(randomCookie);
            System.out.println("catch// cookie's been created from argument exception");
        }
        cartController.cartMaker(request);
    }

}
