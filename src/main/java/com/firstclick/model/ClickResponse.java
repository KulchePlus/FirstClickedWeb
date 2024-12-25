package com.firstclick.model;

import com.firstclick.utils.Generator;
import lombok.Getter;

public class ClickResponse {
    @Getter
    private final String status;
    @Getter
    private final Integer code;
    @Getter
    private int userId;
    @Getter
    private String name;

    public ClickResponse(String status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public ClickResponse(String status, Integer code, int userId) {
        this.status = status;
        this.code = code;
        this.userId = userId;
    }
    public ClickResponse(String status, Integer code, int userId, String name) {
        this.status = status;
        this.code = code;
        this.userId = userId;
        this.name = name;
    }
}
