package com.firstclick.controller;

import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/player")
    public String playerPage() {
        return "playerPage";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

}

