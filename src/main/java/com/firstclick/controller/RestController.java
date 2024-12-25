package com.firstclick.controller;

import com.firstclick.ClickButtonServerSpringMavenApplication;
import com.firstclick.model.ClickRequest;
import com.firstclick.model.ClickResponse;
import com.firstclick.server.Memory;
import com.firstclick.server.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import com.firstclick.config.SpringConfig;
import org.apache.logging.log4j.LogManager;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/firstClick")
public class RestController {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    Processor processor = context.getBean("processor", Processor.class);
    Memory memory = context.getBean("memory", Memory.class);
    static final Logger log = LoggerFactory.getLogger(RestController.class);


    @GetMapping
    public ClickResponse showStatus() {
        return new ClickResponse("success", 1);
    }

    @PostMapping("/click")
    public ClickResponse click(@RequestBody ClickRequest request) {
        log.trace("API request on /click");
        return processor.getResponse(request);
    }

    @GetMapping("/info")
    public ClickResponse getInfo(@RequestBody ClickRequest request) {
        //log.trace("API request on /getInfo");
        return processor.getResponse(request);
    }

    @GetMapping("/whosFirst")
    public String whosFirst() {
        //log.trace("API request on /getInfo");
        if (memory.getFirstClickedName().equals("")) {
            return "Нажмите на кнопку, чтобы ответить";
        } else return "Первый отвечает " + memory.getFirstClickedName();
    }

    @PostMapping("/clear")
    public ClickResponse clearFirst(@RequestBody ClickRequest request) {
        log.trace("API request on /clear");
        return processor.getResponse(request);
    }
}
