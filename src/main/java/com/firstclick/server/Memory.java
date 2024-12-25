package com.firstclick.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Memory {
    @Getter
    @Setter
    private int FirstClickedId;
    @Getter
    @Setter
    private String FirstClickedName;
    HashMap<Integer, String> players = new HashMap<>();
}
