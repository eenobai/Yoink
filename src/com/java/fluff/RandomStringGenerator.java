package com.java.fluff;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringGenerator {

    public String randomString (int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder rndS = new StringBuilder();
        Random rnd = new Random();
        while (rndS.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            rndS.append(chars.charAt(index));
        }
        return rndS.toString();
    }

}
