package com.firstclick.controller;


import com.firstclick.model.ClickResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LocalTestController {

    @GetMapping
    public String  showStatus() {
        return "Ты пиписька";
    }
}
