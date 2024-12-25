package com.firstclick.utils;

import java.util.Random;

public class Generator {
    static Random random = new Random();
    public int generateNewId(){
        return random.nextInt(10000, 99999);
    }
}
