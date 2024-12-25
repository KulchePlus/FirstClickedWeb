package com.firstclick.controller;

import com.firstclick.model.ClickRequest;
import com.firstclick.model.ClickResponse;
import com.firstclick.server.Processor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import com.firstclick.config.SpringConfig;

@RestController
@RequestMapping("/firstClick")
public class Controller {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    Processor processor = context.getBean("processor", Processor.class);


    @GetMapping
    public ClickResponse showStatus() {
        return new ClickResponse("success", 1);
    }

    @PostMapping("/click")
    public ClickResponse pay(@RequestBody ClickRequest request) {
        return processor.getResponse(request);
    }

    @GetMapping("/getInfo")
    public String getIndex() {
        return "ТЫ ХУЙ!";
    }
}
