package com.firstclick.controller;

import com.firstclick.config.SpringConfig;
import com.firstclick.model.ClickRequest;
import com.firstclick.model.ClickResponse;
import com.firstclick.server.Processor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

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

