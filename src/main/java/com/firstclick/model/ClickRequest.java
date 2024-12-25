package com.firstclick.model;


import lombok.Getter;

public class ClickRequest {
    @Getter
    private int userId;

    @Getter
    private messageType messageType;

    @Getter
    private String name;
}

